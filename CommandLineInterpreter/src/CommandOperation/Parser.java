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
               System.out.println(t.currentPath());
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
        }


    }
}

