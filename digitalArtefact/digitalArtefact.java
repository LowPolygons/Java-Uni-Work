import java.util.*;

public class digitalArtefact {
    //Static means the method belongs to the class and not a specific instance of the class
    //That is why when you use this.[variablename] it will not work if the method is static as it is not a specific instance
 
    //Given a scanner and a prompt, it repeatedly accepts inputs unti a valid integer is input

    public static String yesOrNo(Scanner sc) {
        String temp_input = "";

        while ( !(temp_input.equals("yes") || temp_input.equals("no")) ) {
            System.out.println("\nDo you wish to continue? (Yes/No)");
            temp_input = sc.nextLine().toLowerCase();
        }

        return temp_input.toLowerCase();
    }
    public static int validateInt(Scanner sc, String prompt) {
        //scanner.hasNextInt() checks if the next input is a valid integer
        System.out.println(prompt);
        int output = 0;
        boolean valid = false;

        while (!valid) {
            if (sc.hasNextInt()) {
                valid = true;
                output = sc.nextInt();
            } else {
                System.out.println("Invalid input. Try again: ");
                sc.next(); //Skip over the given input
            }
        }

        return output;
    }
    public static int intInRange(Scanner sc, int lowerBound, int upperBound) {

        int output = 0;
        boolean valid = false;

        while (!valid) {
            if (sc.hasNextInt()) {
                valid = true;
                output = sc.nextInt();
                if ( !(output >= lowerBound && output <= upperBound) )
                    valid = false;
            } 
            if (!valid) {
                System.out.println("Invalid input. Try again: ");
            }
            
            sc.nextLine(); //Skip over the given input
        }

        return output;
    }

    public static Profile initialiseProfile(Scanner sc) {

        System.out.println("Welcome to the digital artefact. What is your first name?");
        String first = sc.nextLine();
        
        System.out.println("What is your surname?");
        String sur = sc.nextLine();
        
        //Once profiles are getting saved to files, confirm if they have an account if the name exists in a file
        //If it does, confirm their password, pass it through a hash and compare the hash result to the one in the file

        System.out.println("Where do you work?");
        String comp = sc.nextLine();

        int age = validateInt(sc, "How old are you?");

        int years = validateInt(sc, "How many years have you worked at "+comp);
        int months = validateInt(sc, "How many leftover months have you worked at "+comp);

        System.out.println("Creating profile for user "+ first+ " "+ sur+ "... \n");

        Profile newProfile = new Profile(first, sur, age, comp, years, months);

        return newProfile;
    }

    //The main program flow
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        //Main method to create the Profile; NOT DIRECTLY THE CONSTRUCTOR
        Profile currProfile = initialiseProfile(sc);
        
        //Display the user options
        currProfile.displayProfileOptions();
        String continueProgram;

        do {
            currProfile.promptUserSelection();

            int menuChoice = intInRange(sc, 1, currProfile.getNumMethods() ); //sc.nextInt();

            System.out.println("You chose option: "+menuChoice);

            currProfile.chooseCorrectFunction(menuChoice);

            continueProgram = yesOrNo(sc);

        } while (continueProgram.equals("yes"));

        System.out.println("Program Safely Terminating...");
    }

}