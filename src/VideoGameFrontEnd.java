/*
Written by Musa Azeem
This class acts as the front end, where the user chooses how to use the program
 */
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
public class VideoGameFrontEnd {
    public static Scanner kb = new Scanner(System.in);
    public static VideoGameHelper v = new VideoGameHelper();
    public static void main(String[] args){
        System.out.println("Welcome to the video game database!");
        boolean repeat = true;
        while(repeat){
            printChoices();
            int choice = getValidIntegerInput();
            switch(choice){
                case 0:
                    repeat = false;
                    break;
                case 1:
                    readFromFile();
                    break;
                case 2:
                    searchDatabase();
                    break;
                case 3:
                    v.printResult();
                    break;
                case 4:
                    writeToFile();
                    break;
            }
        }
        System.out.println("Goodbye");
    }
    public static void printChoices(){
        System.out.println("\nEnter 1 to load the video game database\n" +
                "Enter 2 to search the database\n" +
                "Enter 3 to print current results\n" +
                "Enter 4 to print current results to file\n" +
                "Enter 0 to quit");
    }
    public static int getValidIntegerInput(){
        boolean repeat = true;
        int ret = 0;
        while(repeat){
            try{
                String strInput = kb.nextLine();
                ret = Integer.parseInt(strInput);
            }
            catch(NumberFormatException e){
                System.out.println("Enter a valid number");
                continue;
            }
            if(ret<0 || ret>4){
                System.out.println("Enter 0, 1, 2, 3, or 4");
                continue;
            }
            repeat = false;
        }
        return ret;
    }
    public static boolean getBooleanInput(){
        boolean repeat = true;
        boolean ret = false;
        while(repeat) {
            repeat = false;
            String strInput = kb.nextLine();
            if(strInput.toLowerCase().equals("true") || strInput.toLowerCase().equals("false")){
                ret = Boolean.parseBoolean(strInput.toLowerCase());
            }
            else {
                repeat = true;
                System.out.println("Enter true or false");
            }
        }
        return ret;
    }
    public static void readFromFile(){
        System.out.println("Enter the file name: ");
        String file = kb.nextLine();
        v.readFromFile(file);
    }
    public static void searchDatabase(){
        System.out.println("Enter the name of the game or '*' for all names");
        String findName = kb.nextLine();
        System.out.println("Enter the name of the console or '*' for all consoles");
        String findConsole = kb.nextLine();
        v.search(findName,findConsole);
        v.printResult();
    }
    public static void writeToFile(){
        System.out.println("Enter the file name to print out.");
        String file = kb.nextLine();
        System.out.println("Append to file? True or false.");
        boolean append = getBooleanInput();
        v.writeToFile(file,append);
    }
}
