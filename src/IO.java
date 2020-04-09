import java.util.Scanner;

public class IO {
    public static void prompt(String s) {
        System.out.println(Color.CYAN+s+Color.RESET);
    }

    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        for (;;) {
            System.out.print(prompt + ": \n");
            if (! sc.hasNextInt()) {
                System.out.println("** Not a valid number, please try again");
                sc.nextLine(); // discard bad input
                continue;
            }
            int value = sc.nextInt();
            //sc.nextLine(); // discard any extra text on the line
            if (value < min) {
                System.out.println("** Number cannot be smaller than "+min +", please try again");
                continue;
            }
            if (value > max) {
                System.out.println("** Number cannot be larger than "+max +", please try again");
                continue;
            }
            return value;
        }
    }
}
