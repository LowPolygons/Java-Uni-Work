package classes.Workouts;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.HashMap;
import java.io.File;  // Import the File class

//Dont have to instantiate these as they are abstract classes
import classes.Misc.Validator;
import classes.Misc.FileManager;

import interfaces.Workouts.WorkoutBTS;

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
        System.out.println("\nImportant disclaimer: This is to be used as a guide to your workout, rather than a concrete bible workout.\n");

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
        System.out.println("5. Skip this choice section.");

        System.out.println("\nPlease choose 1-5");
        int choice = Validator.intInRange(sc, 1, 5);

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
                this.MuscleGroupExercises(sc);
                break;
            case 6:
                //Do nothing
                break;
        }

    }

    public void MuscleGroupExercises(Scanner sc) {
        System.out.println("\n------------------------------------\n");
        System.out.println("There are some important terms to consider before continuing: ");
        System.out.println("- RPE: Rate of Perceived Exertion. This is a scale from 1-10 on how difficult a given repetition is. For example, a rep which you can only do once and was close to failing would be an RPE 10.");
        System.out.println("- Long-Lengthened Partials: Repetitions which do not extend the full range of motion, instead sticking to the region which puts the muscle in the most stretch position.");
        System.out.println("- MYO Repetitions: These are sets of an exercise where you take lighter weights and shorter rest periods for a more time effective training schedule.");
        System.out.println("- Progressive Overload: This is the concept off, every week, increasing the difficulty of your workout, wether that is increasing the weight or adding more repetitions to a set.");
        System.out.println("- Drop set: A single working set where you decrease the weight once you cant continue with the current weight.");

        System.out.println("\nHere are some important tips to consider:");
        System.out.println("- Training to Failure: finishing a set in such a manner that you are incapable of completing another repetition at the current weight.");
        System.out.println("- Eccentric Control: Throughout any single exercise, ensure you are in total control of the weight during the 'negative' of the movement as well as the positive. This ensures maximum muscle growth potential.");
        System.out.println("- Working out a muscle group betwwen 5-8 sets per session seems to give the most effective muscle growth stimulus.");

        System.out.println("Which gym-day would you like tips on: ");
        System.out.println("1. Push");
        System.out.println("2. Pull");
        System.out.println("3. Legs");

        int choice = Validator.intInRange(sc, 1, 3);

        switch (choice) {
            case 1:
                System.out.println("\n\nA good Push workout will put emphasis on your Pecs, Shoulders/delts and Triceps.");
                System.out.println("\nExample Pec Exercises");
                System.out.println("- Incline Bench Press / Incline Dumbbell Press: 2-3 warm up sets, increasing the weight each time, then 3 working sets, at a weight of RPE 7-8 for 10-12 reps. Consider long-lengthened partials.");
                System.out.println("- Cable Pec Flys/Machine Pec Flys: 1-2 Warm up sets, increasing the weight each time. 3 working sets, 10 reps, RPE 7-8 and the final set considering working till failure followed by a dropset.");
                
                System.out.println("\nExample Shoulder Exercises:");
                System.out.println("- Any Pressing motion, such as Incline Bench Press or Seated Overhead Press: 2-3 warm up sets, 3-4 working sets, RPE 7-8, 8-10 reps.");
                System.out.println("- Cable/Dumbbell lateral raises: 1-2 warm up sets, 4-5 working sets, RPE 7-8 and 10-12 reps. Focus especially on eccentric control.");
                System.out.println("- Reverse Pec Flys: 1-2 warm up sets, 3 working sets, RPE 7-8, 12-15 Reps.");
        
                System.out.println("\nExample Triceps Exercises:");
                System.out.println("- Overhead cable extensions: 2-3 warm up sets, ensuring total eccentric control and a deep stretch. 3-5 working sets, RPE 7-8, 10-15 reps.");
                System.out.println("- Skull Crushers/Cable flat bar push downs: 1-2 warm up sets. 3-4 working sets, RPE 7-8, 10-15 reps. Consider MYO Reps here, and go to failure on at least the final set.");

                break;
            case 2:
                System.out.println("\n\nA good Pull workout will put emphasis on your Back and Biceps.");
                System.out.println("\nExample Back Exercises:");
                System.out.println("- Strict Pull Ups: 2 warm up sets with appropriate weight assistance. 3-4 working sets, focusing on the deep stretch at the bottom and pausing before the next rep. RPE 7-8, 6-10 reps, taking the last set to failure.");
                System.out.println("- Any Pulldown or Row motion: 2 warm up sets. 3-4 working sets, RPE 7-8. Consider Long-lengthened partial reps and MYO Reps.");
                System.out.println("- Stiff-Armed Cable Pulldowns: 1 warm up set, 3-4 working sets, RPE 7-8. Consider long-lengthened partials once a full rep is not achievable.");

                System.out.println("\nExample Bicep Exercises:");
                System.out.println("- Dumbell curls or cable curls (eg Bayesian Curls): 2 warm up sets, 3-4 working sets, RPE 6-8. Pause in the most stretched position. 8-12 Reps");
                System.out.println("- Hammer Curls: 1 warm up set, 2-3 working sets, RPE 6-8. Consider long-lengthened partials. 6-10 Reps");
            case 3:  
                System.out.println("\n\nA good Pull workout will put target your Quads, Hamstrings, Abductors and Calves");
                System.out.println("\nExample Calves Exercises:");
                System.out.println("- Calf Raises: 1-2 warm up sets, 3-5 working sets, RPE 8-9, Reps 5-10. Consider Long-lengthened partials and going to failure.");

                System.out.println("\nExample Hamstring exercises:");
                System.out.println("- Romanian Deadlifts (RDLS): 2-3 warm up sets, 3-4 working sets, RPE 7-8, 10-12 Reps.");
                System.out.println("- Leg Curls: 1-2 warm up sets. 3-5 working sets, 10-15 Reps, RPE 7-8. Consider pausing at the stretched position, and try one leg at a time.");

                System.out.println("\nExample Quads Exercises:");
                System.out.println("- A form of Squat or Leg Press: 2-4 warm up sets, 3-5 working sets, RPE 7-9, 5-15 reps. Focus on a deep stretch and full range of motion. Consider going to failure on Leg press exercises or machine exercises.");
                System.out.println("- Leg Extensions: 1-2 warm up sets, 3-5 working sets, RPE 6-8, 10-15 reps. Focus on eccentric control and a deep stretch. Try going to failure (this will hurt!)");
                System.out.println("- Lunges: 1-2 warm up sets, 2-3 working sets, 10 reps per leg, RPE 6-7. Avoid training to total failure unless you are using a Smith machine and can bail easily.");
                System.out.println("Bonus: Immediately after your last quad exercise, try a single set of bodyweight squats in long-lengthened partials till total failure.");
        }
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
            System.out.println("\nThe number of days you have chosen to work out will not yeild you results as effective as they could be. \n");

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
