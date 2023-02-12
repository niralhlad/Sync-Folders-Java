package test.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

//custom imports
import com.syncFolder.service.SyncService;
import com.syncFolder.error.Error;

import java.io.File;
import java.io.IOException;

/**
SyncServiceTest class has all the test cases to tes the functions of SyncService Class
 */
public class SyncServiceTest {
    SyncService sync = new SyncService();
    Error error = new Error();

    /**
     * This method is to test if syncFolders is success or not.
     */
    @Test
    public void testValidInput() throws IOException {
        assertEquals("Success", sync.syncFolders( new File("source_dir"),new File("destination_dir")) );
    }
    /**
     * This method is to test invalid source folder name
     */
    @Test
    public void testInValidSource() throws IOException {
        assertEquals(error.folderAccessError().replaceFirst("%FOLDER_NAME%","source"), sync.syncFolders( new File("source"),new File("destination_dir")) );
    }
    /**
     * This method is to test invalid destination folder name
     */
    @Test
    public void testInValidDestination() throws IOException {
        assertEquals("Success", sync.syncFolders( new File("source_dir"),new File("destination")) );
    }
}
