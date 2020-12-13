import java.io.FileWriter;
import java.io.IOException;

public class FileWriterMain {
    public static void main(String[] args) {
        try {
            int i=1;
            while(i<40){

                FileWriter fileWriter = new FileWriter("D:\\股票.txt",true);
                fileWriter.append("nihao"+i);
                fileWriter.flush();
                fileWriter.close();
                System.out.println(i);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
