package test_data;

import com.google.gson.Gson;
import org.openqa.selenium.json.Json;
import test_data.computer.ComputerData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGSON {

    public static void main(String[] args) {
        //exploreGSonFeature();
        ComputerData[] computerData = testDataObjectBuilder();
        Arrays.asList(computerData).forEach(productItem -> System.out.println(productItem));
    }

    private static ComputerData[] testDataObjectBuilder() {
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerData = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerData;
    }

    private static void exploreGSonFeature() {
        String jsonString = "{\n" +
                "  \"processorType\": \"Fast\",\n" +
                "  \"ram\": \"8 GB\",\n" +
                "  \"hdd\": \"320 GB\",\n" +
                "  \"software\": \"Image Viever\"\n" +
                "}";

        Gson gson = new Gson();
        ComputerData computerData = gson.fromJson(jsonString, ComputerData.class);
        System.out.println(computerData);
        System.out.println(gson.toJson(computerData));
    }
}
