package W3SchoolTests;

import Pages.W3SchoolPage;
import W3SchoolTests.Data.StaticProvider;
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

    @Test(dataProvider = "Test_1", dataProviderClass = StaticProvider.class)
    public void getAllRecordsFromCustomerTableAndCheckContactAddress(String query, String contactName, String address){
        page.setAndSubmitSelectQuery(query);
        Assert.assertTrue(page.checkForRowPresenceBySeveralAttributes(contactName, address));
    }

    @Test(dataProvider = "Test_2", dataProviderClass = StaticProvider.class)
    public void countRowsByCityValue(String query, int recordNum, String recordNumStr){
        page.setAndSubmitSelectQuery(query);
        Assert.assertEquals(page.getTableRowsCount(), recordNum);
        Assert.assertEquals(page.getNumberOfRecords(), recordNumStr);
    }

    @Test(dataProvider = "Test_3", dataProviderClass = StaticProvider.class)
    public void addNewRecordAndCheckItsPresence(String queryInsert, String querySelect, int recordNum, String recordNumStr){
        page.setAndSubmitUpdateQuery(queryInsert);
        page.setAndSubmitSelectQuery(querySelect);
        Assert.assertEquals(page.getTableRowsCount(), recordNum);
        Assert.assertEquals(page.getNumberOfRecords(), recordNumStr);
    }

    @Test(dataProvider = "Test_4", dataProviderClass = StaticProvider.class)
    public void updateAllRecordFields(String querySelect1, String queryUpdate, String querySelect2, String key, String value, int recordNum, String recordNumStr){
        page.setAndSubmitSelectQuery(querySelect1);
        String recordId = page.getTextValueFromFirstRecord();
        page.setAndSubmitUpdateQuery(String.format(queryUpdate, recordId));
        page.setAndSubmitSelectQuery(querySelect2);
        Assert.assertTrue(page.checkForRowPresenceBySeveralAttributes(key, value));
        Assert.assertEquals(page.getTableRowsCount(), recordNum);
        Assert.assertEquals(page.getNumberOfRecords(), recordNumStr);
    }

    @Test(dataProvider = "Test_5", dataProviderClass = StaticProvider.class)
    public void deleteRecordFromTable(String querySelect1, int recordNum1, String queryDelete, String querySelect2, int recordNum2){
        page.setAndSubmitSelectQuery(querySelect1);
        Assert.assertEquals(page.getTableRowsCount(), recordNum1);
        page.setAndSubmitUpdateQuery(queryDelete);
        page.setAndSubmitSelectQuery(querySelect2);
        Assert.assertEquals(page.getTableRowsCount(), recordNum2);
    }
}