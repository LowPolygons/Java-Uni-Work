package testers.Logging;

import java.util.ArrayList;

import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class Logger {
    
    private ArrayList<String> logLines;

    public Logger() {
        this.validateLogFile();
    }

    //Self explanatory
    public void writeToLog(ArrayList<String> inputs) {
        File logFile = new File("log.txt");
        try {
            FileWriter writer = new FileWriter("log.txt");

            for (String curr : inputs) {
                writer.write(curr);
            }
        } catch (IOException err) {
            System.out.println("There was an issue writing to the log file.");
        }
    }

    //Checks if a log file exists, if it does it deletes it and then initialises a new one
    public void validateLogFile() {
        File logFile = new File("log.txt");
        
        if ( logFile.exists() ) {
            logFile.delete();
        }

        try {
            logFile.createNewFile();

            FileWriter writer = new FileWriter("log.txt");

            writer.write("[INFO] Initialised Log File");

        } catch (IOException err) {
            System.out.println("There was an issue writing to the log file.");
        }
    }
}