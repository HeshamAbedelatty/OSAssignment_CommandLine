package CommandOperation;
import java.nio.file.Paths;

public class Terminal {
   public void clear() {

        // Code to clear the console screen

        for(int i=0;i<30;i++) {
            System.out.println("");
        }
    }
    public void currentPath() {
    /////prints the current path.(pwd)
        java.nio.file.Path path = Paths.get("");

        // Convert the Path to a string representation of the current path
        System.out.println( path.toAbsolutePath().toString());
    }

   public void listFiles() {
        //  list files and directories in the current directory.(ls)
        File currentDirectory = new File(System.getProperty("user.dir"));
        File[] files = currentDirectory.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

}
