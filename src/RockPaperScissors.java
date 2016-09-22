/* ========================================================================== */
/*	PROGRAM Rock Paper Scissors

    AUTHOR: Yuri Khechoyan & Jessie Wilkins
    COURSE NUMBER: CIS 210
    COURSE SECTION NUMBER: 01
    INSTRUCTOR NAME: Dr. Tian
    PROJECT NUMBER: 5
    DUE DATE: 9/22/2016

SUMMARY

	This program is used to simulate a game of rock, paper, scissors. 
	The computer will generate a choice of rock, paper, or scissors, and the user
	will enter a number that corresponds with the with one of the three choices. 
	One is rock; Two is paper; Three is scissors. Based on the basic rules of the game, 
	if the user chooses rock and the computer chooses scissors, the user wins. 
	If the user chooses rock and the computer chooses paper, the computer wins. 
	If the user chooses paper & the computer chooses scissors, the computer wins.
	The vice-versa of these possibilities is also true. If the computer and user have 
	the same choice, the game is played again. After determining the winner, the winner 
	is output to the console. The computer will also ask if the user would like to 
	play again; if the user indicates that he or she does not want to play again, 
	the program will quit. Otherwise, it will play again. As long asthe user wants 
	to play again, the program will repeat the game up to ten times; 
	After ten times, it will quit. All input and output is also printed onto a file.
	
	A Random Number Generator will choose between 3 choices :
	1. Rock
	2. Paper 
	3. Scissors 

INPUT

The input of the program will be integers that correspond
to the choices in the way shown above. After determining the
winners, the computer will ask the user if he or she would
like to play again in the form of a character.
The functions' argument have the parameter of the Random
object (ComputerSelction), two integers one being the user choice
and the other being the computer choice (DecideWinner), and a string
(AnnounceWinner).

OUTPUT

The program will output commands to enter a number and will eventually
announce the winner by printing a string. The computer will also ask
if the user would
Program will output the 3 winning fruits (along with the selected fruit)
The 3 winning fruits will be drawn at random.
And also the accompanied winnings or loss of cash.

ASSUMPTIONS
- None

*/

import java.io.*;										//Import for input/output data streams/file system
import java.util.Scanner;								//Import Scanner Object	
import java.util.Random;								//Import Random Generator	

public class RockPaperScissors_ver5{
	public static void main(String[] args) throws IOException{
		
		//Variables and constants
		int i=0;										//This declares and initilizes the variable used to count the number of timest
														//the program is run
		char continueChoice = 'y';						//User input for continuation of game
		int computerChoice = 0;							//Computer Randomized Choice (R, P, S)
		int userChoice = 0;								//User picked Choice (R, P, S)
		String winner = new String("Nothing");			//Output String for who wins
		Random numGenerator = new Random();				//Initialization for Random Generator
		int MAX_NUMBER_OF_ROUNDS = 10;					//This declares and initializes the constant that is the maximum number of rounds
		
		//**********************
		//	START OF PROGRAM   *
		//**********************
		
		//This deletes the contents of the file from the last program
		PrintWriter deleteContents = new PrintWriter("outputfile.txt");
		deleteContents.println("Start:");
		//This closes the object
		deleteContents.close();
		//This determines that the file will be appended to and creates an object for that file
		FileWriter appendDetermine = new FileWriter("outputfile.txt", true);
		PrintWriter fileAppend = new PrintWriter(appendDetermine);
		//This creates two input variables, one for integer and one for characters
		Scanner intInput = new Scanner(System.in);
		Scanner charInput = new Scanner(System.in);
		
		//While loop for Game Continuation
		while(i<MAX_NUMBER_OF_ROUNDS && continueChoice == 'y') {
			//This outputs the greeting of the program if there is no draw
			if(winner != "Draw" ) {
				Greetings(i, fileAppend);
			}//This closes the if statement
			//The computer selects a random number for the rock, paper, scissors game
			computerChoice = ComputerSelection(numGenerator);
			//The user selects the choice of either rock, paper, scissors
			userChoice = UserSelection(fileAppend, intInput);
			//This shows the computer's choice
			ComputerDecisionOutput(computerChoice, fileAppend);
			//This decides which one is the winner
			winner = DecideWinner(userChoice, computerChoice, fileAppend);
			//This one declares the winner
			AnnounceWinner(winner, fileAppend);
		
			//Increments i each time the program User decides to play a new game if there is no draw
			if(winner!="Draw") {
				i++;
			}//Closes if statement
			
			//This determines if the user wants to continue
			continueChoice = ContinueOption(winner, fileAppend, charInput, i);
			
		}//Closes While loop (continueChoice)
		//This prints out the farewell to the console and the file
		System.out.println("Thanks for playing. Bye!");
		fileAppend.println("Thanks for playing. Bye!");
		//This closes three objects
		fileAppend.close();									//This closes the file object
		intInput.close();									//This closes the input object for the integer
		charInput.close();									//This closes the input object for the character
	}//Closes Main
	
	//*****************************
	//     Function Definitions	  *
	//*****************************
	
	public static int UserSelection(PrintWriter fileOutput, Scanner input) throws IOException{
		//Storing the input from user for userSelection
		int select; 
		//Ouputs instructions to the console and file
		System.out.println("Please select one of the following:");
		fileOutput.println("Please select one of the following:");
		System.out.println("\n 1. Rock | 2. Paper | 3. Scissors");
		fileOutput.println("\r\n 1. Rock | 2. Paper | 3. Scissors");
		//This takes the user input of an integer
		select = input.nextInt();
		//This prints the input to the file
		fileOutput.println("\r\n" + select);
		
		//Error Checks if User input is within Parameters and asks the user for input again
		while(select != 1 && select != 2 && select != 3){
			System.out.println("\r\nThe value entered is not Valid! Please try again.");
			select = input.nextInt();
			//This outpus the input to the file
			fileOutput.println("\r\n" +select);
		}//Closes 'while' loop
		//This returns the user given integer
		return select;
	}//Closes UserSelection Method
	
	public static int ComputerSelection(Random rand) throws IOException{
		//This declares the variable that will be used for the random integer
		int randInt;
		//This assigns a random integer to the variable
		randInt = rand.nextInt(3)+1;
		//This returns the random integer
		return randInt;
	}//Closes ComputerSelection Method
	
	public static String DecideWinner(int userChoice, int computerChoice, PrintWriter fileOutput) throws IOException{
		//This declares and initializes the string variable/object
		String w = null;
		//Switch statement based on what User entered
		//Will do the error checking and let user know who wins (user or computer)
		switch(userChoice){
		//If the user choice is 1
		case 1:
			//This prints out the user's choice to the console and file
			System.out.println("You have chosen: Rock!");
			fileOutput.println("You have chosen: Rock!");
			//This assigns "Draw" to the string variable if the computer choice is 1
			if(computerChoice == 1){
				w = "Draw";
			}//closes if
			//This assigns "Computer" to the string variable if the computer choice is 2
			else if(computerChoice == 2){
				w = "Computer";
			}//closes 1st else if
			//This assigns "You!" to the string variable if the computer choice is 3
			else if(computerChoice == 3){
				w = "You!";
			}//closes 2nd else if
			//This exits the case
			break;
		//If the user choice is 2
		case 2:
			//This prints out the user's choice to the console and file
			System.out.println("You have chosen: Paper!");
			fileOutput.println("You have chosen: Paper!");
			//This assigns "You!" to the string variable if the computer choice is 1
			if(computerChoice == 1){
				w = "You!";
			}//closes if
			//This assigns "Draw" to the string variable if the computer choice is 2
			else if(computerChoice == 2){
				w = "Draw";
			}//closes 1st else if
			//This assigns "Computer" to the string variable if the computer choice is 3
			else if(computerChoice == 3){
					w = "Computer";
			}//closes 2nd else if
			//This exits the case
			break;
			//If the user choice is 2
		case 3:
			//This prints out the user's choice to the console and file
			System.out.println("You have chosen: Scissors!");
			fileOutput.println("You have chosen: Scissors!");
			//This assigns "Computer" to the string variable if the computer choice is 1
			if(computerChoice == 1){
				w = "Computer";
				return w;
			}//closes if
			//This assigns "You!" to the string variable if the computer choice is 2
			else if(computerChoice == 2){
				w = "You!";
			}//closes 1st else if
			//This assigns "Draw" to the string variable if the computer choice is 3
			else if(computerChoice == 3){
				w = "Draw";
			}//closes 2nd else if
			//This exits the case
			break;
		}//Closes 'userChoice' Switch Statement
		//This returns the string
		return w;
	}//Closes DecisionWinner Method
	
	public static void AnnounceWinner(String winner, PrintWriter fileOutput) throws IOException{
		//This prints out the winner to the console and the file if it is not a draw
		if(winner != "Draw") {
		
			System.out.println("The winner of the 2016 Rock, Paper, Scissor Olympics is " + winner);
			fileOutput.println("The winner of the 2016 Rock, Paper, Scissor Olympics is " + winner);
		}//Closes the if statement
		//This prints out that there is a draw to the console and the file if the last condition was not satisfied 
		else {
			
			System.out.println("Its a draw. We need to play again.");
			fileOutput.println("Its a draw. We need to play again.");
		}
	}//Closes AnnounceWinner Method
	
	public static char ContinueOption(String winner, PrintWriter fileOutput, Scanner charIn, int i) throws IOException{
		//This declares and initializes the character variable
		char d = 'y';
		//if there is not draw and it is not the tenth time playing the program
		if(winner !="Draw" && i<9) {
			//This prints out the question and instructions to the console and the file
			System.out.println("Would you like to play again? y/Y = yes, n/N = no");
			fileOutput.println("Would you like to play again? y/Y = yes, n/N = no\r\n");
			//This assigns the user input of a character to the variable
			d = charIn.nextLine().charAt(0);
			//This outputs the input to the file
			fileOutput.println(d +"\r\n");
			//This error checks the character input to see if the input is y/Y or n/N
			while(d!='y' && d!='Y'&& d!='n'&& d!='N') {
				//This prints out to the console and the file that the declaration that the answer is invalid to
				System.out.println("This is an invalid answer. Please type in y/Y or n/N (yes or no).");
				fileOutput.println("This is an invalid answer. Please type in y/Y or n/N (yes or no).\r\n");
				//This asks for the user input of a character
				d = charIn.nextLine().charAt(0);
				//This outputs the input to the file
				fileOutput.println(d + "\r\n");
			}//Closes 'while' loop in ContinueOption Method
		}//Closes 'if' statement in ContinueOption Method
		//This returns the character
		return d;
	}//Closes ContinueOption Method
	
	public static void Greetings(int i, PrintWriter fileOutput) throws IOException {
		//This prints out the beginning greeting to the console and the file
		//if it is the first time the program is running
		if(i==0) {
			System.out.println("Welcome to the 2016 Rock Paper Scissors Olympics of the world!");
			fileOutput.println("Welcome to the 2016 Rock Paper Scissors Olympics of the world!");
		}
		//This prints out the returning greeting to the console and the file if this is not 
		//the first time nor the last time running the program
		else if(i>0 && i<9) {
			System.out.println("Welcome back to the 2016 Rock Paper Scissors Olympics of the world!");
			fileOutput.println("Welcome back to the 2016 Rock Paper Scissors Olympics of the world!");
		}
		//This prints out the final round greeting to the console and the file if 
		//the last conditions were not satisfied
		else {
			System.out.println("Welcome to the final round of the 2016 Rock Paper Scissors Olympics of the world!");
			fileOutput.println("Welcome to the final round of the 2016 Rock Paper Scissors Olympics of the world!");
		}
	}//Closes Greetings Class
	
	public static void ComputerDecisionOutput(int CompDecis, PrintWriter fileOutput) throws IOException {
		//This is the switch statement that determines which item the computer chose
		switch(CompDecis){
		//if the computer's decision is 1, it will print out that the computer chose
		//rock to the console and the file and exits the switch statement
		case 1:
			System.out.println("The computer has chosen: Rock!");
			fileOutput.println("The computer has chosen: Rock!");
			break;
		//if the computer's decision is 2, it will print out that the computer chose
		//paper to the console and the file and exits the switch statement
		case 2:
			System.out.println("The Computer has chosen: Paper!");
			fileOutput.println("The Computer has chosen: Paper!");
			break;
		//if the computer's decision is 3, it will print out that the computer chose
		//scissors to the console and the file and exits the switch statement
		case 3:
			System.out.println("The Computer has chosen: Scissors!");
			fileOutput.println("The Computer has chosen: Scissors!");
			break;
		}//Closes ComputerDecis Switch Statement

	}//Closes ComputerDecisionOutput Class

}//Closes RockPaperScissors Class
