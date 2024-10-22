//Composite Class for a full Profile
class Person {
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
    // public void howManyYearsLeft() {
    //     int diff = 40 - this.timeAtCompany;
    //     System.out.println("\nMost people work for 40 years. You have "+ diff +" years left.\n");
    // }
}