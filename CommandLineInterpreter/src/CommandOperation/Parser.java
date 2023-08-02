package CommandOperation;

public class Parser {
   public static void parse(String userInput) {
        String[] tokens = userInput.split("\\s+");
        String command = tokens[0];
        Terminal t = new Terminal();
        if (command.equals("clear")) {
            t.clear();
        } else if (command.equals("pwd")) {
            t.currentPath();
        }

    }
}
