/*
Written by Musa Azeem
This method contains the functionality to read from a file and populate a Linked List,
write to a file from a linked list, and to search a linked list for certain values
 */
import java.io.*;
import java.util.Scanner;
public class VideoGameHelper {
    private final String DEL = "\t";
    private final int BODY_FIELD_AMT = 2;
    private GenLL<VideoGame> vLL;            //linked list for all video games loaded from a file
    private GenLL<VideoGame> resultVLL;      //linked list for current result of search

    public VideoGameHelper(){
        vLL = new GenLL<VideoGame>();
        resultVLL = new GenLL<VideoGame>();
    }
    //reads from a file given by user
    public void readFromFile(String aN){
        vLL = new GenLL<VideoGame>();  //reset values of vLL to null before reading from a new file
        try{
            Scanner fileScanner = new Scanner(new File(aN));
            String fileLine = null;
            String[] splitLine = null;
            String name = null;
            String console = null;
            while(fileScanner.hasNextLine()){
                fileLine = fileScanner.nextLine();           //read next line in file
                splitLine = fileLine.split(DEL);             //split line into two part at the tab delim
                if(splitLine.length == BODY_FIELD_AMT){      //check that line was formatted correctly
                    name = splitLine[0];                     //set name to the first field
                    console = splitLine[1];                  //set the console to the second field
                    VideoGame aVG = new VideoGame(name, console);    //create new instance of a Video Game using the data
                    vLL.add(aVG);                            //add new instance to the linked list
                }
            }
            fileScanner.close();
        }
        catch(FileNotFoundException f){
            System.out.println("File Not Found");
        }
    }
    public void writeToFile(String aN, boolean append){
        if(append){   //if user chooses to append to file
            try{
                PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(aN, true)));
                while(resultVLL.hasNext()){     //while video game at current exists
                    // print the name of the video game at current, the delim, and then the console to the file
                    fileWriter.append(resultVLL.getCurrent().getName()+DEL+resultVLL.getCurrent().getConsole()+"\n");
                    resultVLL.gotoNext();
                }
                fileWriter.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else {    //if user chooses to write new file or overwrite
            try {
                PrintWriter fileWriter = new PrintWriter(new FileOutputStream(aN));
                while (resultVLL.hasNext()) {    //while the VideoGame at current exists
                    //print the name of the video game at current, the delim, and then the console to the file
                    fileWriter.println(resultVLL.getCurrent().getName() + DEL + resultVLL.getCurrent().getConsole());
                    resultVLL.gotoNext();
                }
                fileWriter.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            }
            resultVLL.reset();   //reset current so that it starts back at the head
        }
    }

    //method to search for certain strings in the names and console of each video game
    public void search(String findName, String findConsole){
        resultVLL = new GenLL<VideoGame>();    //reset all values in resultVLL to null
        /*
        create a temporary linked list. This list will contain the video games that
        contain the string the user entered, or the list will contain all the video games if the user entered *
        */
        GenLL<VideoGame> temp = new GenLL<VideoGame>();
        if(findName.equals("*")){ //if the user entered * for name,
            while(vLL.hasNext()){
                temp.add(vLL.getCurrent());  //copy the linked list of all video games onto the temp list
                vLL.gotoNext();
            }
            vLL.reset();
        }
        else {                          //if the user entered something other than *
            while (vLL.hasNext()) {
                //check if the name of the video game at current contains the input string, ignoring case
                if (vLL.getCurrent().getName().toLowerCase().contains(findName.toLowerCase())) {
                    temp.add(vLL.getCurrent());   //if so, add that video game to temp
                }
                vLL.gotoNext();
            }
            vLL.reset();
        }
        /*
        Once temp contains the wanted video games based on name, if the user entered * add all the video games
        in temp to resultVLL. Otherwise, add only the video games that contain the input string
         */
        if(findConsole.equals("*")){       //if user entered *
            while(temp.hasNext()){
                resultVLL.add(temp.getCurrent());      //add all video games from temp to resultVLL
                temp.gotoNext();
            }
        }
        else{                              //if user entered something other than *
            while(temp.hasNext()){
                //check if the console of the video game at temp's current contains the input string, Ignoring case
                if(temp.getCurrent().getConsole().toLowerCase().contains(findConsole.toLowerCase())){
                    resultVLL.add(temp.getCurrent());     //if so, add it to resultVLL
                }
                temp.gotoNext();
            }
        }
        temp.reset();
    }
    public void printAll(){
        vLL.print();
    }
    public void printResult(){
        resultVLL.print();
    }
}
