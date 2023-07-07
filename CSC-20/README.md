CSC 20: Programming Concepts and Methodology II


Final Project Specifications:

Make a Movie Database program

The purpose of this project is to give you a larger-scale project that incorporates many of the concepts and ideas you have learned in this course. 
It is also to give you a chance to make something that would have real-world application. This course attempted to blend theoretical and academic 
computer science concepts with practical software development while teaching you how to implement object-oriented programming techniques. Think of 
this project as a sort of “capstone” of your Java studies this semester. This project is a bit more “free form” than the others so you are free to 
choose how to make it with a few constraints mentioned below. Complete all of the specifications below for full credit. Partial credit will be 
awarded for partially finished projects based on my determination of how much of the workload to completion you finished. You do need a bare minimum 
of an interactive interface (keyboard entry portion) to receive any credit. Without interaction, this program is not worthwhile. Good luck!

Steps:

Read the assignment specifications below and do everything mentioned if you wish to receive full credit. I will deduct points for parts that are not 
implemented (or not done correctly.) Your program must run without errors or warnings for full credit.
Final Project Specifications

Background

You are tasked by your Project lead to create a console-based Movie Database Management System. This application must be able to handle creating new 
entries in a proprietary database as well as returning searches for items in the current database.

! Use the filename “db.txt” as your database file to save to and load in upon program start!

You MUST implement the following for full credit:

Start the program by displaying the title of your program (you come up with a name)
Have a primary prompt that allows you to enter commands
            (e.g. “Enter command >” )

Interpret primary commands and move to secondary states based on the commands entered. Commands you must support at the primary prompt are:
            a.) new entry

            b.) search by actor

            c.) search by year

            d.) search by runtime (in minutes)

            e.) search by director

            f.) search by title

            g.) quit

Your secondary states must do the following:
            For “new entry” → Prompts to do the following:     

                        Enter title >

                        Enter year >

                        Enter runtime (minutes) >

                        Enter actor 1 >

                        Enter actor 2 >

                        Enter Director >

            You must add this entry into the database in one-line (use delimiters between tokens; come up with your own strategy for token order but BE CONSISTENT!)  

After entry, return to the main command state...
            For “search by actor” → Prompts to:

                        Enter actor >

            Once actor is entered, search database (hopefully already in memory.) Display a list of movie titles that the actor has been in. This must display 
the titles of movies actor has been in regardless of if actor was actor 1 or actor 2. (If nothing found, display “No titles found for actor” and return to main prompt.)

After entry, return to the main command state...
            For “search by year” → Prompt to:

                        Enter year >

            Once year is entered, search database and return list of titles made in that year. (If nothing found, display “No titles found for year” and return to main prompt.)

After entry, return to the main command state...
            For “search by runtime” → Prompts to:

                        Enter runtime (minutes) >

            Search like how you did for year. Display list and return to main prompt. If nothing found, display a prompt saying so and return to main prompt.

            For “search by director” → Prompts to:

                        Enter director >

            Do the same thing as you did with the actor search. Return list of titles the director has done and return to main prompt. If none found, post message and return to main prompt.

After entry, return to the main command state...
            For “search by title” → Prompts to:

                        Enter title >

            If the title is found, return the following about title:

                        Actors: actor1, actor 2

                        Director: director name

                        Year: year made

                        Runtime: x minutes

            if title not found, post message saying so and return to main prompt.

After entry, return to the main command state.
            For “quit” → This simply exits the program (close out any open I / O streams prior to exit!)

Other Requirements:

            You must make this object-oriented. You need to have a MINIMUM of four (4) different classes interacting with the main method. The minimum classes are:

File I/O class (for saving / loading the database) You can make these two classes; one for read / one for write if you want.
Keyboard input class (handles declaring Scanner object, retrieving text input, closing object)
Class representing a single entry. Use this to easily pass entry information around as a parameter. This class must contain private fields for:
title, actor1, actor2, year, runtime, director
Also, use get methods (public) to retrieve data from fields
Have a class that extends the functionality of the single-entry class and holds the whole current database as a class object. (Hint: You can use an ArrayList behind-the-scenes to do this.)
Create get method that can lookup single entry based on certain criteria (index number, search by criteria, etc.) You can overload your get method if you wish.
You MUST save every entry added to the database to file. This entry must be able to be restored later.

You MUST check that a new entry is valid before adding it to memory (and the database.) To be valid, the entry must have at least a title (minimum length of valid title is three characters.) 
All other fields can be empty as needed.

The program MUST not have errors, warnings, or runtime errors (bugs) to receive full credit.

You MUST create at least fifty (50) valid entries for your database and submit this file as part of your .zip file deliverable. (You can research movie data at the Internet Movie Database at imdb.com)

Extra Credit Opportunity!

Receive 20 points of extra credit if you also add an option to “delete entry.” This must follow the following to get the credit:

            Have “delete entry” as a command that can be entered from the main prompt

            For “delete entry” → Prompts to:

                        Enter title to delete >

            Once title is entered, if it exists, delete it from memory (and the database file). If it isn't found, display “Title not found” and return to the main prompt.
