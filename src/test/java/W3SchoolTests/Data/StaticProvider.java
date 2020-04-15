package W3SchoolTests.Data;
import org.testng.annotations.DataProvider;

public class StaticProvider {
    @DataProvider(name = "Test_1")
    public static Object[][] testData1()
    {
        return new Object[][] { { "SELECT * FROM Customers", "Giovanni Rovelli", "Via Ludovico il Moro 22" } };
    }

    @DataProvider(name = "Test_2")
    public static Object[][] testData2()
    {
        return new Object[][] { { "SELECT * FROM Customers WHERE City = 'London'", 6, "6" } };
    }

    @DataProvider(name = "Test_3")
    public static Object[][] testData3()
    {
        return new Object[][] { { "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ('TestCustomer', 'TestContactName', 'TestStreet', 'London', '5555', 'UK')",
                "SELECT * FROM Customers WHERE CustomerName = 'TestCustomer' AND ContactName = 'TestContactName'",
                1, "1" } };
    }

    @DataProvider(name = "Test_4")
    public static Object[][] testData4()
    {
        return new Object[][] { { "SELECT CustomerID FROM Customers WHERE CustomerName = 'Around the Horn' AND ContactName = 'Thomas Hardy'",
                "UPDATE Customers SET CustomerName = 'UpdatedCustomerName', ContactName = 'UpdatedContactName', Address = 'UpdatedAddress', City= 'London', Country = 'UK' WHERE CustomerID = %s",
                "SELECT * FROM Customers WHERE CustomerName = 'UpdatedCustomerName' AND ContactName = 'UpdatedContactName'",
                "UpdatedCustomerName", "UpdatedContactName", 1, "1"} };
    }

    @DataProvider(name = "Test_5")
    public static Object[][] testData5()
    {
        return new Object[][] { { "SELECT * FROM Customers",
                91,
                "DELETE FROM Customers WHERE CustomerID = 2",
                "SELECT * FROM Customers",
                90} };
    }
}