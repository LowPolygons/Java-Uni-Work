import java.util.Scanner;

public class badcode
{

    public static void main (String[] args) 
{
        Scanner so = new Scanner(System.in);
        int a;
        int b;
        int c = 15;
        int[] d = new int[c];
        System.out.print("Please choose a number");
        a = Integer.parseInt(so.nextLine());
        for (int i = 0; i < c; i++ ) {
        d[i] = a * (i+1);
        System.out.println(d[i]+"x"+(i+1)+"= "+d[i]); }
        System.out.println("Please choose another number to multiply the above results");
        b = Integer.parseInt(so.nextLine());
        for (int i = 0; i < c; i++ ) {
            d[i] = d[i] * b;
            System.out.println(d[i]+"x"+b+"="+d[i]);
}so.close();}}