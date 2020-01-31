// import libraries
import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;

// the main class
public class ParkingSpotTracker{
	
	// the main method
	public static void main (String [] args) {
		
		// create keyboard object
		Scanner keyboard = new Scanner(System.in);
		
		// create a queue of names
		LinkedList<String> list = new LinkedList<String>();
		
		// create ints to store rows and columns of 2D array
		int row = 5;
		int col = 7;
		
		// create 2D array of booleans to store the parking lot
		boolean[][] parkingLot = new boolean[row][col];
		
		// use a method to initialize the parking spots
		initializeParkingLot(parkingLot,row,col);
		
		// boolean is true while user is using the system
		boolean using = true;
		
		// loop while the user is using the system
		while(using) {
			// tell the user what character to type to do something
			System.out.println("Type o to occupy a spot, d to "
					+ "deoccupy a spot, f to find a spot close to (0,0), "
					+ "v to view the parking lot, l to check the list, or q to quit");
			
			// receive action using a method
			char action = getActionInput(keyboard);
			
			// do something depending on the action
			switch(action) {
				// if action was o
				case 'o':
					// ask user to enter row and column to occupy
					System.out.println("Enter the row and column to occupy: ");
					
					// initialize variables for row and column
					int occupyRow = 0, occupyCol = 0;
					
					// enter try catch
					try {
						// receive input
						occupyRow = keyboard.nextInt();
						occupyCol = keyboard.nextInt();
					}
					// if there was an error, go here
					catch(Exception error) {
						// print error message and exit
						System.out.println("ERROR!!!");
						System.exit(-1);
					}
					
					// use occupy method to occupy user given row and column
					occupy(parkingLot,row,col,occupyRow,occupyCol);
					
					break;
					
				// if action was d
				case 'd':
					// ask user to enter row and column to deoccupy
					System.out.println("Enter the row and column to deoccupy: ");
					
					// initialize variables for row and column
					int deoccupyRow = 0, deoccupyCol = 0;
					
					// enter try catch
					try {
						// receive input
						deoccupyRow = keyboard.nextInt();
						deoccupyCol = keyboard.nextInt();
					}
					// if there was an error, go here
					catch(Exception error) {
						// print error message and exit
						System.out.println("ERROR!!!");
						System.exit(-1);
					}
					
					// use deoccupy method to deoccupy use given row and column
					deoccupy(parkingLot,row,col,deoccupyRow,deoccupyCol);
					
					break;
					
				// if action was f
				case 'f':
					// use find method to find a spot close to (0,0)
					find(parkingLot,row,col);
					break;
					
				// if action was v
				case 'v':
					// use method to print parking lot
					printParkingLot(parkingLot,row,col);
					break;
					
				// if action was q
				case 'q':
					// change boolean to false to leave while loop
					using=false;
					break;
				
				// if action was l
				case 'l':
				    caseIfLaction(list, keyboard);
				    break;
					
				// if anything else was entered
				default:
					// state that an invalid action characetr was received
					System.out.println("Invalid action");
			}
			
			// output a \n to separate inputs
			System.out.print('\n');
		}
		
		// output a closing message to the user
		System.out.println("Good bye!");
	}
	
	
	/**
	 * Initializes the spots in a parking lot to either true or false using rng.
	 * @param parkingLot The 2D array representing a parking lot.
	 * @param row The rows of the parking lot.
	 * @param col The columns of the parking lot.
	 */
	public static void initializeParkingLot(boolean[][] parkingLot, int row, int col) {
		// create and seed rng
		Random rng = new Random();
		rng.setSeed(System.currentTimeMillis());
		
		// loop through rows
		for(int i =0; i < row; i++) {
			// loop through columns
			for(int j =0; j < col; j++) {
				// generate a random number (0, 1, or 2)
				int chance = rng.nextInt(3);
				
				// if the number was 0, the parking lot spot
				// at row i and column j is occupied
				if(chance==0) { parkingLot[i][j] = true; }
				
				// otherwise, it is not occupied
				else { parkingLot[i][j] = false; }
			}
		}
	}
	
	
	/**
	 * This method gets an action input from the user.
	 * @param keyboard The keyboard object for getting inputs.
	 * @return A character representing the user's input.
	 */
	public static char getActionInput(Scanner keyboard) {
	    // create a string to store the user's input
		// give it an initial value of "??"
	    String input = "??";
	
	    // let the user input
	    input = keyboard.next();
	
	    // return the first character from the user's input
	    return input.charAt(0);
	}	
	
	
	/**
	 * Prints contents of a 2D array representing a parking lot.
	 * It also includes labels for rows and columns (indexing starts at 0).
	 * @param parkingLot The 2D array representing a parking lot.
	 * @param row The rows of the parking lot.
	 * @param col The columns of the parking lot.
	 */
	public static void printParkingLot(boolean[][] parkingLot, int row, int col) {
		// print whitespaces to ensure top labels align correctly
		System.out.print("   ");
		
		// loop for the number of columns
		for(int i =0; i<col;i++) {
			// print each column number
			System.out.printf("%-2d ",i);
		}
		
		// print a \n
		System.out.println();
		
		// loop through rows
		for(int i =0; i < row; i++) {
			
			// print the row number
			System.out.printf("%2d ",i);
			
			// loop through columns
			for(int j =0; j < col; j++) {
				
				// if the parking lot at [i][j] is occupied, print a #
				if(parkingLot[i][j]) { 	System.out.print("#  "); }
				
				// otherwise, print a -
				else { System.out.print("-  "); }
			}
			
			// print a \n
			System.out.println();
		}
	}
	
	
	/**
	 * Occupies a given row and column in a parking lot by setting it to true.
	 * @param parkingLot The 2D array representing a parking lot.
	 * @param row The rows of the parking lot.
	 * @param col The columns of the parking lot.
	 * @param occupyRow The row to try to occupy.
	 * @param occupyCol The column to try to occupy.
	 */
	public static void occupy(boolean[][] parkingLot, int row, int col, int occupyRow, int occupyCol) {
		// check if row and column combination is out of array bounds
		if(occupyRow<0||occupyRow>=row||occupyCol<0||occupyCol>=col) {
			// output message telling user the combination is invalid
			System.out.println("Row and column combination is out of bounds");
		}
		
		// check if row and column combination is already occupied
		else if(parkingLot[occupyRow][occupyCol]) {
			// output message saying row and column combination is already occupied
			System.out.println("Row and column combination is already occupied");	
		}
		
		// otherwise, row and column combination is not occupied
		else {
			// occupy row and column
			parkingLot[occupyRow][occupyCol] = true;
			
			// output message saying row and column combination is now occupied
			System.out.println("Row " + occupyRow + ", column " + occupyCol + " is now occupied");
		}
	}
	
	
	/**
	 * Deoccupies a given row and column in a parking lot by setting it to false.
	 * @param parkingLot The 2D array representing a parking lot.
	 * @param row The rows of the parking lot.
	 * @param col The columns of the parking lot.
	 * @param deoccupyRow The row to try to deoccupy.
	 * @param deoccupyCol The column to try to deoccupy.
	 */
	public static void deoccupy(boolean[][] parkingLot, int row, int col, int deoccupyRow, int deoccupyCol) {
		// check if row and column combination is out of array bounds
		if(deoccupyRow<0||deoccupyRow>=row||deoccupyCol<0||deoccupyCol>=col) {
			// output message telling user the combination is invalid
			System.out.println("Row and column combination is out of bounds");
		}
		
		// check if row and column combination is already deoccupied
		else if(!parkingLot[deoccupyRow][deoccupyCol]) {
			// output message saying row and column combination is already deoccupied
			System.out.println("Row and column combination is already deoccupied");	
		}
		
		// otherwise, row and column combination is not deoccupied
		else {
			// deoccupy row and column
			parkingLot[deoccupyRow][deoccupyCol] = false;
			
			// output message saying row and column combination is now deoccupied
			System.out.println("Row " + deoccupyRow + ", column " + deoccupyCol + " is now deoccupied");
		}
	}
	
	
	/**
	 * Searches a parking lot and finds an empty 
	 * spot as close to (0,0) as possible.
	 * @param parkingLot The 2D array representing a parking lot.
	 * @param row The rows of the parking lot.
	 * @param col The columns of the parking lot.
	 */
	public static void find(boolean[][] parkingLot, int row, int col) {
		// set a boolean to true for as long as we are trying to find a spot
		boolean finding = true;
		
		// loop through all possible diagonal sums in the 2D array
		for(int diagonalSum = 0; diagonalSum <= row+col-2; diagonalSum++) {
			
			// loop through columns less than or equal to diagonal sum
			for(int colCounter = 0; colCounter <= diagonalSum; colCounter++) {
				
				// calculate the corresponding row for the column
				int rowCounter = diagonalSum-colCounter;
				
				// first, ensure row and column combo is in array bounds
				if(rowCounter < row && colCounter < col) {
					
					// check if row and column combo is empty
					if(!parkingLot[rowCounter][colCounter]) {
						// output the row and column combo
						System.out.println("Empty spot at row " + 
						rowCounter + ", column " + colCounter);
						// we are no longer finding a spot
						finding = false;
					}
				}
				// if we are not trying to find a spot, leave loop
				if(!finding) break;
			}
			// if we are not trying to find a spot, leave loop
			if(!finding) break;
		}
		
		// if we left the outermost for loop and never 
		// found a spot, then the parking lot was full
		if(finding) System.out.println("Parking lot is full");
	}
	
	
	//Menu display for the list 
	public static void printListMenu(){
	 System.out.print("Type a to add to list, r to remove from the list"+
	   " or b to go back to the main menu.");
	}

	//Display the queue
	public static void printList(LinkedList<String> l){
	 if(l.isEmpty()){
	  System.out.println("There are no name on the list.");
	 }else
	 {System.out.println(l);}
	}
	
	
	//the case if the user inputs 'l' function 
	public static void caseIfLaction(LinkedList<String> list, Scanner keyboard){
	 printList(list);
	 printListMenu();
	 char userInput;
	 String name;
	 
	 userInput = getActionInput(keyboard);
	 if(userInput == 'a'){
	  System.out.println("Enter the name of the person to be added");
	  try {
	   name = keyboard.next();
	   list.add(name);
	  }
	  // if there was an error, go here
	  catch(Exception error) {
	   // print error message and exit
	   System.out.println("ERROR!!!");
	   System.exit(-1);
	  }
	        
	 }else if(userInput =='r'){
	  if(!list.isEmpty()) {
		  name = list.remove();
		  System.out.println(name + " has now entered the parking lot.");
	  }
	  else {
		  System.out.println("List is empty-cannot remove");
	  }
	  
	 }else if(userInput == 'b'){
	  return;
	 }
	 
	}
}