import java.util.*; //p sure this just means all

public class test2 {
    public static void main ( String[] args) {
        double bias = 0.5; //between zero and one

        double rando = Math.random()*100;

        if (rando < bias*100) {
            System.out.println("Heads!");
        } else {
            System.out.println("Tails!");
        }
    }
}
