package testers.Misc;

import classes.Misc.FileManager;
import interfaces.testers.TesterBTS;
import testers.Logging.Logger;

import classes.Workouts.*;
import classes.Profile;

import java.util.ArrayList;

import java.io.File; 

public class T_FileManager implements TesterBTS {

    // static FileManager fileMan;
    public T_FileManager() {};

    private ArrayList<String> linesToWrite = new ArrayList<>();

    @Override
    public ArrayList<String> testPreparation() {
        ArrayList<String> returnVal = new ArrayList<>();
        //These can be hard coded because there is no validation done on these (They would have already been validated when creating the class properly)

        returnVal.add("239");
        returnVal.add("2387042");
        returnVal.add("-458");
        returnVal.add("4872903");
        returnVal.add("-28");
        returnVal.add("459");

        return returnVal;
    }

    @Override
    public void runTest(ArrayList<String> inputs) {
       //Need to test saveWorkout, implicitly tests saver as well

       //These ensure save workout and saver works for both types of workouts
       boolean[] results = new boolean[5];

       results[0] = this.PowerliftingSaver(Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)), Integer.parseInt(inputs.get(2)), inputs.get(3));
       results[1] = this.BodybuildingSaver(Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)), Integer.parseInt(inputs.get(2)), inputs.get(3));
       results[2] = this.ProfileSaverTest(inputs.get(0), inputs.get(1), Integer.parseInt(inputs.get(2)), inputs.get(3), Integer.parseInt(inputs.get(4)), Integer.parseInt(inputs.get(5)) );
       results[3] = this.loadWorkoutTest(inputs);
       results[4] = this.loadProfileTest(inputs);

       int numSuccessful = 0;

       this.linesToWrite.add("\n[T_FileManager test : Five in Total]");
       
       for (boolean curr : results) {
           if (curr) {
               numSuccessful++;
               this.linesToWrite.add("Passed");
           } else {
               this.linesToWrite.add("Failed");
           }
       }

       this.linesToWrite.add(numSuccessful+" out of 5 Passed\n");
    }
    
    @Override
    public Logger feedbackTest(Logger logger) {
        logger.addToLogLines(this.linesToWrite);

        return logger;
    }
    public boolean loadWorkoutTest(ArrayList<String> inputs) {
        //Load the workout that was just saved. You know what the name should be
        //the 4th parameter is the name of the workout, so check if a file exists equal to the 4th item
        File testFile = new File("workouts/"+inputs.get(3)+".workout");

        if (!testFile.exists()) {
            //Didnt save with the correct name
            return false;
        }
        WorkoutPlan test = FileManager.recreateWorkout(inputs.get(3));
        
        //Now check if the day split, rest day and ideal length variables match the inputs
        if (!Integer.toString(test.getDaySplit()).equals(inputs.get(0)))
            return false;
        
        if (!Integer.toString(test.getRestDays()).equals(inputs.get(1)))
            return false;

        if (!Integer.toString(test.getIdealLength()).equals(inputs.get(2)))
            return false;
        
        //If it reaches here, teh workout was created properly
        return true;
    }

    public boolean loadProfileTest(ArrayList<String> inputs) {
        File testFile = new File("profiles/"+inputs.get(0)+inputs.get(1)+inputs.get(3)+".profile");

        if (!testFile.exists()) {
            System.out.println("File could not be found.");
            return false;
        }

        Profile testProfile = FileManager.recreateProfile(inputs.get(0)+inputs.get(1)+inputs.get(3));

        //Now check the linesToWrite and compare
        ArrayList<String> lines = testProfile.linesToWrite();

        //Just go one at a time
        if (lines.get(0).indexOf(inputs.get(0)) == -1) {
            System.out.println("0 Failed");
            return false; 
        }
        if (lines.get(1).indexOf(inputs.get(1)) == -1) {
            System.out.println("1 Failed");
            return false; 
        }
        if (lines.get(2).indexOf(inputs.get(2)) == -1) {
            System.out.println("2 Failed");
            return false; 
        }
        if (lines.get(3).indexOf(inputs.get(3)) == -1) {
            System.out.println("3 Failed");
            return false; 
        }
            //Skip 4 as job description is optional
        if (lines.get(5).indexOf(inputs.get(4)) == -1) {
            System.out.println("5 Failed");
            return false; 
        }
        if (lines.get(6).indexOf(inputs.get(5)) == -1) {
            System.out.println("6 Failed");
            return false; 
        }
        
        //If it reaches here, test passed
        return true;
    }
    public boolean PowerliftingSaver(int a, int b, int c, String d) {
        // For saving a file it doesn't matter the data as the validators were tested already
 
        WorkoutPlan test = new Powerlifting(a, b, c, d, false);

        ArrayList<String> lines = test.linesToWrite();

        boolean success = FileManager.saveWorkout(test, lines);

        return success;
    }

    public boolean BodybuildingSaver(int a, int b, int c, String d) {

        //Powerlifting(int numDayForSplit, int numRestDays, int idealLengthInMins, String workoutName) { //, float mS, float mB, float mD, float caj) {
        WorkoutPlan test = new Bodybuilding(a, b, c, d, false);

        ArrayList<String> lines = test.linesToWrite();

        boolean success = FileManager.saveWorkout(test, lines);

        return success;
    }

    public boolean ProfileSaverTest(String a, String b, int c, String d, int e, int f) {

        Profile tempProfile = new Profile(a, b, c, d, e, f);

        boolean success = FileManager.saveProfile(tempProfile);

        return success;
    }
}