import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

/*
Title:                          Lost in the woods
Name of file:                   Main.java
List of external files needed:  N/A
List of external files created: N/A
Name of Programmer:             Scott Tabaka
Email of Programmer:            satk5n@mail.umsl.edu
Course/Section Number:          CMP SCI 4500-001
Date finished/submitted:        1/29/2020
Purpose of program:             Creates a map(forest) using a 2d array with the dimensions given by user.
                                It then creates person in the top left and bottom right of the map.  These people move
                                around the map randomly until they meet or the time limit is reached(1000000).  Then the
                                program displays the coordinate location the people met, time taken to find each other,
                                and also the average time taken to find each other in 100 runs.  It also displays a map
                                of the coordinates using text showing the start and finish states of the program.
Resources used:                 StackOverflow.com,W3schools.com
*/


public class Main
{
    private static int A = 0;                   //width or columns
    private static int B = 0;                   //height or rows
    private static String[][] worldArray;       //declaration of 2d array for displaying map
    private static Random rand = new Random();  //declaration of random number variable

    private static void initializeMap(int[][] personLocation)   //Function to populate map with Pat and Chris's location
    {
        int A = worldArray.length;
        int B = worldArray[0].length;

        //Loop for filling in each location of the entire map with "." to show empty
        for(int i = 0; i < B; i++)  //for each row
        {
            for(int j = 0; j < A; j++)  //for each column
            {
                worldArray[j][i] = ".";     //symbol for an empty location
            }
        }

            setLocation(personLocation);        //Function call to populate map with Pat and Chris's location
    }

    private static void printMap(String s)      //Function to display the map
    {
        int A = worldArray.length;      //local variable to calculate map dimensions
        int B = worldArray[0].length;   //local variable to calculate map dimensions

        //Spacing for a title
        for(int i = 0; i < A-3; i++)
        {
            System.out.print(" ");
        }
        System.out.println(s);

        //Loop to display the contents of the "forest"
        for(int i = 0; i < B; i++)  //for each row
        {
            for(int j = 0; j < A; j++)  //for each column
            {
                System.out.print(worldArray[j][i]); //Display the contents of each location in the "forest"
                System.out.print(" ");  //Inputs a space between each location in the "forest" when displaying
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void findPeople(int[][] personLocation)      //Function to move both people until they find each other
    {
        int A = worldArray.length;          //local variable to calculate map dimensions
        int B = worldArray[0].length;       //local variable to calculate map dimensions
        int count = 0;                      //variable for counting each iteration of movement
        int maxTimeUnits = 1000000;         //Preset time limit

        initializeMap(personLocation);                 //function call to populate the map display with
        printMap("Start");                           //function call to display the map

        //loop that continues until people find each other or the time limit is reached
        while((count < maxTimeUnits) && !Arrays.equals(personLocation[0], personLocation[1]))
        {
            movePeople(A,B,personLocation);
            count++;
        }

        if(Arrays.equals(personLocation[0], personLocation[1])) //display info if Pat and Chris found each other
        {
            initializeMap(personLocation);
            printMap("Finish");

            System.out.println("Pat and Chris found each other at (" + (personLocation[0][0] +1 ) + "," + (personLocation[0][1] +1 ) + ")");
            System.out.println("Time units passed: " + count);

        } else if(count == maxTimeUnits)        //display info if time expired before Pat and Chris found each other
        {
            initializeMap(personLocation);
            printMap("Finish");

            System.out.println("Time expired, Pat and Chris did not find each other");
            System.out.println("Time units passed: " + count);
        }
    }

    private static void movePeople(int A,int B,int[][] personLocation)  //Function to move both people
    {
        int[] Pat = {personLocation[0][0],personLocation[0][1]};    //Transfers Pat's location info into a 1d array
        int[] Chris = {personLocation[1][0],personLocation[1][1]};  //Transfers Chris's location info into a 1d array

        Pat = movePerson(A,B,Pat);      //Function call to move Pat and return new location
        personLocation[0][0] = Pat[0];  //Stores new column location
        personLocation[0][1] = Pat[1];  //Stores new row location
//        System.out.println("P:" + personLocation[0][0] +"," + personLocation[0][1]);  //For debugging

        Chris = movePerson(A,B,Chris);      //Function call to move Chris and return new location
        personLocation[1][0] = Chris[0];    //Stores new column location
        personLocation[1][1] = Chris[1];    //Stores new row location
//        System.out.println("C:" + personLocation[1][0] +"," + personLocation[1][1]);  //For debugging
    }

    private static int[] movePerson(int A, int B, int[] person)     //Function to move a person in a direction depending on random number
    {
        int randomNum = getRandomNumber(1,8);   //Function call to generate a random number between 1-8 and store it in a variable

//        System.out.println("RN:" + randomNum);    //For debugging

        switch (randomNum)      //Switch for determining movement direction of Pat and Chris
        {
            case 1: //Moves person N if possible
                if(person[1] > 0)
                {
                    person[1] = person[1]-1;
                }
                break;
            case 2: //NE
                if(person[1] > 0 && person[0] < (A-1))
                {
                    person[1] = person[1]-1;
                    person[0] = person[0]+1;
                }
                break;
            case 3: //E
                if(person[0] < (A-1))
            {
                person[0] = person[0]+1;
            }
                break;
            case 4: //SE
                if(person[1] < (B-1) && person[0] < (A-1))
                {
                    person[1] = person[1]+1;
                    person[0] = person[0]+1;
                }
                break;
            case 5: //S
            if(person[1] < (B-1))
            {
                person[1] = person[1]+1;
            }
                break;
            case 6: //SW
                if(person[1] < (B-1) && person[0] > 0)
                {
                    person[1] = person[1]+1;
                    person[0] = person[0]-1;
                }
                break;
            case 7: //W
                if(person[0] > 0)
            {
                person[0] = person[0]-1;
            }
                break;
            case 8: //NW
                if(person[1] > 0 && person[0] > 0)
                {
                    person[1] = person[1]-1;
                    person[0] = person[0]-1;
                }
                break;
            default:
                System.out.println("Invalid Random Number");
                break;
        }

        return person;
    }

    private static int getRandomNumber(int min, int max)    //Function to generate a random number
    {
        return (min + rand.nextInt((max - min) + 1));
    }

    private static void setLocation(int[][] loc)        //Function to populate map with locations of people
    {
        if(Arrays.equals(loc[0], loc[1]))
        {
            worldArray[loc[0][0]][loc[0][1]] = "O";     //Will input "O" where Pat and Chris meet
        } else
        {
            worldArray[loc[0][0]][loc[0][1]] = "P";     //Will input "P" where Pat is currently standing
            worldArray[loc[1][0]][loc[1][1]] = "C";     //Will input "C" where Chris is currently standing
        }
    }

    private static void getInput()          //Function to get user input for width and height of "forest"
    {
        System.out.println("\t\t\t*** LOST IN THE WOODS(A Forest Wandering Simulation) ***");
        System.out.println("\tWelcome! This simulation will begin by asking for 2 integers(2-50) for the dimensions ");
        System.out.println("of a forest. The forest will contain two people that start in opposite corners which wander randomly ");
        System.out.println("around until they find each other. Once the dimension have been entered you will see how long it took ");
        System.out.println("for the two people to find each other.\n");

        Scanner scanner = new Scanner(System.in);

        //loop for getting the correct width input
        while(A < 2 || A > 50)      //Insures input is from 2-50
        {
            System.out.println("Please enter a width for the forest(2-50)");
            A = scanner.nextInt();

            if(A < 2 || A > 50)
            {
                System.out.println("***Invalid input***");      //Error message if input is incorrect
            }
        }

        //loop for getting the correct height input
        while(B < 2 || B > 50)
        {
            System.out.println("Please enter a height for the forest(2-50)");
            B = scanner.nextInt();

            if(B < 2 || B > 50)
            {
                System.out.println("***Invalid input***");
            }
        }
    }

    private static void waitForEnter()       //function to wait for the "Enter" key to be pressed
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter key to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args)
    {
        getInput();                                     //Function call to get dimensions of "forest" from user

        worldArray = new String[A][B];                  //initialization of 2d array with user dimensions for displaying map
        int[][] personLocation = {{0,0},{A-1,B-1}};     //declaration of 2d array for storing the locations of Pat(0) and Chris(1)

        findPeople(personLocation);                    //function call to move both people until they find each other
        waitForEnter();                                //function call to wait until the "Enter" key is pressed
    }
}