 //The overseeing class for the entire profile
 //Anything not immediately dependent on the class name to be put in a new class (like 3NF in Databases)

 //Chat-GPT was asked if it was better to use composition or inheritance: (paraphrased)
 //"As the classes contained represent a "has-a" relationship as opposed to an "is-a", this is best suited
 //to composition."

package classes;

import java.io.File;
import java.util.Scanner;

public class Profile {
    //Note for the code in the class:
    // - Always reference indexes and orders of things in the same order as they have been defined here
    // - This ensures consistency and makes other methods much more simple

    private Person person;
    private Company company;

    //Initialised not by the user directly
    private String[] personMethods;
    private String[] companyMethods;
    private String[] profileMethods = {"Delete Profile"};
    //Another class for private information, accessible with a password would be a good additional feature

    public Profile(String first, String sur, int age, String comp, int years, int months) {
        this.person = new Person(first, sur, age);
        this.company = new Company(comp, years, months);

        this.personMethods = this.person.getMethods();
        this.companyMethods = this.company.getMethods();
    }

    //Main methods go here, any that are not intrinsically related to the composite classes or are best suited here
    public void displayProfileOptions() {
        System.out.println("\n======================\nPlease choose an option to explore your or expand on your: \n");
        System.out.println("\n------------------------\nPersonal Options: \n");

        int tracker = 0;

        for (int i = 0; i < this.personMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+":");
            System.out.print(this.personMethods[i]+"\n");
        }        

        System.out.println("\n------------------------\nCompany Options: \n");

        for (int i = 0; i < this.companyMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+":");
            System.out.print(this.companyMethods[i]+"\n");
        }
        System.out.println("\n------------------------\nProfile Options: \n");

        for (int i = 0; i < this.profileMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+":");
            System.out.print(this.profileMethods[i]+"\n");
        }

        System.out.print("Option "+(tracker+1)+": Save Profile");
    }

    public void promptUserSelection() {
        int options = this.personMethods.length + this.companyMethods.length + this.profileMethods.length + 1;//For saving
        System.out.println("\nPlease choose an option (1-"+options+")\n");
    }

    public void chooseCorrectFunction(int input) {
        if (input <= this.personMethods.length) {
            // Person methods
            this.person.chosenMethod(input);
        } 
        else if (input <= (this.personMethods.length + this.companyMethods.length)) {
            input = input-this.personMethods.length; //localise it to company method indexes
            this.company.chosenMethod(input);
        } else {
            input = input-(this.personMethods.length + this.companyMethods.length);
            this.chosenMethod(input);
        }

    }

    //Even though it looks very repetitive, but there is no more simple or better alternative really
    //Alternative could be storing these in an array and then looping through it, but that is unecessary memory
    public String[] linesToWrite() {
        String[] linesToWrite = new String[7];
        //format is :parameter => value;
        //Person
        linesToWrite[0] = ":=:firstName => "+this.person.getFirstName()+";";
        linesToWrite[1] = ":=:surname => "+this.person.getSurname()+";";
        linesToWrite[2] = ":=:age => "+this.person.getAge()+";";
        //Company
        linesToWrite[3] = ":=:companyName => "+this.company.getCompanyName()+";";
        linesToWrite[4] = ":=:jobDescription => "+this.company.getDescription()+";";
        linesToWrite[5] = ":=:yearsAtCompany => "+this.company.getYears()+";";
        linesToWrite[6] = ":=:monthsAtCompany => "+this.company.getMonths()+";";

        return linesToWrite;
    }

    public void chosenMethod(int input) {
        System.out.print("\n");
        switch (input) {
            case 1: {
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

        currentProfile.delete();

        System.out.println("Closing Session..");
        System.exit(0);
    }
}