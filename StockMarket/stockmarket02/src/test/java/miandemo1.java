import java.io.IOException;
import java.util.List;

public class miandemo1 {
    public static void main(String[] args) {
      //  querystockcodes querystockcodes= new querystockcodes();
        try {
            List<String> codes =querystockcodes.getAllStackCodes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
