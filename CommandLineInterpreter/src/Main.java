import CommandOperation.Parser;
import CommandOperation.Terminal;


import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            
            System.out.print(Terminal.currentPath()+" >");
            String userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                break;
            }

            Parser.parse(userInput);
        }

        scanner.close();
    }
}
