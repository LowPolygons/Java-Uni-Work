
package classes;

import java.util.HashMap;

public class Powerlifting extends WorkoutPlan {

    private float maxSquat;
    private float maxBench;
    private float maxDeadlift;
    //Clean and Jerk
    private float maxCAJ;

    //HashMap<String, int> --> the int is an arbirtrary number to display how favourable the exercise is
    private HashMap<String, String> muscleGroups;

    public Powerlifting(int numDayForSplit, int numRestDays, int idealLengthInMins, float mS, float mB, float mD, float caj) {
        //Can be initialised separately as it isnt a super important characteristic
        
        //Super Class stuff
        super(numDayForSplit, numRestDays, idealLengthInMins);

        this.loadMuscleFavours(false, null);

        this.maxSquat = mS;
        this.maxBench = mB;
        this.maxDeadlift = mD;
        this.maxCAJ = caj;
    }

    //Sets the favours
    public void loadMuscleFavours(boolean hasFavours, String[] favours) {

        this.muscleGroups = new HashMap<>();

        String[] mgs = {
                "Chest", 
                "Shoulders/Delts", 
                "Triceps", 
                "Biceps", 
                "Traps", 
                "Lats", 
                "Quads", 
                "Hamstrings", 
                "Calfs"
            };

        if (hasFavours) {
            //Assume the favours will be in order
            for (int i = 0; i < mgs.length; i++) { 
                this.muscleGroups.put(mgs[i], favours[i]);
            }
        } else {
            for (String currMuscle : mgs) {
                this.muscleGroups.put(currMuscle, "1");
            }
        }
    }

    //Adding ----s to separate termimal lines, makes it prettier/more readable
    public void displaySBD() {
        System.out.println("--------------------------------");

        System.out.println("Your Max Benchpress: " + this.maxBench + "Kg");
        System.out.println("Your Max Squat: " + this.maxSquat + "Kg");
        System.out.println("Your Max Deadlift: " + this.maxDeadlift + "Kg");
        System.out.println("Your Max Clean-And-Jerk: " + this.maxCAJ + "Kg");

        System.out.println("--------------------------------");
    }
}