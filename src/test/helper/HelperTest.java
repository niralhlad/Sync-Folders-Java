package test.helper;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

//custom imports
import com.syncFolder.helper.Helper;
import com.syncFolder.error.Error;

/**
 HelperTest class has all the test cases to tes the functions of Helper Class
 */
public class HelperTest {
    Helper helper = new Helper();
    Error error = new Error();

    /**
     * This method is to test correct input with source first and destination second
     */
    @Test
    public void testValidInput() {
        assertEquals("source<-d>destination",helper.checkAndParseArgs("sync -s source -d destination".split(" ")));
    }
    /**
     * This method is to test correct input with source second and destination first
     */
    @Test
    public void testValidInput2() {
        assertEquals("source<-d>destination",helper.checkAndParseArgs("sync -d destination -s source".split(" ")));
    }
    /**
     * This method is to test incorrect number of arguments
     */
    @Test
    public void testInvalidNumberofArgs(){
        assertEquals(error.invalidNumberofArguments() , helper.checkAndParseArgs("sync -s source -d destination error".split( " ")));
    }
    /**
     * This method is to test incorrect command
     */
    @Test
    public void testInvalidCommand(){
        assertEquals(error.noCommandFound().replaceFirst("%USER_CMD%","testCommand") , helper.checkAndParseArgs("testCommand -s source -d destination".split( " ")));
    }
    /**
     * This method is to test with no source argument
     */
    @Test
    public void testNoSourceArgument(){
        assertEquals(error.invalidArguments().replaceFirst("%USER_CMD%","sync -e source -d destination") , helper.checkAndParseArgs("sync -e source -d destination".split( " ")));
    }
    /**
     * This method is to test with no source argument
     */
    @Test
    public void testNoDestinationArgument(){
        assertEquals(error.invalidArguments().replaceFirst("%USER_CMD%","sync -s source -e destination") , helper.checkAndParseArgs("sync -s source -e destination".split( " ")));
    }
    /**
     * This method is to test folder name containing invalid characters
     */
    @Test
    public void testInvalidFolderName(){
        assertEquals(error.invalidFolderName().replaceFirst("%FOLDER_NAME%","sou<rce") , helper.checkAndParseArgs("sync -s sou<rce -d destination".split( " ")));
    }
    /**
     * This method is to test folder name containing invalid characters
     */
    @Test
    public void testInvalidFolderName2(){
        assertEquals(error.invalidFolderName().replaceFirst("%FOLDER_NAME%","{source") , helper.checkAndParseArgs("sync -s {source -d destination".split( " ")));
    }
}
