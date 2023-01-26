package AccelByte;

import java.io.*;

//Custom classes
import AccelByte.error.Error;
import AccelByte.helper.Helper;
import AccelByte.service.SyncService;

/**
 * This class holds the main method that is the entry point of the application.
 *
 */
public class Main {
    /**
     * This main method will start and drive the application.
     *
     * @param args arguments required in the application.
     * @output Respective Error in case of any com.com.error.error or nothing in case of success.
     * @throws Exception
     */
    public static void main(String[] args) throws  Exception{
        Error error = new Error();
        try{
            Helper helperObj = new Helper();
            SyncService sync = new SyncService();
            String checkAndParseArgsResult = helperObj.checkAndParseArgs(args);

            if( checkAndParseArgsResult.contains("ERR") ){
                System.out.println(checkAndParseArgsResult);
                System.exit(0);
            }

            String[] folders = checkAndParseArgsResult.split("<-d>");
            String sourceFolder = folders[0], targetFolder = folders[1];

            File source = new File(sourceFolder);
            if(!source.exists()) {
                System.out.println(error.folderAccessError().replaceFirst("%FOLDER_NAME%", sourceFolder));
                System.exit(0);
            }

            File destination = new File(targetFolder);

            if(!destination.exists()) {
                System.out.println(error.folderAccessError().replaceFirst("%FOLDER_NAME%", targetFolder));
                System.exit(0);
            }
            String syncResult = sync.syncFolders(source,destination);

            if(syncResult.contains("ERR"))
                System.out.println(syncResult);
        }
        catch (Exception e){
            error.undetectedError();
        }
    }
}