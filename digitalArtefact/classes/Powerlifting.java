
package classes;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;  // Import the File class
import classes.Validator;
import classes.FileManager;

import interfaces.WorkoutBTS;

//All the nitty gritty of the workouts is done. Next step on this is just functionality
public class Powerlifting extends WorkoutPlan implements WorkoutBTS {

    private double maxSquat;
    private double maxBench;
    private double maxDeadlift;
    private double maxCAJ;     //Clean-And-Jerk

    //First 3 args for super class, the rest are for this
    public Powerlifting(int numDayForSplit, int numRestDays, int idealLengthInMins, String workoutName, boolean alreadyExists) { //, float mS, float mB, float mD, float caj) {
        super(numDayForSplit, numRestDays, idealLengthInMins, workoutName);
        
        this.promptPowerliftingConstructor(alreadyExists);
    }
    public void promptPowerliftingConstructor(boolean alreadyExists) {
        Scanner sc = new Scanner(System.in);
        if (!alreadyExists) {
            this.maxSquat = Validator.validateDouble(sc, "\nWhat is your Max Squat?\n");
            this.maxBench = Validator.validateDouble(sc, "\nWhat is your Max Benchpress?\n");
            this.maxDeadlift = Validator.validateDouble(sc, "\nWhat is your Max Deadlift?\n");
            this.maxCAJ = Validator.validateDouble(sc, "\nWhat is your Max Clean and Jerk?\n");
        } else {
            HashMap<String, String> args = FileManager.keyAndVals(new File("workouts/"+this.getNameForFile()+".workout"));

            this.maxSquat = Double.parseDouble(args.get("maxSquat"));
            this.maxBench = Double.parseDouble(args.get("maxBench"));
            this.maxDeadlift = Double.parseDouble(args.get("maxDeadlift"));
            this.maxCAJ = Double.parseDouble(args.get("maxCAJ"));
            //Load them in from the file
        }
        //Print a Disclaimer
        System.out.println("\n Important disclaimer: This is to be used as a guide to your workout, rather than a concrete bible workout.\n");
    }

    //Adding ----s to separate termimal lines, makes it prettier/more readable

    //Override the interface methods
    @Override
    public void displayInformation() {
        System.out.println("--------------------------------");

        System.out.println("Your Max Benchpress: " + this.maxBench + "Kg");
        System.out.println("Your Max Squat: " + this.maxSquat + "Kg");
        System.out.println("Your Max Deadlift: " + this.maxDeadlift + "Kg");
        System.out.println("Your Max Clean-And-Jerk: " + this.maxCAJ + "Kg");

        System.out.println("--------------------------------");
    }

    @Override
    public ArrayList<String> linesToWrite() {
        ArrayList<String> linesToSave = new ArrayList<String>();
        linesToSave.add("workoutStyle => Powerlifting;");
        linesToSave.add("maxSquat => "+this.maxSquat+";");
        linesToSave.add("maxBench => "+this.maxBench+";");
        linesToSave.add("maxDeadlift => "+this.maxDeadlift+";");
        linesToSave.add("maxCAJ => "+this.maxCAJ+";");
        linesToSave.add("numDays => "+this.getDaySplit()+";");
        linesToSave.add("numRestDays => "+this.getRestDays()+";");
        linesToSave.add("workoutLength => "+this.getIdealLength()+";");

        return linesToSave;
    }


}
