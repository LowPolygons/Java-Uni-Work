
package classes.Misc;

import java.util.Scanner;

//Represents a mitigation of Testing needed to be done regarding user inputs

//Doesn't need a constructor so may as well be abstract
public abstract class Validator {
    //A validation function until a yes or no is input
    public static String yesOrNo(Scanner sc, String msg) {
        String temp_input = "";

        while ( !(temp_input.equals("yes") || temp_input.equals("no")) ) {
            System.out.println(msg);
            temp_input = sc.nextLine().toLowerCase();
        }

        return temp_input.toLowerCase();
    }

    //A validation function until an integer is put in
    public static int validateInt(Scanner sc, String prompt) {
        //scanner.hasNextInt() checks if the next input is a valid integer
        System.out.println(prompt);
        int output = 0;
        boolean valid = false;

        while (!valid) {
            if (sc.hasNextInt()) {
                valid = true;
                output = sc.nextInt();
                sc.nextLine();
                
            } else {
                System.out.println("Invalid input. Try again: ");

                if (sc.hasNext()) {
                    sc.nextLine(); //Skip over the given input
                } else {
                    return -1;
                }
            }
        }

        return output;
    }

    //A validation function until a double is put in
    public static double validateDouble(Scanner sc, String prompt) {
        //scanner.hasNextInt() checks if the next input is a valid integer
        System.out.println(prompt);
        double output = 0;
        boolean valid = false;

        while (!valid) {
            if (sc.hasNextDouble()) {
                valid = true;
                output = sc.nextDouble();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Try again: ");

                if (sc.hasNext()) {
                    sc.nextLine(); //Skip over the given input
                } else {
                    return -1.0;
                }
            }
        }

        return output;
    }

    //Waits until a valid integer in a range is given
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
            
            if (sc.hasNextLine()) {
                sc.nextLine(); //Skip over the given input
            }
        }

        return output;
    }
}