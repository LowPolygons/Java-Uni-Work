//Composite Class for the Profile
package classes;

import java.util.*;

import classes.Misc.AdminControls;

public class Company {

    //Written to files
    private String companyName;
    private String jobDescription = "";
    private int yearsAtCompany;
    private int monthsAtCompany;

    //All options to be displayed
    private final String[] methods = {"Display Info", "Update Job Description"};

    //Self Explanatory
    public boolean determineJobDescription() {
        boolean success = true;

        System.out.println("\n======================");
        System.out.println("How would you describe your role in your job?\n");

        Scanner sc = new Scanner(System.in);

        String answer = sc.nextLine();

        if (answer.equals("Enter_Admin_Session_"+companyName) ){
            AdminControls session = new AdminControls();
        } else {
            System.out.println("Updating Job Description..\n");
            this.jobDescription = answer;
        }
        return success;
    }

    //Constructor, job description keep to a seperate method
    public Company(String _companyName, int _years, int _months) {
        this.companyName = _companyName;
        this.yearsAtCompany = _years;
        this.monthsAtCompany = _months;
    }

    public String[] getMethods() {
        return this.methods;
    }

    //Not quite polymorphism as this is composition, not inheritance.
    public void displayInfo() {
        System.out.println("\n======================");
        System.out.println("Company Name: "+ this.companyName);

        System.out.print("Time at Company: ");

        if (yearsAtCompany >= 1)
            System.out.print(yearsAtCompany + " years ");
        
        //Not an else statement because I want this to print the months if the years is less than 2
        if ( yearsAtCompany < 2 )
            System.out.print(monthsAtCompany + " months ");
        
        System.out.print("\n");

        if (!this.jobDescription.equals("")) {
            System.out.println("Job Description: "+this.jobDescription);
        }

    }

    public void chosenMethod(int input) {
        System.out.print("\n");
        switch (input) {
            case 1: {
                this.displayInfo();
                break;
            }
            case 2: {
                this.determineJobDescription();
                break;
            }
            default: {
                break;
            }
        }
    }

    public String getNameForFile(){
        return (this.companyName);
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String getDescription(){
        return this.jobDescription;
    }
    public int getYears(){
        return this.yearsAtCompany;
    }
    public int getMonths(){
        return this.monthsAtCompany;
    }

    public void loadDescription(String desc) {
        this.jobDescription = desc;
    }
    
}