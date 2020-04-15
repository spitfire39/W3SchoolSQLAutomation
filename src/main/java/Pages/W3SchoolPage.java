package Pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class W3SchoolPage extends BasePage{
    // This method inserts value into SQL Statement field
    // +query+ should contain sql query without ';', for example, SELECT * FROM Customers
    public W3SchoolPage setQuery(String query){
        String jsFunction = String.format("window.editor.getDoc().setValue(\"%s;\");", query);
        executeJavaScript(jsFunction);
        return this;
    }

    // This method supposed to executes SQL SELECT request and waits for
    // Analog of click on 'Run SQL' button
    public W3SchoolPage submitQuery(){
        executeJavaScript("w3schoolsSQLSubmit();");
        return this;
    }

    // This method waits for table with query output to be visible
    public W3SchoolPage checkForTablePresence(){
        $(".w3-table-all").shouldBe(Condition.appears);
        return this;
    }

    // This method waits for success message after query updated/inserted new row
    public W3SchoolPage checkForSuccessMessagePresence(String messageTest){
//        $("#divResultSQL div[style='padding:10px;']").shouldHave(Condition.text(messageTest));
        $("#divResultSQL div").shouldHave(Condition.text(messageTest));
        return this;
    }

    // This method joins several methods in one peace to run SELECT query
    public void setAndSubmitSelectQuery(String query){
        setQuery(query);
        submitQuery();
        checkForTablePresence();
    }

    // This method joins several methods in one peace to run INSERT and UPDATE queries
    public void setAndSubmitUpdateQuery(String query){
        setQuery(query);
        submitQuery();
        checkForSuccessMessagePresence("You have made changes to the database. Rows affected: 1");
    }

    // This method checks for row presence by value and that row contains key, for example:
    // +key+ - contact name
    // +value+ - contact address
    // NOTE: This method works only if key and value columns are displayed
    public boolean checkForRowPresenceBySeveralAttributes(String key, String value){
        if ($$(".w3-table-all tr").filter(Condition.text(key)).toString().contains(value)){
            return true;
        }
        return false;
    }

    // This method gets number of rows inside of output table, excluding -1 as header
    public int getTableRowsCount(){
        return $$(".w3-table-all tbody tr").size() - 1;
    }

    // This method gets number from field 'Number of Records: '
    public String getNumberOfRecords(){
        String string = "Number of Records: ";
        return $("[style='margin-bottom:10px;']").text().replace(string, "");
    }

    // This method gets value of the first column in first row
    // Recommended to use for output for single record with
    public String getTextValueFromFirstRecord(){
        return $(".w3-table-all tr td").text();
    }

    // This method restores database
    // NOTE: Recommended to use before each new test in order to avoid data duplication
    public W3SchoolPage restoreDatabase(){
        $("#restoreDBBtn").click();
        switchTo().alert().accept();
        checkForSuccessMessagePresence("The database is fully restored.");
        return this;
    }
}