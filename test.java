import java.util.*; //p sure this just means all

public class test {
    
    final double shortPi = 3.14159;  // Constants start with the keyword `final`

   static Scanner userInp = new Scanner(System.in);

    public static void main ( String[] args) {
        System.out.println("Hello World, java sucks");

        //byte, short, char, boolean, int, float, double, long, string
        //floats need an uppercase F at the end of the number
        //scientific notation is allowed, 1e+3 is 1000 for eg
        //for large numbers, you can use _ eg

        long num = 123_456_789;
        System.out.println(num);

        //casting is the same as in C++,
        int newNum = (int)num;
        System.out.println(newNum + " Input a number: \n");

        String input = userInp.nextLine();

        newNum = Integer.parseInt(input);

        System.out.println("Your Number Squared: "+ newNum*newNum);
    
        for (int i = 1; i <= 12; i++){
            System.out.println(newNum + " x " + i + " = " + newNum*i);
        }    
        System.out.println("\n");
        for (int i = 1; i <= 12; i++){
            double throwAway = Math.random();
            System.out.println(newNum + " x " + throwAway + " = " + ((double)newNum)*throwAway);
        }
        for (int i = 1; i <= 10; i++){
            System.out.println("\n");
        }

        int[] dims = {20,20};

        for (int i = 1; i <= dims[1]; i++) {
            String temp = "";
            for (int j = 1; j <= dims[0]; j++) {
                String currChar = " ";
                if ((i == 1 || i == dims[1]) || (j == 1 || j == dims[0])) {
                    currChar = "#";
                }

                temp = temp + currChar;
            }
             System.out.println(temp);
        }

    }
}
