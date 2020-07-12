import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ExcelDrivenTest {

    DataDriven dataDriven = new DataDriven();
    String book_id = null;


    @Test
    public void addBook() {
        RestAssured.baseURI="http://216.10.245.166";
        Response response = given().log().all()
                                    .header("Content-Type", "application/json")
                                    .body(dataDriven.getBody())
                            .when().post("Library/Addbook.php")
                            .then().log().all().statusCode(200).extract().response();

        book_id = response.jsonPath().getString("ID");
        System.out.println(book_id);

    }


}
