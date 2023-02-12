package com.syncFolder.error;

/**
 Error class has all the com.com.syncFolder.service.error.error raised by the application.
 */
public class Error {
    public String validInput(){
        return "Please Try Again with Valid Input format: sync -s source_folder -d target_folder";
    }
    public String invalidNumberofArguments() {
        return "ERR : INVALID NUMBER OF ARGUMENTS (syncfolders.exe excepts 5 arguments)\n" + validInput();
    }
    public String noCommandFound() {
        return "ERR : NO SUCH COMMAND FOUND => \"%USER_CMD%\"\n" + validInput();
    }
    public String invalidArguments(){
        return "ERR : INVALID ARGUMENTS => \"%USER_CMD%\"\n" + validInput();
    }
    public String invalidFolderName(){
        return "ERR : INVALID FOLDER NAME => \"%FOLDER_NAME%\"";
    }
    public String folderAccessError(){
        return "ERR : NOT ABLE TO OPEN FOLDER => \"%FOLDER_NAME%\" \nPlease check folder name and/or path";
    }
    public String syncingError(){
        return "ERR : ERROR WHILE SYNCING";
    }
    public String undetectedError() {
        return "ERR: UNDETECTED ERROR OCCURRED\n" + validInput();
    }
}



