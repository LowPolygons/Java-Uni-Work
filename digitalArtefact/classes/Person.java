//Composite Class for a full Profile
package classes;

public class Person {
    //This is what gets written to a file
    private String firstName;
    private String surname;
    private int age;

    //Hard coded -> The index(+1) represents the case in chooseCorrectFunction
    //All options to be displayed
    private final String[] methods = {"Display Info"};

    // Constructor Method
    public Person(String first, String sur, int _age) {
        this.firstName = first;
        this.surname = sur;
        this.age = _age;
    }

    public String[] getMethods() {
        return this.methods;
    }

    public void displayInfo() {
        System.out.println("\n======================");
        System.out.println("Name: "+this.firstName+" "+this.surname);
        System.out.println(this.firstName+ " "+ this.surname+ " is "+ this.age+ " years old.");
    }

    public void chosenMethod(int input) {
        System.out.print("\n");
        switch (input) {
            case 1: {
                this.displayInfo();
                break;
            }
            default: {
                break;
            }
        }
    }
    public String getNameForFile(){
        return (this.firstName+this.surname);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }
    
    public int getAge() {
        return this.age;
    }
}