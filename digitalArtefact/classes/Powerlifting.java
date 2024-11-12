
package classes;

import java.util.HashMap;

public class Powerlifting extends WorkoutPlan {

    private float maxSquat;
    private float maxBench;
    private float maxDeadlift;
    private float maxCAJ;     //Clean and Jerk

    //HashMap<String, int> --> the int is an arbirtrary number to display how favourable the exercise is
    private HashMap<String, String> muscleGroups;

    //First 3 args for super class, the rest are for this
    public Powerlifting(int numDayForSplit, int numRestDays, int idealLengthInMins, String workoutName) { //, float mS, float mB, float mD, float caj) {
        //Can be initialised separately as it isnt a super important characteristic
        
        //Super Class stuff : WorkoutPlan isnt a non-parameter constructor, so
        // in this constructor you need to get the parent constructor too
        super(numDayForSplit, numRestDays, idealLengthInMins, workoutName);
        
        this.promptPowerliftingConstructor();

        // this.maxSquat = mS;
        // this.maxBench = mB;
        // this.maxDeadlift = mD;
        // this.maxCAJ = caj;
    }
    public void promptPowerliftingConstructor() {
        // Do Something
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