package classes.Misc;

import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;

import java.util.Scanner;
import classes.Misc.Validator;

public class AdminControls {
    public AdminControls() {
        System.out.println("\n<<>> <<>> Admin Options (Warning: Session will Terminate after Admin Session) <<>> <<>>\n");

        System.out.println("1. Wipe Profiles directory");
        System.out.println("2. Wipe Workouts directory");
        System.out.println("3. Destroy Individual Profile");
        System.out.println("4. Destroy Individual Workout");

        boolean continueSession = true;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Please Choose from the above options\n");

            int choice = Validator.intInRange(sc, 1, 4);

            switch (choice) {
                case 1:
                    wipe("profiles");
                    break;
                case 2:
                    wipe("workouts");
                    break;
                case 3:
                    deleteFile("profile",sc);
                    break;
                case 4:
                    deleteFile("workout",sc);
                    break;
                default:
                    System.out.println("An issue has occured, terminating session.");
                    break;
            }

            String continueSessionStr = Validator.yesOrNo(sc, "Do you wish to continue?\n");

            if (continueSessionStr.equals("yes")) {
                continueSession = true;
            } else {
                continueSession = false;
            }

        } while( continueSession );

        sc.close();

        System.out.println("\nTerminating session.");
        System.exit(0);
    }
    
    public static void deleteFile(String type, Scanner sc) {
        System.out.println("\nWhat is the name of the file you want to delete, not including the file extension?");
        String fileName = sc.nextLine();

        File file = new File(type+"s/"+fileName+"."+type);

        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("There was a problem removing the file.");
            } else {
                System.out.println("File deleted.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    public static void wipe(String dir) {
        File directory = new File(dir);
        if (directory.isDirectory()) {
            //All files in the directory
            File[] files = directory.listFiles();

            for (File curr : files) {
                if (!curr.delete()) {
                    System.out.println("An issue has occured, terminating session.");
                    break;
                }
            }

        } else {
            System.out.println("Directory does not exist.");
        }
    }
}