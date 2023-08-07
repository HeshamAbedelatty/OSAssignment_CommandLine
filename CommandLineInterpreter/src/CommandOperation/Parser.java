package CommandOperation;

public class Parser {
    public static void parse(String userInput) {
        String[] arg = userInput.split("\\s+");
        String command = arg[0];
        Terminal t = new Terminal();
        if (command.equals("clear"))
        {
            if(arg.length != 1)
            {
                System.out.println ("Error ! ,'clear' takes no arguments");
            }
            else
            {
                t.clear();
            }
        }
        else if (command.equals("pwd"))
        {
            if(arg.length != 1)
            {
                System.out.println ("Error ! ,'pwd' takes no arguments");
            }
            else
            {
               System.out.println(Terminal.currentPath());
            }
        }
        else if(command.equals("cd"))
        {
            if (arg.length==1){
                t.cd1();
            }
            else if(arg.length==2){
                t.cd2(arg[1]);
            }
            else{
                System.out.println("Error! ,'cd' takes one or no arguments");
            }
        }
        else if (command.equals("ls"))
        {
            if(arg.length != 1)
            {
                System.out.println ("Error ! ,'ls' takes no arguments");
            }
            else
            {
                t.listFiles();
            }
        }
        else if (command.equals("cp")){
            if (arg.length==3)
            {
                t.copycontent(arg[1], arg[2]);
            }
            else
            {
                System.out.println ("Error! ,cp Two arguments expected.");
            }
        } else if (command.equals("help")) {
            if (arg.length > 1) {
                System.out.println("no arguments should be added");
            }
            else
                t.help();
        } else if (command.equals("date")) {
            if (arg.length > 1)
                System.out.println("no arguments should be added");
            else
                t.date();
        } else if (command.equals("rm")) {
            if (arg.length == 1)
                System.out.println("you should add arguments ");
            else {
                String[] subArray = getSubArray(arg, 1, arg.length-1);
                t.rm(subArray);
            }
        } else if (command.equals("mkdir")) {
            if (arg.length == 1)
                System.out.println("you should add arguments ");
            else {
            String[] subArray = getSubArray(arg, 1, arg.length-1);
            t.mkdir(subArray);
            }
        } else if (command.equals("rmdir")) {
            if (arg.length == 1)
                return;
            String[] subArray = getSubArray(arg, 1, arg.length-1);
            t.rmdir(subArray);

        } else if (command.equals("cat")) {
            if (arg.length == 1)
                System.out.println("you should add arguments ");
            else {
                String[] subArray = getSubArray(arg, 1, arg.length-1);
                t.cat(subArray);
            }
        }

    }
    public static String[] getSubArray(String [] array, int startIndex, int endIndex) {
        int subArrayLength = endIndex - startIndex + 1;
        String [] subArray = new String[subArrayLength];

        for (int i = startIndex; i <= endIndex; i++) {
            subArray[i - startIndex] = array[i];
        }

        return subArray;
    }
}

