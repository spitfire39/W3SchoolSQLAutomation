package W3SchoolTests.Data;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "sql")
    public static Object[][] sqlQuery()
    {
        return new Object[][] { { "data one" } };
    }
}