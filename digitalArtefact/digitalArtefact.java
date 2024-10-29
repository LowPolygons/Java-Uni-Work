//All compilation things added to a compile.sh file. In linux: "./compile.sh"
//In Linux: 'javac classes/Profile.java classes/Person.java classes/Company.java' compiles
//        : `javac digitalArtefact.java`
//        : 'java digitalArtefact' - Runs all code, but this is assuming all the files are in the same repo


import classes.*;
import java.util.*;

//w3schools gave this
import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;

//Dictionary for reading profile files
import java.util.HashMap;

public class digitalArtefact {
    //Static means the method belongs to the class and not a specific instance of the class
    //That is why when you use this.[variablename] it will not work if the method is static as it is not a specific instance
 
    //Given a scanner and a prompt, it repeatedly accepts inputs unti a valid integer is input

    //The standard inputs for the construction of a Profile class (includes but not limited to)
    String[] listOfParameters = {"firstName", "surname", "age", "companyName", "jobDescription", "yearsAtCompany", "monthsAtCompany"};

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

        //Write to file
        try {
            // Line format is:
            // :parameter => value;
            // This will make searching and parsing easy
            
            //Needs to be done in the try
            FileWriter writeToProfile = new FileWriter("profiles/"+fileName+".profile");

            String[] lines = saveMe.linesToWrite();

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
                String type = line.substring(line.indexOf(":=:")+3, line.indexOf("=>")-1); //WORK FROM HERE NEXT SESSION
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
    //A validation function until a yes or no is input
    public static String yesOrNo(Scanner sc, String msg) {
        String temp_input = "";

        while ( !(temp_input.equals("yes") || temp_input.equals("no")) ) {
            System.out.println(msg);
            temp_input = sc.nextLine().toLowerCase();
        }

        return temp_input.toLowerCase();
    }

    //A validation function until an integer is put in
    public static int validateInt(Scanner sc, String prompt) {
        //scanner.hasNextInt() checks if the next input is a valid integer
        System.out.println(prompt);
        int output = 0;
        boolean valid = false;

        while (!valid) {
            if (sc.hasNextInt()) {
                valid = true;
                output = sc.nextInt();
            } else {
                System.out.println("Invalid input. Try again: ");
                sc.next(); //Skip over the given input
            }
        }

        return output;
    }

    //Redundant code here from the validateInt function, but until an integer within a range is put in
    public static int intInRange(Scanner sc, int lowerBound, int upperBound) {
        int output = 0;
        boolean valid = false;
        
        while (!valid) {
            if (sc.hasNextInt()) {
                valid = true;
                output = sc.nextInt();
                if ( !(output >= lowerBound && output <= upperBound) )
                    valid = false;
            } 
            if (!valid) {
                System.out.println("Invalid input. Try again: ");
            }
            
            sc.nextLine(); //Skip over the given input
        }

        return output;
    }

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
        
        System.out.println("What is your surname?");
        String sur = sc.nextLine();
    
        System.out.println("Where do you work?");
        String comp = sc.nextLine();

        //Once profiles are getting saved to files, confirm if they have an account if the name exists in a file
        //If it does, confirm their password, pass it through a hash and compare the hash result to the one in the file

        String success = profileExists(first, sur, comp);

        if ( !success.equals("") ) {
            //If it isnt empty, validate whether they have an account already
            String confirm = yesOrNo(sc, "Do you already have a profile?");

            if ( confirm.contains("yes") ) {

                String name = success.substring(0, success.length()-8);

                System.out.println("Loading profile..");

                return recreateProfile(name);
            }
        } 

        int age = validateInt(sc, "How old are you?");

        int years = validateInt(sc, "How many years have you worked at "+comp);
        int months = validateInt(sc, "How many leftover months have you worked at "+comp);

        System.out.println("Creating profile for user "+ first+ " "+ sur+ "... \n");

        Profile newProfile = new Profile(first, sur, age, comp, years, months);

        return newProfile;
    }

    //The main program flow
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        //Main method to create the Profile; NOT DIRECTLY THE CONSTRUCTOR
        Profile currProfile = initialiseProfile(sc);

        //Display the user options
        currProfile.displayProfileOptions();
        String continueProgram;

        do {
            currProfile.promptUserSelection();

            int menuChoice = intInRange(sc, 1, currProfile.getNumMethods() ); //sc.nextInt();

            System.out.println("You chose option: "+menuChoice);

            currProfile.chooseCorrectFunction(menuChoice);

            continueProgram = yesOrNo(sc, "\nDo you wish to continue? (Yes/No)");

        } while (continueProgram.equals("yes"));

        System.out.println("Program Safely Terminating...");
        
        boolean success = saveProfile(currProfile);
    }

}