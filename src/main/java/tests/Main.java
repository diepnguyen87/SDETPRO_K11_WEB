package tests;

import com.google.common.reflect.ClassPath;
import driver.BrowserType;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws IOException {
        //get all class test
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testsClasses = new ArrayList<>();
        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith("tests.") &&
                    !info.getName().equalsIgnoreCase("tests.BaseTest") &&
                    !info.getName().equalsIgnoreCase("tests.Main") &&
                    !info.getName().equalsIgnoreCase("tests.CheckTriangle") &&
                    !info.getName().equalsIgnoreCase("tests.Point")) {
                testsClasses.add(info.load());
            }
        }

        //get browser
        String browser = System.getProperty("browser");

        if (browser == null) {
            throw new RuntimeException("[ERROR] Please supply browser via -Dbrowser");
        }

        try {
            BrowserType.valueOf(browser);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] " + browser + " is not supported. We coverd for " + Arrays.toString(BrowserType.values()));
        }

        //we will parallel base on maximum parallel session
        final int MAX_PARALLEL_SESSION = 3;
        List<String> testGroupName = new ArrayList<>();
        for (int index = 0; index < MAX_PARALLEL_SESSION; index++) {
            testGroupName.add("Group " + (index + 1));
        }

        //divide test classes into groups
        int testNumOfEachGroup = testsClasses.size() / testGroupName.size();
        HashMap<String, List<Class<?>>> desiredCaps = new HashMap<>();
        for (int groupIndex = 0; groupIndex < testGroupName.size(); groupIndex++) {
            int startIndex = groupIndex * testNumOfEachGroup;
            int endIndex = groupIndex == testGroupName.size()-1 ? testsClasses.size() : (startIndex + testNumOfEachGroup);
            List<Class<?>> subTestList = testsClasses.subList(startIndex, endIndex);
            desiredCaps.put(testGroupName.get(groupIndex), subTestList);
        }

        //build dynamic test suite
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        //put all test class into groups
        List<XmlTest> allTest = new ArrayList<>();
        for (String groupName : desiredCaps.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(groupName);

            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> dedicatedClasses = desiredCaps.get(groupName);
            for (Class<?> dedicatedClass : dedicatedClasses) {
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }
            test.setXmlClasses(xmlClasses);
            test.addParameter("browser", browser);
            allTest.add(test);
        }
        //add all tests into suite
        boolean isTestingSafari = browser.equalsIgnoreCase("safari");
        suite.setTests(allTest);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(isTestingSafari ? 1 : MAX_PARALLEL_SESSION);

        //Run a group of test
        if(isTestingSafari){
            suite.addIncludedGroup("smoke");
        }
        System.out.println(suite.toXml());

        //add suite into suite list
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        //invoke method run
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
