import java.util.Scanner;

public class Main {
    public static String SELECT_GAME = "Please enter 1 to play QUEST1, enter 2 to play QUEST OF LEGENDS";

    public static void main(String[] args){
        Quest q = new Quest();

        System.out.println("Welcome to My Game 3.0");
        int dir = IO.promptInt(new Scanner(System.in),SELECT_GAME,1,2);

        if (dir == 1) {
            q.playQ1();
        }
        else {
            q.playQ2();
        }

    }

}
