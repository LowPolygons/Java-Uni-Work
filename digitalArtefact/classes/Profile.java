 //The overseeing class for the entire profile
 //Anything not immediately dependent on the class name to be put in a new class (like 3NF in Databases)

 //"As the classes contained represent a "has-a" relationship as opposed to an "is-a", this is best suited
 //to composition."

package classes;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import classes.Misc.Validator;
import classes.Misc.FileManager;
import classes.Workouts.*;

public class Profile {

    // Composite classes
    private Person person;
    private Company company;
    private WorkoutPlan workout; //You can initalise this with a new Powerlifing() or bodybuilding or any child class, polymorphism baby what a time to be alive
    
    //Initialised not by the user directly
    private String[] personMethods;
    private String[] companyMethods;
    private String[] profileMethods = {"Create/Load a Workout", "Delete Profile"};

    public Profile(String first, String sur, int age, String comp, int years, int months) {
        this.person = new Person(first, sur, age);
        this.company = new Company(comp, years, months);

        this.personMethods = this.person.getMethods();
        this.companyMethods = this.company.getMethods();
        //then form the class
    }

    public void initialiseWorkout(Scanner sc, Validator validator) {
        
        System.out.println("\n======================\nWorkout Plan");
        //Check if they already have a workout
        String answer = validator.yesOrNo(sc, "Do you already have a workout?\n");
        String workoutName = this.person.getNameForFile().toLowerCase();
        
        File possibleExistance = new File("workouts/"+workoutName+".workout");
        if ( answer.equals("yes") && possibleExistance.exists()) {
            //Get the 3 primary numbers, num of days, num of rests, ideal workout length

            System.out.println(workoutName);

            this.workout = FileManager.recreateWorkout(workoutName);
        } else {
            //If they said yes but a file doesnt exist so the flow of the program doesn't look rubbish
            if (answer.equals("yes"))
                System.out.println("\nNo existing workout found, creating a new one..");

            System.out.println("\nHow many days a week would you like this workout to be?\n");

            int numDays = validator.intInRange(sc, 1, 7);
            int numRests = 7 - numDays;
            int workoutLength = validator.validateInt(sc, "\nHow long would you like each workout to be? (in minutes)\n");

            //Determine workout type, create workout
            System.out.println("\nPlease choose a workout type (1-2): ");
            System.out.println("1. Powerlifting");
            System.out.println("2. Bodybuilding");

            int choice = validator.intInRange(sc, 1, 2);

            switch (choice) {
                case 1: {
                    this.workout = new Powerlifting(numDays, numRests, workoutLength, workoutName, false);
                    
                    boolean success = FileManager.saveWorkout(this.workout, this.workout.linesToWrite() );

                    break;
                }
                case 2: {
                    this.workout = new Bodybuilding(numDays, numRests, workoutLength, workoutName, false);
                    
                    boolean success = FileManager.saveWorkout(this.workout, this.workout.linesToWrite() );
                    
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    //Main methods go here, any that are not intrinsically related to the composite classes or are best suited here
    public void displayProfileOptions() {
        System.out.println("\n======================\nPlease choose an option to explore your or expand on your: \n");
        System.out.println("\n------------------------\nPersonal Options: \n");

        int tracker = 0;

        for (int i = 0; i < this.personMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+": ");
            System.out.print(this.personMethods[i]+"\n");
        }        

        System.out.println("\n------------------------\nCompany Options: \n");

        for (int i = 0; i < this.companyMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+": ");
            System.out.print(this.companyMethods[i]+"\n");
        }
        System.out.println("\n------------------------\nProfile Options: \n");

        for (int i = 0; i < this.profileMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+": ");
            System.out.print(this.profileMethods[i]+"\n");
        }

        System.out.print("Option "+(tracker+1)+": Save Profile\n");
    }

    public void promptUserSelection() {
        int options = this.personMethods.length + this.companyMethods.length + this.profileMethods.length + 1;//For saving
        System.out.println("\nPlease choose an option (1-"+options+")\n");
    }

    public void chooseCorrectFunction(int input, Scanner sc, Validator validator) {
        if (input <= this.personMethods.length) {
            // Person methods
            this.person.chosenMethod(input);
        } 
        else if (input <= (this.personMethods.length + this.companyMethods.length)) {
            input = input-this.personMethods.length; //localise it to company method indexes
            this.company.chosenMethod(input);
        } else {
            input = input-(this.personMethods.length + this.companyMethods.length);
            this.chosenMethod(input, sc, validator);
        }

    }

    //Even though it looks very repetitive, but there is no more simple or better alternative really
    //Alternative could be storing these in an array and then looping through it, but that is unecessary memory

    public ArrayList<String> linesToWrite() {
        ArrayList<String> linesToWrite = new ArrayList<String>();
        //format is :parameter => value;
        //Person
        linesToWrite.add("firstName => "+this.person.getFirstName()+";");
        linesToWrite.add("surname => "+this.person.getSurname()+";");
        linesToWrite.add("age => "+this.person.getAge()+";");
        //Company
        linesToWrite.add("companyName => "+this.company.getCompanyName()+";");
        linesToWrite.add("jobDescription => "+this.company.getDescription()+";");
        linesToWrite.add("yearsAtCompany => "+this.company.getYears()+";");
        linesToWrite.add("monthsAtCompany => "+this.company.getMonths()+";");

        return linesToWrite;
    }

    public void chosenMethod(int input, Scanner sc, Validator validator) {
        System.out.print("\n");
        switch (input) {
            case 1: {
                this.initialiseWorkout(sc, validator);
                break;
            }
            case 2: {
                this.deleteProfile();
                break;                
            }
            default: {
                break;
            }
        }
    }

    public Person getPerson() {
        return this.person;
    }
    
    public Company getCompany() {
        return this.company;
    }

    public int getNumMethods() {
        return this.companyMethods.length + this.personMethods.length + this.profileMethods.length;
    }

    public void loadCompDescription(String desc) {
        this.company.loadDescription(desc);
    }

    public void deleteProfile() {
        System.out.println("\n======================");
        
        System.out.println("\nDeleting Profile. This will terminate the session.");

        String fileName = (this.person.getFirstName()+this.person.getSurname()+this.company.getCompanyName()).toLowerCase();
        File currentProfile = new File("profiles/"+fileName+".profile");

        File currentWorkout = new File("workouts/"+ (this.person.getFirstName()+this.person.getSurname()).toLowerCase()+".workout");
        
        if (currentWorkout.exists())
            currentWorkout.delete();

        currentProfile.delete();

        System.out.println("Closing Session..");
        System.exit(0);
    }
}
