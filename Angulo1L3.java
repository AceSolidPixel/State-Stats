/*
Prince Angulo 
Date: 9/8/2020
CECS 277
Description: Display statistics about the US states and territories and their populations.
*/
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Angulo1L3
{
   public static void main(String[] args)
   {
      final int FIRST_MENU_OPTION = 1; //Set a boundary minimum value for the menu
      final int TOTAL_MENU_OPTIONS = 6; //Set a maximum value for the menu
      
      Scanner in = new Scanner(System.in);
      ArrayList <String> states = new ArrayList <String> ();       //Declare two ArrayLists that will run parallel to each other: 
      ArrayList <Integer> populations = new ArrayList <Integer> ();//One with State Names and one with their respective populations
      readFile(states, populations);
      
      int input = 0; // set a default statement for input that isn't 6
      while(input != 6)
      {
         menu();
         input = getIntRange(FIRST_MENU_OPTION, TOTAL_MENU_OPTIONS);
         System.out.println();
         switch(input)
         {
            case 1:   
               sortStates(states, populations);
               displayStates(states, populations);
               System.out.println();
               break;
               
            case 2:
               sortPopulation(states, populations);
               displayStates(states, populations);
               System.out.println();
               break;
               
            case 3:
               System.out.printf("US Population = %,d", totalSum(populations));
               System.out.println();
               System.out.println();
               break;
            case 4:
               medianState(states, populations);
               System.out.println();
               break;
            case 5:
               populationGreater(states, populations);
               System.out.println();
               break;
               
            default:
               break;
         }
      }
   }
/*
   The readFile function reads the names and populations of states in from an input file with a specific name,
   and stores them into two parallel ArrayLists, one stores names and the other stores populations
   
   @param stateNames ArrayList that stores state names 
   @param pops ArrayList that stores state populations 
*/
   public static void readFile(ArrayList <String> stateNames, ArrayList<Integer> pops)
   {
      try
      {
         Scanner readFile = new Scanner(new File("StatePops.txt"));
         while(readFile.hasNext())
         {
            String line = readFile.nextLine();
            String [] nameAndPopulation = line.split(",");
            stateNames.add(nameAndPopulation[0]);
            pops.add(Integer.parseInt(nameAndPopulation[1]));
         } 
      }catch(FileNotFoundException e) //Checks to see if the file is in the same folder as this program
       {                              //Also Checks to see if the text file has the right name
         System.out.println("File Not Found - place in project folder");
       }      
   }
/*
   The sortStates function sorts the parallel ArrayLists of data in alphabetical order by the states name
   using a bubblesort algorithm and also moves the populations in tandem with the state names
  
   @param  stateNames  ArrayList of state names
   @param  pops   ArrayList of state populations
*/   
   public static void sortStates(ArrayList<String> stateNames, ArrayList<Integer> pops)
   {
      for (int i = 0; i < stateNames.size() - 1; ++i)
      {
         for (int j = 0; j < stateNames.size() - i - 1; ++j)
         {
            if (stateNames.get(j).compareToIgnoreCase(stateNames.get(j + 1)) > 0)
            {
               Collections.swap(stateNames, j, j + 1);
               Collections.swap(pops, j, j + 1);
            }
         }
      }
   }
/*
   The sortPopulation function sorts the parallel arraylists of data in increasing order of population size
   using a bubblesort algorithm and also moves the state names in tandem with the state names
  
   @param  stateNames  ArrayList of state names
   @param  pops   ArrayList of state populations
*/   
   public static void sortPopulation(ArrayList<String> stateNames, ArrayList<Integer> pops)
   {
      for (int read = 0; read < stateNames.size() - 1; ++read)
      {
         for (int sort = 0; sort < stateNames.size() - read - 1; ++sort)
         {
            if (pops.get(sort) > pops.get(sort + 1))
            {
               Collections.swap(stateNames, sort, sort + 1);
               Collections.swap(pops, sort, sort + 1);
            }
         }
      }
   }
   
/*
   The displayStates method prints the names of US states and territories and their respective populations.
    
   @param stateNames   ArrayList of state names 
   @param pops   ArrayList of state populations   
*/   
   public static void displayStates(ArrayList<String> stateNames, ArrayList<Integer> pops)
   {
      for(int i = 0; i <= stateNames.size() -1; i++)
      {
         System.out.printf("%s %,d ",stateNames.get(i), pops.get(i));
         System.out.println();
      }
   }
   
/*
   The totalSum method calcualtes the total US population by adding up all of the values stored in the ArrayList
   containing the populations of all states and territories
   
   @param ArrayList<Integer> pops is an ArrayList containing the popluation of each state and territory of 
   the US.
*/   
   public static int totalSum(ArrayList<Integer> pops)
   {
      int sum = 0;
      for(int i = 0; i <= pops.size() - 1; i++)
      {
         sum += pops.get(i);
      }
      return sum;
   }
/*
   The medianState function finds the state/states with the median population and prints it/them
   (Depending on whether the ArrayLists contain an odd or an even number of indexes)  
  
   @param stateNames ArrayList of states names used for printing 
   @param pops ArrayList of states populations used for finding median and printing 
*/   
   public static void medianState(ArrayList<String> stateNames, ArrayList<Integer> pops)
   {
      sortPopulation(stateNames, pops);
      int medianIndex = (stateNames.size() - 1) / 2; //find which index is in the very middle or the middle-left
      if (stateNames.size() % 2 != 0) //If the amount of indexes is odd, then only one value is the median
      {
         System.out.printf("%s %,d ",stateNames.get(medianIndex), pops.get(medianIndex));
         System.out.println("");
      }
      else //if there is an even amount of indexes, then there are two median values
      {
         System.out.printf("%s %,d ",stateNames.get(medianIndex), pops.get(medianIndex));
         System.out.println("");
         System.out.printf("%s %,d ",stateNames.get(medianIndex + 1), pops.get(medianIndex + 1));
         System.out.println("");
      }
  }
/*
   The populationGreater method prints the names and population of US states and territories greater than an user  
   input value.
  
  @param stateNames is an ArrayList containing the name of each state and territory of the US. 
  @param pops is an ArrayList containing the popluation of each state and territory of the US. 
*/  
   public static void populationGreater(ArrayList<String> stateNames, ArrayList<Integer> pops)
   {
      Scanner in = new Scanner(System.in);
      int input = getPositiveInt(); //Populations cannot be negative, so the user must input a positive number
      sortPopulation(stateNames, pops); //Call sortPopulation becuase the ArrayList needs to be sorted before checking the input
      System.out.println();
      boolean checker = true;
      int position = 0;
      while(checker)
      {
         for(int i = 0; i < pops.size() - 1; i++)
         {
            if(input > pops.get(i))
               position += 1;
            else
               checker = false;
         }
      }
      for( ; position <= pops.size() - 1; position++)
      {
         System.out.printf("%s %,d ",stateNames.get(position), pops.get(position));
         System.out.println("");
      }
   }
/*
   The getIntRange function prompts the user to enter a number between a start number(whatever is declared for FIRST_MENU_OPTION)
   and an end number (the value for TOTAL_MENU_OPTIONS) and continues looping until the user does so
   
   @param startNum Lower Bounds of integer range the user can enter
   @param endNum Upper Bounds of integer range the user can enter
   @return Integer entered by user within passed in range
*/   
   public static int getIntRange(int startNum, int endNum)
   {
      Scanner input = new Scanner(System.in);
      boolean repeat = true;
      int returnVal = 0;
      while (repeat)
      {
         returnVal = 0;
         try
         {
            System.out.print("Please enter an integer between " + startNum + " and " + endNum + ": ");
            returnVal = input.nextInt();
            if (returnVal <= endNum && returnVal >= startNum)
            {
               repeat = false;
            }
            else
            {
               System.out.println("Invalid Input ");
            }
         }catch(InputMismatchException e)
          {
            if (input.hasNext())
            {
               String stuff = input.next();
            }
            System.out.println("Invalid Input (It must be an integer between 1-6): ");
          }
        
       }
      return returnVal; 
   }
/*
   The getPostitiveInt function prompts the user to enter a positive integer and loops until
   the user does so.
  
   @return Positive Integer entered by the user
*/   
   public static int getPositiveInt()
   {
      Scanner input = new Scanner(System.in);
      boolean repeat = true;
      int returnVal = 0;
      while (repeat)
      {
         try
         {
            System.out.print("Please enter a positive integer: ");
            returnVal = input.nextInt();
            if (returnVal > 0)
            {
               repeat = false;
            }
            else
            {
               System.out.println("Your input must be positive. Please try again: ");
            }
         }catch(Exception e)
          {
             if (input.hasNext())
             {
               String stuff = input.next();
             }
             System.out.println("Invalid input(Your input must be an integer). Please try again: ");
          }
      }
    return returnVal;
  }
/*
   The displayMenu method prints to the console a menu of options for the user to select
   in oreder to learn about the population of the US
*/  
   public static void menu()
   {
      System.out.println("State Stats");
      System.out.println("1. Display Sorted States");
      System.out.println("2. Display Sorted Populations");
      System.out.println("3. Display Total US Population");
      System.out.println("4. Display Median State(s)");
      System.out.println("5. Display States with Population Greater Than");
      System.out.println("6. Quit");            
   }
}