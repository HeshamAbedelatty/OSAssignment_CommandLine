import CommandOperation.Parser;
import CommandOperation.Terminal;


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                break;
            }

            Parser.parse(userInput);
        }

        scanner.close();
    }
}
