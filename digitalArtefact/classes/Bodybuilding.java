package classes;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.HashMap;
import java.io.File;  // Import the File class
import classes.Validator;
import classes.FileManager;

import interfaces.WorkoutBTS;

public class Bodybuilding extends WorkoutPlan implements WorkoutBTS {
    double bodyFatPercentage;
    double bodyWeight;
    double dailyCalories;
    double dailyProtein;

    public Bodybuilding(int numDays, int numRests, int length, String workoutName, boolean alreadyExists) {
        super(numDays, numRests, length, workoutName);

        this.promptBodybuildingConstructor(alreadyExists);
    }

    public void promptBodybuildingConstructor(boolean alreadyExists) {
        Scanner sc = new Scanner(System.in);
        if (!alreadyExists) {
            this.bodyFatPercentage = Validator.validateDouble(sc, "\nWhat is your Body Fat Percentage?\n");
            this.bodyWeight = Validator.validateDouble(sc, "\nWhat is your Bodyweight in Kilograms?\n");
            this.dailyCalories = Validator.validateDouble(sc, "\nHow many calories can you take in to maintain your bodyweight?\n");
            this.dailyProtein = Validator.validateDouble(sc, "\nHow many grams of protein do you take in per day?\n");
        } else {
            HashMap<String, String> args = FileManager.keyAndVals(new File("workouts/"+this.getNameForFile()+".workout"));

            this.bodyFatPercentage = Double.parseDouble(args.get("bodyFatPercentage"));
            this.bodyWeight = Double.parseDouble(args.get("bodyWeight"));
            this.dailyCalories = Double.parseDouble(args.get("dailyCalories"));
            //Load them in from the file
        }
        //Print a Disclaimer
        System.out.println("\n Important disclaimer: This is to be used as a guide to your workout, rather than a concrete bible workout.\n");
    }
 
    @Override 
    public ArrayList<String> linesToWrite() {
        ArrayList<String> linesToSave = new ArrayList<String>();
        
        linesToSave.add("workoutStyle => Bodybuilding;");
        linesToSave.add("numDays => "+this.getDaySplit()+";");
        linesToSave.add("numRestDays => "+this.getRestDays()+";");
        linesToSave.add("workoutLength => "+this.getIdealLength()+";");
        linesToSave.add("bodyFatPercentage => "+this.bodyFatPercentage+";");
        linesToSave.add("bodyWeight => "+this.bodyWeight+";");
        linesToSave.add("dailyCalories => "+this.dailyCalories+";");
        linesToSave.add("dailyProtein => "+this.dailyProtein+";");

        return linesToSave;
    }

    @Override
    public void displayInformation() {
        System.out.println("\n----------------------\n");
        System.out.println("Current Bodyweight: "+ this.bodyWeight + "Kg");
        System.out.println("Current Bodyfat Percentage: "+ this.bodyFatPercentage + "%");
        System.out.println("Current Maintenance Calories: "+ this.dailyCalories + "Kcal");
        System.out.println("\n----------------------\n");
    }
}
