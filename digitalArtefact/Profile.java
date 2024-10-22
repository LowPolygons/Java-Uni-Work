 //The overseeing class for the entire profile
 //Anything not immediately dependent on the class name to be put in a new class (like 3NF in Databases)

 //Chat-GPT was asked if it was better to use composition or inheritance: (paraphrased)
 //"As the classes contained represent a "has-a" relationship as opposed to an "is-a", this is best suited
 //to composition."
public class Profile {
    //Note for the code in the class:
    // - Always reference indexes and orders of things in the same order as they have been defined here
    // - This ensures consistency and makes other methods much more simple

    private Person person;
    private Company company;

    //Initialised not by the user directly
    private String[] personMethods;
    private String[] companyMethods;

    //Another class for private information, accessible with a password would be a good additional feature

    public Profile(String first, String sur, int age, String comp, int years, int months) {
        this.person = new Person(first, sur, age);
        this.company = new Company(comp, years, months);

        this.personMethods = this.person.getMethods();
        this.companyMethods = this.company.getMethods();
    }

    //Main methods go here, any that are not intrinsically related to the composite classes or are best suited here
    public void displayProfileOptions() {
        System.out.println("\nPlease choose an option to explore your or expand on your: \n");
        System.out.println("\nPerson Information: ");

        int tracker = 0;

        for (int i = 0; i < this.personMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+":");
            System.out.print(this.personMethods[i]+"\n");
        }        

        System.out.println("\nCompany Information: ");

        for (int i = 0; i < this.companyMethods.length; i++) {
            tracker++;

            System.out.print("Option "+(tracker)+":");
            System.out.print(this.companyMethods[i]+"\n");
        }
    }

    public void promptUserSelection() {
        int options = this.personMethods.length + this.companyMethods.length;
        System.out.println("\nPlease choose an option (1-"+options+")\n");
    }

    public void chooseCorrectFunction(int input) {

        if (input <= this.personMethods.length) {
            // Person methods
            this.person.chosenMethod(input);
        } 
        else if (input <= (this.personMethods.length + this.companyMethods.length)) {
            input = input-this.personMethods.length; //localise it to company method indexes
            System.out.println(input);
            this.company.chosenMethod(input);
        }

    }


    public Person getPerson() {
        return this.person;
    }
    
    public Company getCompany() {
        return this.company;
    }

    public int getNumMethods() {
        return this.companyMethods.length + this.personMethods.length;
    }
}