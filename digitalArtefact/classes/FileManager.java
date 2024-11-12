package classes;

//w3schools gave this
import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class FileManager {
    
    public static boolean saveProfile(Profile saveMe){

        String fileName = ((saveMe.getPerson()).getNameForFile() + (saveMe.getCompany()).getNameForFile()).toLowerCase();

        File classInfo = new File("profiles/"+fileName+".profile");


        //First try catch will create the file if it doesnt exist already
        //createNewFile returns a boolean whether the file has been created or not: if it hasn't been created, then it already exists
        try {
            classInfo.createNewFile();
        } catch (IOException error) {
            System.out.println("There was an error creating the file.");
            return false;
        }
        
        System.out.println("\nSaving profile to file "+fileName+".profile");

        //Write to file
        try {
            // Line format is:
            // parameter => value;
            // This will make searching and parsing easy
            
            //Needs to be done in the try
            FileWriter writeToProfile = new FileWriter("profiles/"+fileName+".profile");

            ArrayList<String> lines = saveMe.linesToWrite();

            //Didn't know java had this until now
            for (String currLine : lines) {
                writeToProfile.write(currLine+"\n");
            }
            writeToProfile.close();    

        } catch (IOException error) {
            System.out.println("There was an error saving the file");

            return false;
        }

        return true;
    }

    public static Profile recreateProfile(String fileName) {
        File openMe = new File("profiles/"+fileName+".profile");

        Profile createdProfile;

        //To store the results with keys, so that order doesn't matter when reforming the class
        HashMap<String, String> profileValues = new HashMap<>();

        try {
            Scanner fileReader = new Scanner(openMe);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                //System.out.println(line); Works
                String type = line.substring(0, line.indexOf("=>")-1); //WORK FROM HERE NEXT SESSION
                String value = line.substring(line.indexOf("=>")+3, line.length()-1);

                //Now the types and values are known and formatted correctly, add to the hash map
                profileValues.put(type, value);
            }

            fileReader.close();

        } catch (FileNotFoundException error) {
            System.out.println("File not found.");
        }
        

        //new Profile(first, sur, age, comp, years, months);

        createdProfile = new Profile(
            profileValues.get("firstName"),
            profileValues.get("surname"),
            Integer.parseInt(profileValues.get("age")),
            profileValues.get("companyName"),
            Integer.parseInt(profileValues.get("yearsAtCompany")),
            Integer.parseInt(profileValues.get("monthsAtCompany"))
        );

        createdProfile.loadCompDescription(profileValues.get("jobDescription"));
        
        return createdProfile;
    }
}