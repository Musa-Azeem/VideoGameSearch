/*
Written by Musa Azeem

class for instances of a video game read from the file,
contains variables for the name and console of each video game
*/
public class VideoGame {
    private String name;
    private String console;

    public VideoGame(String aName, String aConsole){
        this.setName(aName);
        this.setConsole(aConsole);
    }
    public String getName(){
        return this.name;
    }
    public String getConsole(){
        return this.console;
    }
    public void setName(String aName){
        if(aName != null)
            this.name = aName;
        else
            this.name = "None";
    }
    public void setConsole(String aConsole){
        if(aConsole != null)
            this.console = aConsole;
        else
            this.console = "None";
    }
    public String toString(){
        return this.name+"\t"+this.console;
    }

}
