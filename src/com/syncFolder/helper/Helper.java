package com.syncFolder.helper;

import java.util.Arrays;
import java.util.List;

//Custom classes
import com.syncFolder.error.Error;
/**
 Helper class has all the helper functions needed in the application
 */
public class Helper {
    private Error error;
    public Helper(){
        this.error = new Error();
    }
    /**
     * This method validates and parse the input arguments.
     *
     * @param args arguments to be validated and parsed.
     */
    public String checkAndParseArgs(String[] args){
        List<String> argsArray = Arrays.asList(args);
        String sourceFolder = "", targetFolder = "";
        String inputCommand = String.join(" ",argsArray);

        if(argsArray.size() != 5)
            return error.invalidNumberofArguments();

        if(!argsArray.get(0).equals("sync"))
            return error.noCommandFound().replaceFirst("%USER_CMD%",args[0]);

        if(!argsArray.contains("-s") || !argsArray.contains("-d"))
            return error.invalidArguments().replaceFirst("%USER_CMD%", inputCommand);

        for(int i=1; i < argsArray.size(); i++) {
            if (argsArray.get(i).equals("-s")) {
                i++;
                if (!isValidFolderName(argsArray.get(i)))
                    return error.invalidFolderName().replaceFirst("%FOLDER_NAME%", argsArray.get(i));
                sourceFolder = argsArray.get(i);
            }
            if (argsArray.get(i).equals("-d")) {
                i++;
                if (!isValidFolderName(argsArray.get(i)))
                    return error.invalidFolderName().replaceFirst("%FOLDER_NAME%", argsArray.get(i));
                targetFolder = argsArray.get(i);
            }
        }
        return sourceFolder + "<-d>" + targetFolder;
    }

    /**
     * This method validates any folder name.
     *
     * @param folderName Folder name to be validated.
     * @return true if folder name is valid and does not have any invalid character else returns false
     */
    public Boolean isValidFolderName(String folderName){
        String[] invalidCharacters = {"<",">","|","?","*","\""};
        char firstChar = folderName.charAt(0);

        if(!Character.isAlphabetic(firstChar) && !Character.isDigit(firstChar))
            return false;

        for(String s: invalidCharacters){
            if( folderName.contains(s) )
                return false;
        }
        return true;
    }
}
