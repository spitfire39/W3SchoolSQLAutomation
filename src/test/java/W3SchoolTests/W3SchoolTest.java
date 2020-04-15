package W3SchoolTests;

import Pages.W3SchoolPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class W3SchoolTest {
    W3SchoolPage page = new W3SchoolPage();

    @BeforeSuite
    public void openW3SchoolPage(){
        page.openPage();
    }

    @BeforeMethod
    public void restoreDatabase(){
        page.restoreDatabase();
    }

    @Test
    public void getAllRecordsFromCustomerTableAndCheckContactAddress(){
        page.setAndSubmitSelectQuery("SELECT * FROM Customers");
        Assert.assertTrue(page.checkForRowPresenceBySeveralAttributes("Giovanni Rovelli", "Via Ludovico il Moro 22"));
    }

    @Test
    public void countRowsByCityValue(){
        page.setAndSubmitSelectQuery("SELECT * FROM Customers WHERE City = 'London'");
        Assert.assertEquals(page.getTableRowsCount(), 6);
        Assert.assertEquals(page.getNumberOfRecords(), "6");
    }

    @Test
    public void addNewRecordAndCheckItsPresence(){
        page.setAndSubmitUpdateQuery("INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) " +
                "VALUES ('TestCustomer', 'TestContactName', 'TestStreet', 'London', '5555', 'UK')");
        page.setAndSubmitSelectQuery("SELECT * FROM Customers " +
                "WHERE CustomerName = 'TestCustomer' AND ContactName = 'TestContactName'");
        Assert.assertEquals(page.getTableRowsCount(), 1);
        Assert.assertEquals(page.getNumberOfRecords(), "1");
    }

    @Test
    public void updateAllRecordFields(){
        page.setAndSubmitSelectQuery("SELECT CustomerID FROM Customers " +
                "WHERE CustomerName = 'Around the Horn' AND ContactName = 'Thomas Hardy'");
        String recordId = page.getTextValueFromFirstRecord();
        page.setAndSubmitUpdateQuery(String.format("UPDATE Customers " +
                "SET CustomerName = 'UpdatedCustomerName', ContactName = 'UpdatedContactName', Address = 'UpdatedAddress', City= 'London', Country = 'UK' " +
                "WHERE CustomerID = %s", recordId));
        page.setAndSubmitSelectQuery("SELECT * FROM Customers " +
                "WHERE CustomerName = 'UpdatedCustomerName' AND ContactName = 'UpdatedContactName'");
        Assert.assertTrue(page.checkForRowPresenceBySeveralAttributes("UpdatedCustomerName", "UpdatedContactName"));
        Assert.assertEquals(page.getTableRowsCount(), 1);
        Assert.assertEquals(page.getNumberOfRecords(), "1");
    }

    @Test
    public void deleteRecordFromTable(){
        page.setAndSubmitSelectQuery("SELECT * FROM Customers");
        Assert.assertEquals(page.getTableRowsCount(), 91);
        page.setAndSubmitUpdateQuery("DELETE FROM Customers WHERE CustomerID = 2");
        page.setAndSubmitSelectQuery("SELECT * FROM Customers");
        Assert.assertEquals(page.getTableRowsCount(), 90);
    }
}