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
            Scanner fileScanner = new Scanner(new File(filename));
            String line = null;
            String[] splitLine = null;
            String videoGameName = null;
            String videoGameConsole = null;
            while(fileScanner.hasNextLine()){
                line = fileScanner.nextLine();           //read next line in file
                splitLine = line.split(DELIM);             //split line into two part at the tab delim
                if(splitLine.length == BODY_FIELD_AMT){      //check that line was formatted correctly
                    videoGameName = splitLine[0];                     //set name to the first field
                    videoGameConsole = splitLine[1];                  //set the console to the second field
                    VideoGamesLL.add(new VideoGame(videoGameName, videoGameConsole));                            //add new instance to the linked list
                }
            }
            fileScanner.close();
        }
        catch(FileNotFoundException f){
            System.out.println("File Not Found");
        }
    }
    public void writeToFile(String filename, boolean append){
        if(append){   // if user chooses to append to file
            try{
                PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
                while(currentResults.hasNext()){     // while video game at current exists
                    // print the name of the video game at current, the delim, and then the console to the file
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
                PrintWriter fileWriter = new PrintWriter(new FileOutputStream(filename));
                while (currentResults.hasNext()) {    //while the VideoGame at current exists
                    //print the name of the video game at current, the delim, and then the console to the file
                    fileWriter.println(currentResults.getCurrent().getName() + DELIM + currentResults.getCurrent().getConsole());
                    currentResults.gotoNext();
                }
                fileWriter.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            }
            currentResults.reset();   //reset current so that it starts back at the head
        }
    }

    // method to search for certain strings in the names and console of each video game
    public void search(String name, String console){
        currentResults = new GenLL<VideoGame>();    // reset currentResults
        /*
        create a temporary linked list. This list will contain the video games that
        contain the string the user entered, or will contain all the video games if the user entered *
        */
        GenLL<VideoGame> temp = new GenLL<VideoGame>();
        if(name.equals(WILDCARD)){ //if the user entered * for name,
            while(VideoGamesLL.hasNext()){
                temp.add(VideoGamesLL.getCurrent());  //copy the linked list of all video games onto the temp list
                VideoGamesLL.gotoNext();
            }
            VideoGamesLL.reset();
        }
        else {                          //if the user entered something other than *
            while (VideoGamesLL.hasNext()) {
                //check if the name of the video game at current contains the input string, ignoring case
                if (VideoGamesLL.getCurrent().getName().toLowerCase().contains(name.toLowerCase())) {
                    temp.add(VideoGamesLL.getCurrent());   //if so, add that video game to temp
                }
                VideoGamesLL.gotoNext();
            }
            VideoGamesLL.reset();
        }
        /*
        Once temp contains the desired video games based on name, if the user entered * add all the video games
        in temp to currentResults. Otherwise, add only the video games that contain the input string
         */
        if(console.equals(WILDCARD)){       //if user entered *
            while(temp.hasNext()){
                currentResults.add(temp.getCurrent());      //add all video games from temp to currentResults
                temp.gotoNext();
            }
        }
        else{     //if user entered something other than *
            while(temp.hasNext()){
                //check if the console of the video game at temp's current contains the input string, Ignoring case
                if(temp.getCurrent().getConsole().toLowerCase().contains(console.toLowerCase())){
                    currentResults.add(temp.getCurrent());     //if so, add it to currentResults
                }
                temp.gotoNext();
            }
        }
        temp.reset();
    }
    public void printAll(){
        VideoGamesLL.print();
    }
    public void printResult(){
        currentResults.print();
    }
}
