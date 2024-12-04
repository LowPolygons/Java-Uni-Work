package testers.Logging;

import java.util.ArrayList;

import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class Logger {
    
    private ArrayList<String> logLines = new ArrayList<>();

    public Logger() {
        this.validateLogFile();
    }

    //Self explanatory
    public void writeToLog() {
        File logFile = new File("log.txt");
        try {
            FileWriter writer = new FileWriter("log.txt");

            for (String curr : this.logLines) {
                writer.write(curr+"\n");
            }

            writer.close();
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

            writer.write("Initialised Log File - This message will disappear upon testing output");     

            writer.close();
        } catch (IOException err) {
            System.out.println("There was an issue writing to the log file.");
        }
    }

    //This object is passed around and only every instantiated once so you can have all seperate classes all add to one array
    public void addToLogLines(ArrayList<String> lines) {
        for (String curr : lines) {
            this.logLines.add(curr);
        }
    }
}