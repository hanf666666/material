package reflect.reflectmain;

import org.testng.annotations.Test;
import reflect.domains.User;

import java.lang.reflect.Constructor;

public class Reflect_main {
    @Test
    public void demo01(){
        try {
            Class<User> aClass = (Class<User>) Class.forName("reflect.domains.User");
            Constructor<User> constructor = aClass.getConstructor();
            User user = constructor.newInstance();
            System.out.println(user

            );


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
