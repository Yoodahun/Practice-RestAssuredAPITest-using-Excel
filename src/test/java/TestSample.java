import java.util.ArrayList;
import java.util.List;

public class TestSample {
    public static void main(String[] args) {
        DataDriven dataDriven = new DataDriven();

        List<String> list = dataDriven.getData("Testcases", "Purchase");

        for (String data : list) {
            System.out.println(data);
        }


    }
}
