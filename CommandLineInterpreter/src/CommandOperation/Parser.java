package CommandOperation;

import java.io.IOException;
import java.util.List;

public class Parser {
    public static void parse(String userInput) throws IOException {
        String[] arg = userInput.split("\\s+");
        String command = arg[0];
        Terminal t = new Terminal();
        String output = "";
        int flag =0;
        if (command.equals("clear")) {
            if (arg.length != 1) {
                System.out.println("Error ! ,'clear' takes no arguments");
                flag = 1;
            } else {
                t.clear();
            }
        } else if (command.equals("pwd")) {
            if (arg.length != 1 && !arg[1].equals(">") && !arg[1].equals(">>")) {
                System.out.println("Error ! ,'pwd' takes no arguments");
                flag = 1;
            } else {
                output = Terminal.currentPath();
                System.out.println(output);
            }
        } else if (command.equals("cd")) {
            if (arg.length == 1) {
                t.cd1();
            } else if (arg.length == 2) {
                t.cd2(arg[1]);
            } else {
                flag = 1;
                System.out.println("Error! ,'cd' takes one or no arguments");
            }
        } else if (command.equals("ls")) {
            if (arg.length == 2|| arg.length > 3) {
                flag = 1;
                System.out.println("Error ! ,'ls' takes no arguments");
            } else {
                output = t.listFiles();
                System.out.println(output);
            }
        } else if (command.equals("cp")) {
            if (arg.length == 3) {
                t.copycontent(arg[1], arg[2]);
            } else {
                flag = 1;
                System.out.println("Error! ,cp Two arguments expected.");
            }
        } else if (command.equals("help")) {
            if (arg.length == 2 || arg.length > 3) {
                flag = 1;
                System.out.println("no arguments should be added");
            } else
                output = t.help();
            System.out.println(output);
        } else if (command.equals("date")) {
            if (arg.length > 1) {
                System.out.println("no arguments should be added");
                flag = 1;
            }else{
                output = t.date();
                System.out.println(output);
            }
        } else if (command.equals("rm")) {
            if (arg.length == 1){
                System.out.println("you should add arguments ");
                flag = 1;
            }
            else {
                String[] subArray = getSubArray(arg, 1, arg.length - 1);
                t.rm(subArray);
            }
        }else if (command.equals("mv")) {
            if (arg.length == 1 || arg.length == 2)
                System.out.println("you should add 2 arguments ");
            else {
                String[] subArray = getSubArray(arg, 1, arg.length - 1);
                t.mv(subArray);
            }
        } else if (command.equals("mkdir")) {
            if (arg.length == 1) {
                flag = 1;
                System.out.println("you should add arguments ");
            }else {
                String[] subArray = getSubArray(arg, 1, arg.length - 1);
                t.mkdir(subArray);
            }
        } else if (command.equals("rmdir")) {
            if (arg.length == 1){
                flag = 1;
                System.out.println("you should add arguments ");
            }
            else {
                String[] subArray = getSubArray(arg, 1, arg.length - 1);
                t.rmdir(subArray);
            }
        } else if (command.equals("cat")) {
            if (arg.length == 1)
                System.out.println("you should add arguments ");
            else if (arg.length == 4)
                System.out.println("you should add 1 or 2 arguments ");
            else {
                String[] subArray = getSubArray(arg, 1, 2);
                List<String> list = t.cat(subArray);
                output = convertListString(list);
                printList(list);
            }
        } else if (command.equals("more")) {
            if (arg.length == 1)
                System.out.println("you should add arguments ");
            else {
                List<String> list = t.more(arg[1]);
                output = convertListString(list);
                printList(list);
            }
        }
        // handle redirect directories
        int redirct_index = find(arg,">");
        int D_redirctIndex = find (arg,">>");
        if (flag ==1 ) {
            System.out.println();
        }
        else if (redirct_index == arg.length || D_redirctIndex == arg.length ){
            System.out.println("you should add file name. ");
        }
        else if (redirct_index > 0) {
            t.reidrect(output,arg[arg.length-1]);
        }
        else if (D_redirctIndex > 0) {
            t.D_reidrect(output,arg[arg.length-1]);
        }
    }
    public static int find(String[] array, String target){
        int found = -1;
        int i = 1;
        for (String element : array) {
            if (element.equals(target)) {
                found = i;
                break;
            }
            i++;
        }
        return found;
    }

    public static String[] getSubArray(String[] array, int startIndex, int endIndex) {
        int subArrayLength = endIndex - startIndex + 1;
        String[] subArray = new String[subArrayLength];

        for (int i = startIndex; i <= endIndex; i++) {
            subArray[i - startIndex] = array[i];
        }

        return subArray;
    }
    public static String convertListString(List<String> line){
        String out = "";
        for (String s : line) {
            out = out.concat(s);
            out = out.concat("\n");
        }
        return out;
    }
    public static void printList(List<String > list){
        for (String s : list) {
            System.out.println(s);
        }
    }
}

