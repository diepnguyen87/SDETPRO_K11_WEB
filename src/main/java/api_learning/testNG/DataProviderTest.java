package api_learning.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "getUserName", groups = {"smoke"})
    public void testUserName(User user){
        System.out.println(user);
    }

    @DataProvider()
    public User[] getUserName(){
        User user1 = new User("teo", 20);
        User user2 = new User("ti", 21);
        User user3 = new User("tun", 22);

        return new User[]{user1, user2, user3};
    }

    public static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
