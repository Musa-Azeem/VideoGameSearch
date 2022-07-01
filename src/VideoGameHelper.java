/*
Author:     Musa Azeem
Date:       3/29/21
This file implements the VideoGameHelperClass, which contains the methods that provide the 
    functionality of the program to load a video game database, search for certain entries, 
    and write the results to a file or the console.

Class Members:
    DELIM (String):         delimeter (tab) of database file
    BODY_FIELD_AMT (int):   number of fields in a correctly formatted database file
    WILDCARD (String):      string that serves a wildcard for search
    VideoGamesLL (GenLL<VideoGame>):    Linked List of all entries loaded from the database
    currentResults (GenLL<VideoGame>):  Linked list of entries found during the most recent search

Class Methods:
    Default Constructor:    Initializes instance with empty linked lists
    readFile:       reads a database file and loads all entries into VideoGameLL
    writeToFile:    writes or appends all entries in currentResults to a file
    search:         Searches VideoGamesLL for entries based on the video game title or console, and saves results to currentResults
    printAll:       prints all entries in VideoGamesLL
    printResults:   prints all entries in currentResults
*/
import java.io.*;
import java.util.Scanner;
public class VideoGameHelper {
    private final String DELIM = "\t";
    private final int BODY_FIELD_AMT = 2;
    private final String WILDCARD = "*";
    private GenLL<VideoGame> VideoGamesLL;              //linked list for all video games loaded from a file
    private GenLL<VideoGame> currentResults;            //linked list for current result of search

    public VideoGameHelper(){
        /*
        Default contructor initializes VideoGamesLL and currentResults as empty linked lists
        */

        VideoGamesLL = new GenLL<VideoGame>();
        currentResults = new GenLL<VideoGame>();
    }
    public void readFile(String filename){
        /*
        Reads a database file and loads entries into VideoGamesLL
        If a line is not formatted correctly, the line is skipped
        If the file cannot be opened, a message indicating so is printed to the console

        Parameters:
            filename (String):  name of file to read
        */

        VideoGamesLL = new GenLL<VideoGame>();      //reset VideoGamesLL to empty before reading from a new file
        try{
            Scanner fileScanner = new Scanner(new File(filename));      // Scanner to read file
            String[] line = null;
            while(fileScanner.hasNextLine()){
                line = fileScanner.nextLine().split(DELIM);             // read next line in file and split into fields [title, console]
                if(line.length == BODY_FIELD_AMT)                       //check that line was formatted correctly
                    VideoGamesLL.add(new VideoGame(line[0], line[1]));  //add new entry to the linked list
            }
            fileScanner.close();
        }
        catch(FileNotFoundException f){
            System.out.println("File Not Found");   // If unable to open file, report to user
        }
    }

    public void writeToFile(String filename, boolean append){
        /*
        Writes all entries in currentResults to a file
        If append is true, it appends to the end of the file rather than overwriting it

        Parameters:
            filename (String):  name of file to write to
            append (boolean):   true if appending
        */

        if(append){   // if user chooses to append to file
            try{
                PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));   // fileWriter to append to file
                while(currentResults.hasNext()){
                    // print the name of each video game, the delimiter, and then the console name to the file
                    fileWriter.append(currentResults.getCurrent().getName()+DELIM+currentResults.getCurrent().getConsole()+"\n");
                    currentResults.gotoNext();
                }
                fileWriter.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else {    //if user chooses to write new file or overwrite
            try {
                PrintWriter fileWriter = new PrintWriter(new FileOutputStream(filename));   // fileWriter to overwrite file
                while (currentResults.hasNext()) {
                    // print the name of each video game, the delimiter, and then the console name to the file
                    fileWriter.println(currentResults.getCurrent().getName() + DELIM + currentResults.getCurrent().getConsole());
                    currentResults.gotoNext();
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentResults.reset();     // reset current pointer back to the head
        }
    }

    // method to search for certain strings in the names and console of each video game
    public void search(String name, String console){
        /*
        Searches VideoGamesLL for entries based on a search string for the name and a search string for the console of the video game
        if the search string is contained anywhere within the entry, it is considered a match
        If the user enters "*" for either category, all entries are matched for that category

        Parameters:
            name (String):      search string to find in video game names
            console (String):   search string to find in video game console names
        */

        currentResults = new GenLL<VideoGame>();    // reset currentResults

        /*
        Create a temporary linked list. This list will contain the video games whose names contain the
        name search string the user entered, or will contain all the video games if the user entered *
        */
        GenLL<VideoGame> temp = new GenLL<VideoGame>();

        if(name.equals(WILDCARD)){                      //if the user entered * for name,
            while(VideoGamesLL.hasNext()){
                temp.add(VideoGamesLL.getCurrent());    //copy the linked list of all video games onto the temp list
                VideoGamesLL.gotoNext();
            }
            VideoGamesLL.reset();
        }
        else {                          //if the user entered something other than *
            while (VideoGamesLL.hasNext()) {
                //check if each video game's name contains the name search string, ignoring case
                if (VideoGamesLL.getCurrent().getName().toLowerCase().contains(name.toLowerCase())) {
                    temp.add(VideoGamesLL.getCurrent());   //if so, add that video game to temp
                }
                VideoGamesLL.gotoNext();
            }
            VideoGamesLL.reset();
        }

        /*
        Once temp contains the desired video games based on name, if the user entered "*" add all the video games
        in temp to currentResults. Otherwise, add only the video games that contain the console search string
         */
        if(console.equals(WILDCARD)){                       // if user entered *
            while(temp.hasNext()){
                currentResults.add(temp.getCurrent());      // add all video games from temp to currentResults
                temp.gotoNext();
            }
        }
        else{                                               //if user entered something other than *
            while(temp.hasNext()){
                //check if the console of the video game at temp's current contains the input string, Ignoring case
                if(temp.getCurrent().getConsole().toLowerCase().contains(console.toLowerCase())){
                    currentResults.add(temp.getCurrent());                          //if so, add it to currentResults
                }
                temp.gotoNext();
            }
        }
        temp.reset();
    }
    public void printAll(){
        /*
        Prints all entries in VideoGamesLL to the console
        */
        VideoGamesLL.print();
    }
    public void printResult(){
        /*
        Prints all entries in currentResults to console
        */
        currentResults.print();
    }
}
