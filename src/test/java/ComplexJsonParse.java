import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js=new JsonPath(payload.CoursePrice());
        // Print number of courses returned by API

        int count = js.getInt("courses.size()");
        System.out.println("Number of courses returned by API: " + count);
        // Print purchase amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Total purchase amount: " + purchaseAmount);
        // Print title of first course
        String courseTitle = js.getString("courses[0].title");
        System.out.println("Course title: " + courseTitle);

        // Print All course titles and their respective prices
        for (int i = 0; i < count; i++) {
            String title = js.getString("courses[" + i + "].title");
            int price = js.getInt("courses[" + i + "].price");
            System.out.println("Course title: " + title + " Price: " + price);
        }
        System.out.println("Number of copies of RPA:");
        for (int i = 0; i < count; i++) {
            String title = js.getString("courses[" + i + "].title");
            if (title.equalsIgnoreCase("RPA")){
                int copies = js.getInt("courses[" + i + "].copies");
                System.out.println("Copies: " + copies);
                break;
            }
        }
        int purchaseAmountCalculated = 0;
        for (int i = 0; i < count; i++) {
            int price = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            purchaseAmountCalculated += copies * price;
        }
        System.out.println("Total purchase amount calculated: " + purchaseAmountCalculated);
        Assert.assertEquals(purchaseAmount, purchaseAmountCalculated, "They are not equal");



}
    }