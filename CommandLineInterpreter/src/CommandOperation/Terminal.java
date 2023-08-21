package CommandOperation;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal {
   public void clear() {

        // Code to clear the console screen

        for(int i=0;i<30;i++) {
            System.out.println("");
        }
    }
   public static String currentPath() {
    /////prints the current path.(pwd)
        String currentPath = System.getProperty("user.dir");

        // Convert the Path to a string representation of the current path
        return currentPath;

    }

   public String listFiles() {
        //  list files and directories in the current directory.(ls)
        File currentDirectory = new File(System.getProperty("user.dir"));
        File[] files = currentDirectory.listFiles();
        int i = 0;
        String out = "";
        List<String > list = new ArrayList<>() ;
        for (File file : files) {
            list.add(file.getName());
            i++;
        }
       for (String s : list) {
          out = out.concat(s);
          out = out.concat("  ");
       }
       return out;
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
    public void cd1(){
        // Get the user's home directory
        String userHome = System.getProperty("user.home");

        // Set the user's home directory as the current working directory
        System.setProperty("user.dir", userHome);

        // Verify the current working directory has been changed
        //System.out.println( System.getProperty("user.dir"));
    }
   public void cd2(String newpath){
        //set the new directory as the current working directory
        System.setProperty("user.dir", newpath);
        
    }
    public String help() {
        return "pwd : Current work directory\n"
                + "date : Current date/time\n"
                + "exit : Stop all\n"
                + "rm: Takes 1 argument which is a file name that exists in the current" +
                "directory and removes this file.\n"
                + "cat : Takes 1 argument and prints the file’s content or takes 2 arguments" +
                "and concatenates the content of the 2 files and prints it.\n"
                + "ls: Takes no arguments and lists the contents of the current directory" +
                "sorted alphabetically."
                + "cp: Takes 2 arguments, both are files and copies the first onto the\n" +
                "second."
                + "mkdir: Takes 1 or more arguments and creates a directory for each\n" +
                "argument"
                + "rmdir: remove empty directories\n"
                + ""
                + ""
                + ""
                + "";


    }
    public String  date() {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = now.format(formatter);

        return currentTime;
    }
    public void rm(String[] args) {
        for (String arg : args) {
            File file = new File(currentPath(), arg);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }
    public void mkdir(String[] args) {
        for (String arg : args) {
            File newDir = new File(currentPath(), arg);
            if (!newDir.exists()) {
                newDir.mkdirs();
            }
        }
    }
    public void rmdir(String[] args) {
        if (args[0].equals("*")) {
            File[] subDirs = new File(currentPath()).listFiles(File::isDirectory);
            if (subDirs != null) {
                for (File dir : subDirs) {
                    if (dir.listFiles().length == 0) {
                        dir.delete();
                    }
                }
            }
        } else {
            File dir = new File(currentPath(), args[0]);
            if (dir.isDirectory() && dir.listFiles().length == 0) {
                dir.delete();
            } else {
                System.out.println("Error: Directory is not empty.");
            }
        }
    }
    public List<String> more(String text)
    {
            Scanner scanner = new Scanner(System.in);
            File file = new File(currentPath(), text);
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                for (int i = 0; i < lines.size(); i++) {
                    if(i>=10){
                        System.out.println("More "+(lines.size()-i));
                        scanner.nextLine();
                        System.out.print(lines.get(i));
                    }
                    else
                        System.out.println(lines.get(i));
                }
                return lines;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
            List<String>lines = null;
            return lines;
    }
    public List<String > cat(String[] args) {
        if (args.length == 1) {
            File file = new File(currentPath(), args[0]);
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                for (String line : lines) {
                    System.out.println(line);
                }
                return lines;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        else if (args.length == 2) {
            File file1 = new File(currentPath(), args[0]);
            File file2 = new File(currentPath(), args[1]);
            try {
                List<String> lines1 = Files.readAllLines(file1.toPath());
                List<String> lines2 = Files.readAllLines(file2.toPath());
                for (String line : lines1) {
                    System.out.println(line);
                }
                for (String line : lines2) {
                    System.out.println(line);
                }
                lines1.addAll(lines2);
                return lines1;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("Error: cat command takes 1 or 2 arguments.");
        }
        List<String > line = null;
        return line;
    }
    public void D_reidrect(String text,String filename) throws IOException {

        File file = new File(currentPath(), filename);
        String p = "";
        if (file.exists() && file.isFile()) {
            try {
                Path path = Paths.get(currentPath()+"/"+filename);
                Scanner scanner = new Scanner(path);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    p = p.concat(line);
                }

                scanner.close();
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
            p = p.concat(text);
            try {
                // Create the directory if it doesn't exist
                File directory = new File(currentPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Create a FileOutputStream for the specified file
                FileOutputStream outputStream = new FileOutputStream(new File(directory, filename));

                // Convert the string to bytes and write to the file
                byte[] contentBytes = p.getBytes();
                outputStream.write(contentBytes);

                // Close the output stream
                outputStream.close();

                System.out.println("Content has been written to the file in the specified directory.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                // Create the directory if it doesn't exist
                File directory = new File(currentPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Create a FileOutputStream for the specified file
                FileOutputStream outputStream = new FileOutputStream(new File(directory, filename));

                // Convert the string to bytes and write to the file
                byte[] contentBytes = text.getBytes();
                outputStream.write(contentBytes);

                // Close the output stream
                outputStream.close();

                System.out.println("Content has been written to the file in the specified directory.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void reidrect(String text,String filename) throws IOException {

        File file = new File(currentPath(), filename);
        String p = "";
        if (file.exists() && file.isFile()) {
            p = p.concat(text);
            try {
                // Create the directory if it doesn't exist
                File directory = new File(currentPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Create a FileOutputStream for the specified file
                FileOutputStream outputStream = new FileOutputStream(new File(directory, filename));

                // Convert the string to bytes and write to the file
                byte[] contentBytes = p.getBytes();
                outputStream.write(contentBytes);

                // Close the output stream
                outputStream.close();

                System.out.println("Content has been written to the file in the specified directory.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                // Create the directory if it doesn't exist
                File directory = new File(currentPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Create a FileOutputStream for the specified file
                FileOutputStream outputStream = new FileOutputStream(new File(directory, filename));

                // Convert the string to bytes and write to the file
                byte[] contentBytes = text.getBytes();
                outputStream.write(contentBytes);

                // Close the output stream
                outputStream.close();

                System.out.println("Content has been written to the file in the specified directory.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void mv(String[] args) {
       
        Path source = Paths.get(args[0]);
        Path target = Paths.get(args[1]);

        try {
            Files.move(source, target);
            System.out.println("File moved successfully.");
        } catch (IOException e) {
            System.err.println("Error moving file: " + e.getMessage());
        }

        }
    }

}
