# Video Game Search

This project loads a database of video games and searches for video games  
based on their titles and console, with functionality to save the results to  
a new file.

## Requirements
Unix system with java installed

## Directory Structure
    .
    ├── makefile
    ├── README.md
    ├── res
    │   └── Collection.txt
    └── src
        ├── GenLL.java
        ├── VideoGameFrontEnd.java
        ├── VideoGameHelper.java
        └── VideoGame.java

## Usage:
### Commands
    clean repository:   make clean
    compile project:    make compile
    run project:        make run
### Running the Project
- Options will be displayed upon running the project
- First, load a database into the project by entering 1
    - an example video game database is locating is the `res` directory
- Enter 2 to search the database by Video Game title or by the name of the  
    console it runs on
    - entering `*` for either category will include all results
- Enter 3 to print the most recent results to the console
- Enter 4 to save the most recent results to a specified filename
    - if file already contains data, choose append when given the option to  
        append the search results to the end of the file
- To stop the program, enter 0
### Database Format
- The database is a tab delimited plain text file
- Format: `Title` \t `Console`


## Project Classes
### VideoGameFrontEnd
- This class contains the main method and other helper methods, and serves as  
    the interface to run the program
- In a continous event loop, users are provided options to run different tasks
- It is defined in the file `src/VideoGameFrontEnd.java`

### VideoGameHelper
- This class contains helper methods to provide functionality for the search,  
    file reading, and file writing capabilities of the program
- It creates and manages a linked list of VideoGame objects
- It is defined in the file `src/VideoGameHelper.java`

### VideoGame
- This class defines an instance of a video game, defining its title and the  
    console it runs on
- It is defined in the file `src/VideoGame.java`

### GenLL
- This class is an implementation of a generic linked list, with all the  
    standard features
- It is defined in the file `src/GenLL.java`