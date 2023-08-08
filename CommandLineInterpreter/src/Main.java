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

            if ( userInput.equals("exit"))
            { break;}

            if (userInput.contains("&")){
                String  [] parts = userInput.split("& " , 2);
                String input1 = parts[0];
                String input2 = parts[1];
                Parser.parse(input1);
                Parser.parse(input2);
            }
            else { Parser.parse(userInput); }
        }

        scanner.close();
    }
}
