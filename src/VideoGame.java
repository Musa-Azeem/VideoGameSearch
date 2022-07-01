/*
Author:     Musa Azeem
Date:       3/29/21
This file implements the VideoGame, which defines an instance of a video game

Class Members:
    name (String):      title of video game
    console (String):   name of the console the video game is made for

Class Methods:
    Constructor:    sets name and console members
    getName:    Accessor for name member
    getConsole: Accessor for console member
    setName:    Mutator for name member
    setConsole: Mutator for console member
    toString:   prints video game title and console name to the console
*/

public class VideoGame {
    private String name;
    private String console;

    public VideoGame(String name, String console){
        /*
        Constructor - sets name and console members
        
        Parameters:
            name (String):      name of video game
            console (String):   name of video game console
        */

        this.setName(name);
        this.setConsole(console);
    }
    public String getName(){
        /*
        Accessor for name member

        Returns:
            String: name of video game
        */

        return this.name;
    }
    public String getConsole(){
        /*
        Accessor for console member

        Returns:
            String: console name of video game
        */

        return this.console;
    }
    public void setName(String name){
        /*
        Mutator for name member
        If argument is null, name is set to "None"

        Parameters:
            name (String):  name of video game
        */

        if(name != null)
            this.name = name;
        else
            this.name = "None";
    }
    public void setConsole(String console){
        /*
        Mutator for console member
        Only sets console if argument is not null

        Parameters:
            console (String):   console name of video game
        */
        if(console != null)
            this.console = console;
        else
            this.console = "None";
    }
    public String toString(){
        /*
        Prints video game name and console to the console
        */
        
        return this.name+"\n\t"+this.console;
    }

}
