/*
Written by Musa Azeem

class for instances of a video game read from the file,
contains variables for the name and console of each video game
*/
public class VideoGame {
    private String name;
    private String console;

    public VideoGame(String name, String console){
        this.setName(name);
        this.setConsole(console);
    }
    public String getName(){
        return this.name;
    }
    public String getConsole(){
        return this.console;
    }
    public void setName(String name){
        if(name != null)
            this.name = name;
        else
            this.name = "None";
    }
    public void setConsole(String console){
        if(console != null)
            this.console = console;
        else
            this.console = "None";
    }
    public String toString(){
        return this.name+"\n\t"+this.console;
    }

}
