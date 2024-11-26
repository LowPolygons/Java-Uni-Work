package classes;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.HashMap;
import java.io.File;  // Import the File class

//Dont have to instantiate these as they are abstract classes
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

        this.promptLocalConstructor(alreadyExists);

        this.WorkoutInstance();
    }

    @Override
    public void promptLocalConstructor(boolean alreadyExists) {
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

        //Brief intro
        System.out.println("\n-------------------------------\n");
        System.out.println("This Workout plan will serve as a week-to-week guide on your training. Bodybuilding is not focused on PRs, but muscle Hypertrophy.");
        System.out.println("You have " + this.getDaySplit() + " days per week.");
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

    //This is an override from the parent class

    @Override
    public void perWorkoutFunc(Scanner sc) {
        //The workout plan, recalculated at the start every instance incase they change the split
        HashMap<String, String> plan = WeeklyRoutineCalculator(this.getDaySplit());

        //Hardcoded because this is like an embedded system
        System.out.println("\nOptions: \n");
        System.out.println("1. Change Number of Working Days");
        System.out.println("2. Display Weekly Split");
        System.out.println("3. Cutting and Bulking Advice");
        System.out.println("4. What Exercises Per Muscle?");
        System.out.println("5. Delete Workout");
        System.out.println("6. Skip this choice section.");

        System.out.println("\nPlease choose 1-5");
        int choice = Validator.intInRange(sc, 1, 6);

        switch (choice) {
            case 1:
                System.out.println("\nPlease choose a new number of working days.");
                int newNumDays = Validator.intInRange(sc, 1, 7);
                this.updateNumDays(newNumDays);
                this.updateNumRests(7 - newNumDays);
                break;
            case 2:
                System.out.println("\nHere is your weekly split. For exercises, check out the Exercises Per Muscle section.\n");

                String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

                for (String day : days) {
                    System.out.println(day + ": " + plan.get(day));
                }
                break;
            case 3:
                //Advice on cutting and bulking with reference to calorific intake and protein
                this.CuttingBulking();
                break;
            case 4:
                //Muscle groups
                this.MuscleGroupExercises();
                break;
            case 5:
                break;
            case 6:
                //Do nothing
                break;
        }

    }

    public void MuscleGroupExercises() {
        System.out.println("\n------------------------------------\n");
        System.out.println("There are some important terms to consider before continuing: ");
        System.out.println("\nRPE: Rate of Perceived Exertion. This is a scale from 1-10 on how difficult a given repetition is. For example, a rep which you can only do once and was close to failing would be an RPE 10.");
        System.out.println("Long-Lengthened Partials: Repetitions which do not extend the full range of motion, instead sticking to the region which puts the muscle in the most stretch position.");
        System.out.println("MYO Repetitions: These are sets of an exercise where you take lighter weights and shorter rest periods for a more time effective training schedule.");
        System.out.println("Progressive Overload: This is the concept off, every week, increasing the difficulty of your workout, wether that is increasing the weight or adding more repetitions to a set.");


    }

    public void CuttingBulking() {
        System.out.println("\n------------------------------------\n");
        System.out.println("\nWhen working out, you go through cutting and bulking phases.");
        System.out.println("\nBulking: This is your period when you are putting on size and muscle, and you fuel this by consuming more calories than your maintenance calories.");
        System.out.println("Cutting: This is your phase where you up your protein intake, consume less calories than your maintenance to drop body weight, and let your muscle fibers become more visible as you get more 'shredded.'");

        //Calculate calories stuff.
        double[] bulkingCalories = {this.dailyCalories * 1.05, this.dailyCalories*1.2};
        double[] cuttingCalories = {this.dailyCalories * 0.775, this.dailyCalories*0.925};

        System.out.println("\nFor your bodyweight and maintenance calories, on a bulking phase we recommend upping your calorie consumption to somewhere in the range of " + (int)bulkingCalories[0] + "Kcal to " + (int)bulkingCalories[1] + "Kcal.");
        System.out.println("For cutting, we recommend dropping to between " + (int)cuttingCalories[0] + "Kcal to " + (int)cuttingCalories[1] + "Kcal.");
        //Protein things

        double kgToLb = 2.20462;

        double bulkingProteinLambda = 0.7;
        double cuttingProteinLambda = 1;

        double bulkingProtein = this.bodyWeight * kgToLb * bulkingProteinLambda;
        double cuttingProtein = this.bodyWeight * kgToLb * cuttingProteinLambda;

        System.out.println("\nOn a bulking phase, to maximise muscle growth we recommend taking your protein intake to approximately "+(int)bulkingProtein + " grams of protein per day.");
        System.out.println("On a cutting phase, we recommend taking your protein intake to approximately " + (int)cuttingProtein + " grams of protein per day.");

        double proteinPercentageDiff =  100 * (this.dailyProtein) / (this.bodyWeight * kgToLb) ;
        if ( Math.abs(100 - proteinPercentageDiff) > 20 ) {
            //If it is out by more than 20%
            if (100 - proteinPercentageDiff > 0)
                System.out.println("\nRight now, your protein intake is too small, and this will be costing you potential muscle growth. Try eat more high protein foods such as Chicken Breast or Mince to fill in this gap.");
            else
                System.out.println("\nYour daily protein intake is too high right now. Please try and decrease your intake as right now it is excessive.");
        }
        

        System.out.println("\nA good bulking phase for you might look something like this:");
        System.out.println("- Consume "+ (int)bulkingCalories[0] + "Kcal to " + (int)bulkingCalories[1] + "Kcal a day until you have put on between 5-15% of your starting bodyweight, around "+ (int)this.bodyWeight*1.1 + "Kg");
        System.out.println("- Try not to exceed 15-20% body fat percentage. Throughout the duration of the bulk. Your diet therefore will determine how long this bulk lasts.");
        if (this.bodyFatPercentage > 15) {
            System.out.println("At your current body fat percentage, consider cutting first.");
        }

        System.out.println("\nA good cutting phase for you might look like this:");
        System.out.println("- Drop your calorie intake to " + (int)cuttingCalories[0] + "Kcal to " + (int)cuttingCalories[1] + "Kcal throughout the cutting phase until you have decreased your body fat percentage into the 8-10% range.");
        System.out.println("- To get an extra shredded tone on your physique, towards the end of the cut try and reduce your water intake. Be careful with this though, and restrict this to a handful of days at most.");
        
        System.out.println("\n------------------------------------\n");
    }

    //Bit of a mess but it functions nicely
    public HashMap<String, String> WeeklyRoutineCalculator(int numDays) {
        // It is already in the range of 1 to 7, and num rests is calculated from that
        String[] gymDays = {"Push", "Pull", "Legs", "Any"}; //the Any is for a 7 day split

        HashMap<String, String> returnValue = new HashMap<String, String>();

        returnValue.put("Monday", "Rest");
        returnValue.put("Tuesday", "Rest");
        returnValue.put("Wednesday", "Rest");
        returnValue.put("Thursday", "Rest");
        returnValue.put("Friday", "Rest");
        returnValue.put("Saturday", "Rest");
        returnValue.put("Sunday", "Rest");

        if (numDays < gymDays.length)
            System.out.println("\n The number of days you have chosen to work out will not yeild you results as effective as they could be. \n");

            switch (numDays) {
                case 1:
                    System.out.println("Due to this being a 1 day split, do the planned workout on any day of the week you want.");
                    returnValue.put("Wednesday", gymDays[0] + ", " + gymDays[1] + ", " + gymDays[2]);

                    break;
                case 2:
                    System.out.println("Due to this being a 2 day split, do the days on any two days of the week.");
                    returnValue.put("Tuesday", gymDays[0] + ", " + gymDays[1]);
                    returnValue.put("Thursday", gymDays[2]);

                    break;
                case 3:
                    System.out.println("A Three day split will let you hit each muscle group with once a week without residual fatigue.");

                    returnValue.put("Monday", gymDays[0]);
                    returnValue.put("Wednesday", gymDays[1]);
                    returnValue.put("Friday", gymDays[2]);

                    break;
                case 4:
                    System.out.println("A four day split will represent an 'Upper / Lower' split, hitting each group twice per week.");

                    returnValue.put("Monday", gymDays[0] + ", " + gymDays[1]);
                    returnValue.put("Tuesday", gymDays[2]);

                    returnValue.put("Thursday", gymDays[0] + ", " + gymDays[1]);
                    returnValue.put("Friday", gymDays[2]);
                    break;
                case 5:
                    System.out.println("A five day split will represent a combination of a Push-Pull-Legs and 'Upper / Lower' split, hitting each group twice per week.");

                    returnValue.put("Monday", gymDays[0] + ", " + gymDays[1]);
                    returnValue.put("Tuesday", gymDays[2]);

                    returnValue.put("Thursday", gymDays[0]);
                    returnValue.put("Friday", gymDays[1]);
                    returnValue.put("Saturday", gymDays[2]);
                    break;                
                case 6:
                    System.out.println("A six day split is an optimised Push-Pull-Legs workout hitting each group twice per week");

                    returnValue.put("Monday", gymDays[0]);
                    returnValue.put("Tuesday", gymDays[1]);
                    returnValue.put("Wednesday", gymDays[2]);
                    
                    returnValue.put("Friday", gymDays[0]);
                    returnValue.put("Saturday", gymDays[1]);
                    returnValue.put("Sunday", gymDays[2]);
                    break;
                case 7:
                    System.out.println("A routine with no dedicated rest day may not be optimal. On the day which has 'Any', take it easy and hit only smaller muscle groups that aren't fatigued and aren't worked out the followed day.");

                    
                    returnValue.put("Monday", gymDays[0]);
                    returnValue.put("Tuesday", gymDays[1]);
                    returnValue.put("Wednesday", gymDays[2]);
                    returnValue.put("Thursday", gymDays[3]);
                    returnValue.put("Friday", gymDays[0]);
                    returnValue.put("Saturday", gymDays[1]);
                    returnValue.put("Sunday", gymDays[2]);

                    break;
                default:
                    System.out.println("A problem has occured with calculating the workout. Please re-enter your number of working Days per week.");
            }

        return returnValue;
    }
}
