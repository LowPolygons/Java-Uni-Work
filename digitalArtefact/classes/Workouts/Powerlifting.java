
package classes.Workouts;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;  // Import the File class

import classes.Misc.Validator;
import classes.Misc.FileManager;

import interfaces.Workouts.WorkoutBTS;

//All the nitty gritty of the workouts is done. Next step on this is just functionality
public class Powerlifting extends WorkoutPlan implements WorkoutBTS {

    private double maxSquat;
    private double maxBench;
    private double maxDeadlift;
    private double maxCAJ;     //Clean-And-Jerk

    //First 3 args for super class, the rest are for this
    public Powerlifting(int numDayForSplit, int numRestDays, int idealLengthInMins, String workoutName, boolean alreadyExists) { //, float mS, float mB, float mD, float caj) {
        super(numDayForSplit, numRestDays, idealLengthInMins, workoutName);
        
        this.promptLocalConstructor(alreadyExists);

        this.WorkoutInstance();
    }

    @Override
    public void promptLocalConstructor(boolean alreadyExists) {
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
        System.out.println("\nImportant disclaimer: This is to be used as a guide to your workout, rather than a concrete bible workout.\n");

        //Enter the Workout Running instance
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


    @Override
    public void perWorkoutFunc(Scanner sc) {
        //Hardcoded because this is like an embedded system
        System.out.println("\nWarning: This type of training program is for the more advanced who have gym experience, and will focus majorly on the 'PR' lifts.");

        System.out.println("\nOptions: \n");
        System.out.println("1. Display Current PRs");
        System.out.println("2. Max Squat Work");
        System.out.println("3. Max Bench Work");
        System.out.println("4. Max Deadlift Work");
        System.out.println("5. Max Clean-And-Jerk Work");
        System.out.println("6. Skip this section");

        System.out.println("\nPlease choose 1-5");
        int choice = Validator.intInRange(sc, 1, 6);

        switch (choice) {
            case 1:
                this.displayInformation();
                break;
            case 2:
                this.squatHelper(sc);
                break;
            case 3:
                this.benchHelper(sc);
                break;
            case 4:
                this.deadliftHelper(sc);
                break;
            case 5:
                this.cajHelper(sc);
                break;
            case 6:
                break;
        }
    }

    public void squatHelper(Scanner sc) {
        System.out.println("\n-------------------------------------\n");
        System.out.println("Would you like to:");
        System.out.println("1. Work up to new PR");
        System.out.println("2. Know when to PR");
        System.out.println("3. Update current PR");

        int choice = Validator.intInRange(sc, 1, 3);

        switch (choice) {
            case 1:
                this.prPlan(sc, this.maxSquat);
                break;
            case 2:
                this.advice("squat");
                break;
            case 3:
                this.maxSquat = Validator.validateDouble(sc, "\nWhat is your new PR in Kilograms?");
                System.out.println("\nNew PR Updated\n");
                break;
        }
    }
    public void benchHelper(Scanner sc) {
        System.out.println("\n-------------------------------------\n");
        System.out.println("Would you like to:");
        System.out.println("1. Work up to new PR");
        System.out.println("2. Know when to PR");
        System.out.println("3. Update current PR");

        int choice = Validator.intInRange(sc, 1, 3);

        switch (choice) {
            case 1:
                this.prPlan(sc, this.maxBench);
                break;
            case 2:
                this.advice("bench");
                break;
            case 3:
                this.maxBench = Validator.validateDouble(sc, "\nWhat is your new PR in Kilograms?");
                System.out.println("\nNew PR Updated\n");
                break;
        }
    }
    public void deadliftHelper(Scanner sc) {
        System.out.println("\n-------------------------------------\n");
        System.out.println("Would you like to:");
        System.out.println("1. Work up to new PR");
        System.out.println("2. Know when to PR");
        System.out.println("3. Update current PR");

        int choice = Validator.intInRange(sc, 1, 3);

        switch (choice) {
            case 1:
                this.prPlan(sc, this.maxDeadlift);
                break;
            case 2:
            this.advice("deadlift");
                break;
            case 3:
                this.maxDeadlift = Validator.validateDouble(sc, "\nWhat is your new PR in Kilograms?");
                System.out.println("\nNew PR Updated\n");
                break;
        }
    }
    public void cajHelper(Scanner sc) {
        System.out.println("\n-------------------------------------\n");
        System.out.println("Would you like to:");
        System.out.println("1. Work up to new PR");
        System.out.println("2. Know when to PR");
        System.out.println("3. Update current PR");

        int choice = Validator.intInRange(sc, 1, 3);

        switch (choice) {
            case 1:
                this.prPlan(sc, this.maxCAJ);
                break;
            case 2:
                this.advice("caj");
                break;
            case 3:
                this.maxCAJ = Validator.validateDouble(sc, "\nWhat is your new PR in Kilograms?");
                System.out.println("\nNew PR Updated\n");
                break;
        }
    }

    public void advice(String exercise) {
        System.out.println("\n-----------------------------\n"); 
        System.out.println("There are some important things to remember when working up to a new PR:"); 
        System.out.println("\n- Proper Diet\n"); 
        System.out.println("   A proper diet which consists of appropriate amounts of all your macros is imperative to strength and size training, particularly your protein.");
        System.out.println("   Avoid going for PRs on a cut, as this is a time for leanness and not strength. PRing is best suited to a bulk.");
        System.out.println("   Try supplements as well, considering Protein power as well as Creatine Power."); 
        System.out.println("\n- Deload weeks and proper build ups\n");
        System.out.println("   After attempting any PRs or particularly heavy lifts, or alternatively if you are fatigued, try a Deload week.");
        System.out.println("   On a deload week, never go for weights in your program about 60% of your max weight. Focus on form and recovery.");
        System.out.println("   This will help recover from accumulated fatigue.");
        System.out.println("   Then, slowly build back up to your current PR and then your new PR over multiple weeks. Never PR more than once a month. If you are more advanced, never more than once per 8-12 weeks.\n");
    
        System.out.println("\n- Accessory Exercises\n"); 
        switch(exercise) {
            case "squat":
              System.out.println("   Any exercises which would support your squat are crucial. Squats target your quads and glutes primarily, so emphasis on these is imperative."); 
              System.out.println("   For your quads, ensure to include other squatting or pressing motions such as Leg Press or hack squat, as these will transfer over extremely well."); 
              System.out.println("   For your glutes, exercises lke hipthrusts will put good emphasis on your glutes. You can often hipthrust a lot more weight than expected, so push yourself.");
              break;
            case "bench":
              System.out.println("   Benchpressing targets your chest and triceps primarily, so working on these can be extremely beneficial"); 
              System.out.println("   For your chest, try an alternative pressing motion like Incline Bench or Dumbbell Bench which you don't care as much about PRing on. Focus on form and repetitions."); 
              System.out.println("   For your triceps, try pushdowns or extensions, focusing on smaller repetitions and more weight. Research suggests even one heavy set per week can increase strength.");
              break;
            case "deadlift":
              System.out.println("   Deadlifting puts tension on your back, tests grip strength, as well as your hamstrings and glutes.");
              System.out.println("   For grip strength, either attempt deadhangs or invest in lifting straps.");
              System.out.println("   For your back, try exercises which safely strengthen your lower back and consider a lifting belt if you are feeling painful tension.");
              System.out.println("   For your hamstrings and glutes, try Romanian Deadlifts, Stiff Legged deadlifts and hipthrusts, focusing on lower reps and more weight you can safely handle.");
            case "caj":
              System.out.println("   Clean-And-Jerk puts strain on your legs, core and shoulders. A key component is balance as well.");
              System.out.println("   For your legs, consider intense movement exercises like high jumps, lunges and any pressing motion.");
              System.out.println("   For your core, try lots of push ups and sit ups for high repetitions.");
              System.out.println("   For your shoulders, try secure pressing motions like seated overhead press, focusing on weight and less on reps.");
              System.out.println("   It is imperitive to have form right on this exercises, so get used to it with a weight on Clean-And-Jerk you can easily control. Try balancing movements to get used to instability.");
            //No default case needed
        }
    }

    public void prPlan(Scanner sc, double currentPr) {
        System.out.println("\nWould you like: \n1. A Recommended PR \n2. To suggest a new PR");
        int choiceTwo = Validator.intInRange(sc, 1, 2);
        double desiredPr;
        if (choiceTwo == 1) {
            desiredPr = currentPr + 5;
        } else {
            desiredPr = Validator.validateDouble(sc, "What PR would you like to go for?");
            if ( (desiredPr-currentPr) > 10 ) {
                System.out.println("\nThis is too heavy of a jump. If you feel confident you can get this, consider attempting the recommended PR first.");
                desiredPr = currentPr + 5;
            }
        }

        System.out.println("\nYour target PR for this session is " + desiredPr + "Kg. Your build up will be a pyramid style build up.");
        System.out.println("\n- Initial warm up: Consider just the bar or a weight of RPE 2-4 for 10 reps.\n");

        for (int i = 10; i >= 5; i = i - 1) {
            System.out.println("- "+roundLift((int)(desiredPr*(0.5 + (10-i)*(0.3 / 5))))+"Kg, "+ i + " Reps");
        }

        System.out.println("- "+roundLift((int)(desiredPr*0.9))+"Kg for 3 reps");
        System.out.println("- "+roundLift((int)(desiredPr*0.95))+"Kg for 2 reps");
        System.out.println("- PR Attempt: " + desiredPr+"Kg for 1 rep.");
        System.out.println("If you fail the PR, but made a form mistake which you believe costed you the PR, attempt again once after a long rest, but only one more attempt.");
    }

    public int roundLift(int input) {
        int remainder = input % 5;
        return input-remainder;
    }
}
