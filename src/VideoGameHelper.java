/*
Written by Musa Azeem
This class contains the functionality to read from a file and populate a Linked List,
write to a file from a linked list, and to search a linked list for certain values
 */
import java.io.*;
import java.util.Scanner;
public class VideoGameHelper {
    private final String DELIM = "\t";
    private final int BODY_FIELD_AMT = 2;
    private final String WILDCARD = "*";
    private GenLL<VideoGame> VideoGamesLL;            //linked list for all video games loaded from a file
    private GenLL<VideoGame> currentResults;      //linked list for current result of search

    public VideoGameHelper(){
        // Default contructor initializes linked list as empty
        VideoGamesLL = new GenLL<VideoGame>();
        currentResults = new GenLL<VideoGame>();
    }
    public void readFile(String filename){
        // reads video games from a file given by user
        VideoGamesLL = new GenLL<VideoGame>();      //reset values of VideoGamesLL to null before reading from a new file
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
