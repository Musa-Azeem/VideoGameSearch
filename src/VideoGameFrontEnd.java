/*
Author:     Musa Azeem
Date:       3/29/21

This file impelements the VideoGameFrontEnd class, which serves as the user interface with the program

Class Members:
    kb (Scanner):           Reads user input from the console
    v (VideoGameHelper):    Manages videogame database

Class Methods:
    main:                   Runs continous loop, asking for user input and calling functions based on the selected option
    printChoices:           prints the user's options to the console
    getValidIntegerInput:   Recieves a valid integer value from the console
    getBooleanInput:        Recieves a valid Boolean input from the console
    readFile:               Recieves a filename from the console and calls the VideoGameHelper's method to read a file
    searchDatabase:         Recieves two search strings from the console adn calls the VideoGameHelper's method to search the database
    writeToFile:            Recieves a filename from the console and calls the VideoGameHelper's method to write to the file
 */
import java.util.Scanner;
public class VideoGameFrontEnd {
    public static Scanner kb = new Scanner(System.in);
    public static VideoGameHelper v = new VideoGameHelper();
    public static void main(String[] args){
        /*
        Main method runs a continous loop
        It prints the user choices and gets the user input
        Based on the user's choice, it calls one of the helper methods and continues, or ends loop

        Parameters: 
            args (String[]): command line arguments
        */

        System.out.println("Welcome to the video game database!");
        boolean repeat = true;
        while(repeat){  // loops until user enters 0
            // Print choices and get input
            printChoices();
            int choice = getValidIntegerInput();
            switch(choice){
                case 0:
                    // if user enters 0, end program
                    repeat = false;
                    break;
                case 1:
                    // if user enters 1, read database into program
                    readFile();
                    break;
                case 2:
                    // if user enters 2, search the database
                    searchDatabase();
                    break;
                case 3:
                    // if user enters 3, print results to the console with VideoGameHelper's method
                    v.printResult();
                    break;
                case 4:
                    // if user enters 4, write results to a file
                    writeToFile();
                    break;
            }   // close switch statement
        }   // close while loop
        System.out.println("Goodbye");
    }   // close main method

    public static void printChoices(){
        /*
        Prints user's options to the console
        */

        System.out.println("\nEnter 1 to load the video game database\n" +
                "Enter 2 to search the database\n" +
                "Enter 3 to print current results\n" +
                "Enter 4 to print current results to file\n" +
                "Enter 0 to quit");
    }

    public static int getValidIntegerInput(){
        /*
        Recieves an integer input from the user
        If the user inputs a non integer value, or an integer that is outside the options range,
            the method does not accept the input and loops again
        
        Return:
            int:    user input
        */

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
        /*
        Recieves a boolean input from the user
        If the user inputs a string that is not true or false, 
            the method does not accept the input and loops again
        
        Return:
            boolean:    user input
        */

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
    public static void readFile(){
        /*
        Recieves a filename from the console, and calls VideoGameHelper's readFile method
        */

        System.out.println("Enter the file name: ");
        String file = kb.nextLine();
        v.readFile(file);
    }
    public static void searchDatabase(){
        /*
        Recieves a search string for the video game title and one for the console, 
            and calls VideoGameHelper's search method
        The method then calls VideoGameHelper's printResult method to print the results to the console
        */

        System.out.println("Enter the name of the game or '*' for all names");
        String findName = kb.nextLine();
        System.out.println("Enter the name of the console or '*' for all consoles");
        String findConsole = kb.nextLine();
        v.search(findName,findConsole);
        v.printResult();
    }
    public static void writeToFile(){
        /*
        Recieves a filename from the console, and a boolean to append or not
        Method then calls VideoGameHelper's writeToFile method to write to the file
        */
        
        System.out.println("Enter the file name to print out.");
        String file = kb.nextLine();
        System.out.println("Append to file? True or false.");
        boolean append = getBooleanInput();
        v.writeToFile(file,append);
    }
}
