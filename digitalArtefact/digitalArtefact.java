//All compilation things added to a compile.sh file. In linux: "./compile.sh"
//In Linux: 'javac classes/Profile.java classes/Person.java classes/Company.java' compiles
//        : `javac digitalArtefact.java`
//        : 'java digitalArtefact' - Runs all code, but this is assuming all the files are in the same repo


//Need all
import classes.Misc.*;
import classes.*;
import testers.Tester;

import java.util.*;

//w3schools gave this
import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;

//Dictionary for reading profile files
import java.util.HashMap;

//For testing
import testers.Tester;

public class digitalArtefact {
    //Static means the method belongs to the class and not a specific instance of the class
    //That is why when you use this.[variablename] it will not work if the method is static as it is not a specific instance
 
    //Given a scanner and a prompt, it repeatedly accepts inputs unti a valid integer is input

    //The standard inputs for the construction of a Profile class (includes but not limited to)
    String[] listOfParameters = {"firstName", "surname", "age", "companyName", "jobDescription", "yearsAtCompany", "monthsAtCompany"};

    static FileManager fileManager; // = new FileManager();
    static Validator validator;
    static Tester tester;

    public static String profileExists(String firstName, String surname, String comp) {
        String fullName = (firstName+surname+comp).toLowerCase();

        String returnVal = "";

        File directory = new File("profiles");

        if ( directory.isDirectory() ) {
            File[] allProfiles = directory.listFiles();

            for (File curr : allProfiles) {
                //Assume it is a file
                if ( curr.getName().contains(fullName) ) {
                    //Success
                    returnVal = curr.getName();
                }
            }
        }

        return returnVal;
    }

    //Creates the Profile for the user, or Recreates it when file handling exists
    public static Profile initialiseProfile(Scanner sc) {

        System.out.println("Welcome to the digital artefact. What is your first name?");
        String first = sc.nextLine();
        
        System.out.println("\nWhat is your surname?");
        String sur = sc.nextLine();
    
        System.out.println("\nWhere do you work?");
        String comp = sc.nextLine();

        //Once profiles are getting saved to files, confirm if they have an account if the name exists in a file
        //If it does, confirm their password, pass it through a hash and compare the hash result to the one in the file

        String success = profileExists(first, sur, comp);

        if ( !success.equals("") ) {
            //If it isnt empty, validate whether they have an account already
            String confirm = validator.yesOrNo(sc, "\nDo you already have a profile?");

            if ( confirm.contains("yes") ) {

                String name = success.substring(0, success.length()-8);

                System.out.println("\nLoading profile..");

                return fileManager.recreateProfile(name);
            }
        } 

        int age = validator.validateInt(sc, "\nHow old are you?");
        int years = validator.validateInt(sc, "\nHow many years have you worked at "+comp);
        int months = validator.validateInt(sc, "\nHow many (unconsidered) months have you worked at "+comp);

        System.out.println("\nCreating profile for user "+ first+ " "+ sur+ "... \n");

        // Null is the workout which comes later
        Profile newProfile = new Profile(first, sur, age, comp, years, months);

        return newProfile;
    }

    public static void header() {
        System.out.println("\n\n<==================================================>");
            System.out.println("<================ Digital Artefact ================>");
            System.out.println("<==================================================>\n");
    }   
    
    //The main program flow
    public static void main (String[] args) {
        //Run tester first by simply instanciating the main tester
        Tester testClass = new Tester();

        header();

        Scanner sc = new Scanner(System.in);

        //Main method to create the Profile; NOT DIRECTLY THE CONSTRUCTOR
        Profile currProfile = initialiseProfile(sc);

        //Prompting User to Fully finish their profile
        System.out.println("\nIf this a new profile, we recommend Updating your Job description. If you are interested in working out, set up a Workout Plan for yourself!");

        String continueProgram;

        do {
            currProfile.displayProfileOptions();
            currProfile.promptUserSelection();

            int menuChoice = validator.intInRange(sc, 1, currProfile.getNumMethods()+1); //Saving profile is a DA function, so must be considered seperately
            
            //Check if it is a Class Method option, or saving the file
            if (menuChoice <= currProfile.getNumMethods()) {
                currProfile.chooseCorrectFunction(menuChoice, sc, validator);
            } else {
                System.out.println("\n======================\nSaving Profile");
                boolean success = fileManager.saveProfile(currProfile);
            }

            continueProgram = validator.yesOrNo(sc, "\nDo you wish to continue? (Yes/No)");

        } while (continueProgram.equals("yes"));

        System.out.println("Program Safely Terminating...");
        

        //Not directly a member of the tester class but can confirm if it get saved properly

        boolean success = fileManager.saveProfile(currProfile);

        System.out.println("\n\nProfile save status: " + success);

        if (success) {
            System.out.println("Profile saving successful.");
        } else {
            System.out.println("Profile saving unsuccessful.");
        }


        // THE TESTER FOR SAVING WORKOUTS  
        
        //Then run tests:
        
        // Saving Workouts
        // System.out.println("\n\nRunning Workout Saving Tests...");

        // boolean pl = tester.PowerliftingSaver();
        // boolean bb = tester.BodybuildingSaver();

        // System.out.println(pl + "," + bb);
        
        // if(pl && bb) {
        //     System.out.println("Workout Saving tests successful");
        // } else {
        //     System.out.println("Workout Saving tests unsuccessful");
        // }
    }

}