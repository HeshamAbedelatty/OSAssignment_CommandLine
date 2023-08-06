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

   public void copycontent(String source, String destination) {
        try {
            // Create input and output streams
            FileInputStream inputStream = new FileInputStream(source);
            FileOutputStream outputStream = new FileOutputStream(destination);

            // Create a buffer to hold the data while copying
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Copy data from the source file to the destination file
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close the streams
            inputStream.close();
            outputStream.close();

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred while copying the file: " + e.getMessage());
        }

    }

}
