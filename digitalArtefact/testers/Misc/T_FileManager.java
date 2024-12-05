package testers.Misc;

import classes.Misc.FileManager;
import interfaces.testers.TesterBTS;
import testers.Logging.Logger;

import classes.Workouts.*;
import classes.Profile;

import java.util.ArrayList;

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
       boolean[] results = new boolean[3];

       results[0] = this.PowerliftingSaver(Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)), Integer.parseInt(inputs.get(2)), inputs.get(3));
       results[1] = this.BodybuildingSaver(Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)), Integer.parseInt(inputs.get(2)), inputs.get(3));
       results[2] = this.ProfileSaverTest(inputs.get(0), inputs.get(1), Integer.parseInt(inputs.get(2)), inputs.get(3), Integer.parseInt(inputs.get(4)), Integer.parseInt(inputs.get(5)) );

       int numSuccessful = 0;

       this.linesToWrite.add("\n[T_FileManager test : Three in Total]");
       
       for (boolean curr : results) {
           if (curr) {
               numSuccessful++;
               this.linesToWrite.add("Passed");
           } else {
               this.linesToWrite.add("Failed");
           }
       }

       this.linesToWrite.add(numSuccessful+" out of 3 Passed\n");
    }
    
    @Override
    public Logger feedbackTest(Logger logger) {
        logger.addToLogLines(this.linesToWrite);

        return logger;
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