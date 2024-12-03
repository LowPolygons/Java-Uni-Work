package classes.Misc;

//w3schools gave this
import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import classes.Workouts.*;
import classes.Profile;

public abstract class FileManager {
    
    public static boolean saver(String filePath, ArrayList<String> lines) {
        File file = new File(filePath);
        
        try {
          file.createNewFile();

        } catch (IOException error) {
          System.out.println("There was an error creating the file.");
          return false;
        }

        try {
          FileWriter writer = new FileWriter(filePath);
          
          for (String currLine : lines) {
              writer.write(currLine+"\n");
          }
          writer.close();

        } catch(IOException error) {

          System.out.println("There was an error saving to the file.");

          return false;
        }    

        return true;
    }

    public static boolean saveWorkout(WorkoutPlan saveMe, ArrayList<String> lines){
        String fileName = saveMe.getNameForFile();
        String filePath = "workouts/"+fileName+".workout";

        File file = new File("workouts/"+fileName+".workout");
        
        boolean success = saver(filePath, lines);

        return success;
    }

    public static HashMap<String, String> keyAndVals(File openMe) {
        //To store the results with keys, so that order doesn't matter when reforming the class
        HashMap<String, String> values = new HashMap<>();

        try {
            Scanner fileReader = new Scanner(openMe);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                //System.out.println(line); Works
                String type = line.substring(0, line.indexOf("=>")-1); //WORK FROM HERE NEXT SESSION
                String value = line.substring(line.indexOf("=>")+3, line.length()-1);

                //Now the types and values are known and formatted correctly, add to the hash map
                values.put(type, value);
            }

            fileReader.close();

        } catch (FileNotFoundException error) {
            System.out.println("File not found.");
        }
        
        return values;
    }
    public static WorkoutPlan recreateWorkout(String fileName) {
        File openMe = new File("workouts/"+fileName+".workout");

        //To store the results with keys, so that order doesn't matter when reforming the class
        HashMap<String, String> profileValues = keyAndVals(openMe); 

        for (String key : profileValues.keySet()) {
            System.out.println(key+": "+profileValues.get(key));
        }

        //new Profile(first, sur, age, comp, years, months);
        switch (profileValues.get("workoutStyle") ){
            case "Bodybuilding":
                return new Bodybuilding(
                    Integer.parseInt(profileValues.get("numDays")),
                    Integer.parseInt(profileValues.get("numRestDays")),
                    Integer.parseInt(profileValues.get("workoutLength")),
                    fileName,
                    true
                );       
            case "Powerlifting":
                return new Powerlifting(
                    Integer.parseInt(profileValues.get("numDays")),
                    Integer.parseInt(profileValues.get("numRestDays")),
                    Integer.parseInt(profileValues.get("workoutLength")),
                    fileName,
                    true
                );
            default:
                return new Bodybuilding(0, 0, 0, "FILE_DOES_NOT_EXIST_EXCEPTION", true);
        }
    }

    public static boolean saveProfile(Profile saveMe){

        String fileName = ((saveMe.getPerson()).getNameForFile() + (saveMe.getCompany()).getNameForFile()).toLowerCase();

        String filePath = "profiles/"+fileName+".profile";
        File classInfo = new File("profiles/"+fileName+".profile");
        ArrayList<String> lines = saveMe.linesToWrite();

        boolean success = saver(filePath, lines);
        
        return success;
    }

    public static Profile recreateProfile(String fileName) {
        File openMe = new File("profiles/"+fileName+".profile");

        Profile createdProfile;

        //To store the results with keys, so that order doesn't matter when reforming the class
        HashMap<String, String> profileValues = keyAndVals(openMe); //new HashMap<>();

        // try {
        //     Scanner fileReader = new Scanner(openMe);

        //     while (fileReader.hasNextLine()) {
        //         String line = fileReader.nextLine();
        //         //System.out.println(line); Works
        //         String type = line.substring(0, line.indexOf("=>")-1); //WORK FROM HERE NEXT SESSION
        //         String value = line.substring(line.indexOf("=>")+3, line.length()-1);

        //         //Now the types and values are known and formatted correctly, add to the hash map
        //         profileValues.put(type, value);
        //     }

        //     fileReader.close();

        // } catch (FileNotFoundException error) {
        //     System.out.println("File not found.");
        // }
        

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
