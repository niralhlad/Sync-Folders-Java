package com.syncFolder.service;

import com.syncFolder.error.Error;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 FolderService class is a class that contains the logic for copying and deleted files between source and destination folder.
 */
public class SyncService {
    private Error error;

    public SyncService(){
        this.error = new Error();
    }

    /**
     * This method sync destination folder with source folder.
     * If destination folder is not present this function will create a new one with the new given destination name.
     * NOTE: This would not happen when we run the Main function.
     *
     * @param File sourceFolder Source Folder Path from where the sync needs to be done.
     * @param File destinationFolder Destination Folder Path where sync would be performed.
     * @return String ERR with specific details in case of any com.com.syncFolder.service.error.error.
     *
     */
    public String syncFolders(File sourceFolder, File destinationFolder) throws IOException {

        //Check if source directory is a file or a folder.
        if (sourceFolder.isDirectory()) {
            //Check if destination directory exist or not.
            //If not then create one.
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
            }

            List destinationList = new ArrayList<String>(List.of(destinationFolder.list()));

            //Sync all the files in that child folder
            for (String children : sourceFolder.list()) {
                File source = new File(sourceFolder, children);

                //check if source file is directory or file.
                if( source.isDirectory() ) {
                    if( destinationList.contains(children) )
                        destinationList.remove(children);
                    syncFolders(source, new File(destinationFolder, children));
                }
                else{
                    if( destinationList.contains(children) )
                       destinationList.remove(children);
                    else{
                        syncFolders( source, new File(destinationFolder, children) );
                    }
                }
            }

            //Remove all the removing files in the destination folder.
            for(Object child : destinationList){
                new File(destinationFolder,child.toString()).delete();
            }
        }
        else {
            InputStream in;
            OutputStream out;

            try{
                in = new FileInputStream(sourceFolder);
            }
            catch (Exception e){
                return error.folderAccessError().replaceFirst("%FOLDER_NAME%",sourceFolder.toString());
            }

            try{
                out = new FileOutputStream(destinationFolder);
            }
            catch (Exception e){
                in.close();
                return error.folderAccessError().replaceFirst("%FOLDER_NAME%",destinationFolder.toString());
            }

            try{
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            catch (Exception e){
                return error.syncingError();
            }
            finally {
                in.close();
                out.close();
            }
        }
        return "Success";
    }


}
