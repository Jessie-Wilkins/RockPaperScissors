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

public class RockPaperScissors_ver3{
	public static void main(String[] args) throws IOException{
		
		//Variables and constants
		int i=0;
		char continueChoice = 'y';						//User input for continuation of game
		int computerChoice = 0;							//Computer Randomized Choice (R, P, S)
		int userChoice = 0;								//User picked Choice (R, P, S)
		String winner = new String("Nothing");			//Output String for who wins
		Random numGenerator = new Random();				//Initialization for Random Generator
		
		//**********************
		//	START OF PROGRAM   *
		//**********************
		
		PrintWriter deleteContents = new PrintWriter("outputfile.txt");
		deleteContents.println("Start:");
		deleteContents.close();
		FileWriter appendDetermine = new FileWriter("outputfile.txt", true);
		PrintWriter fileAppend = new PrintWriter(appendDetermine);
		Scanner intInput = new Scanner(System.in);
		Scanner charInput = new Scanner(System.in);
		
		//While loop for Game Continuation
		while(i<10 && continueChoice == 'y') {
			//This outputs the greeting of the program
			if(winner != "Draw" ) {
				Greetings(i, fileAppend);
			}
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
		
			//Increments i each time the program User decides to play a new game
			if(winner!="Draw") {
				i++;
			}
			
			//This determines if the user wants to continue
			continueChoice = ContinueOption(winner, fileAppend, charInput, i);
			
		}//Closes While loop (continueChoice)
		System.out.println("Thanks for playing. Bye!");
		fileAppend.println("Thanks for playing. Bye!");
		
		fileAppend.close();
		intInput.close();
		charInput.close();
	}//Closes Main
	
	//*****************************
	//     Function Definitions	  *
	//*****************************
	
	public static int UserSelection(PrintWriter fileOutput, Scanner input) throws IOException{
		int select; //Storing the input from user for userSelection
		
		System.out.println("Please select one of the following:");
		fileOutput.println("Please select one of the following:");
		System.out.println("\n 1. Rock | 2. Paper | 3. Scissors");
		fileOutput.println("\r\n 1. Rock | 2. Paper | 3. Scissors");
		select = input.nextInt();
		fileOutput.println("\r\n" + select);
		
		//Error Checks if User input is within Parameters
		while(select != 1 && select != 2 && select != 3){
			System.out.println("\r\nThe value entered is not Valid! Please try again.");
			select = input.nextInt();
			fileOutput.println("\r\n" +select);
		}//Closes 'while' loop
		return select;
	}//Closes UserSelection Method
	
	public static int ComputerSelection(Random rand) throws IOException{
		int randInt;
		
		randInt = rand.nextInt(3)+1;
		
		return randInt;
	}//Closes ComputerSelection Method
	
	public static String DecideWinner(int userChoice, int computerChoice, PrintWriter fileOutput) throws IOException{
		String w = null;
		//Switch statement based on what User entered
		//Will do the error checking and let user know who wins (user or computer)
		
		switch(userChoice){
		case 1:
			System.out.println("You have chosen: Rock!");
			fileOutput.println("You have chosen: Rock!");
			if(computerChoice == 1){
				w = "Draw";
			}//closes if
			else if(computerChoice == 2){
				w = "Computer";
			}//closes 1st else if
			else if(computerChoice == 3){
				w = "You!";
			}//closes 2nd else if
			break;
		case 2:
			System.out.println("You have chosen: Paper!");
			fileOutput.println("You have chosen: Paper!");
			if(computerChoice == 1){
				w = "You!";
			}//closes if
			else if(computerChoice == 2){
				w = "Draw";
			}//closes 1st else if
			else if(computerChoice == 3){
					w = "Computer";
			}//closes 2nd else if
			break;
		case 3:
			System.out.println("You have chosen: Scissors!");
			fileOutput.println("You have chosen: Scissors!");
			if(computerChoice == 1){
				w = "Computer";
				return w;
			}//closes if
			else if(computerChoice == 2){
				w = "You!";
			}//closes 1st else if
			else if(computerChoice == 3){
				w = "Draw";
			}//closes 2nd else if
			break;
		}//Closes 'userChoice' Switch Statement
		return w;
	}//Closes DecisionWinner Method
	
	public static void AnnounceWinner(String winner, PrintWriter fileOutput) throws IOException{
		
		if(winner != "Draw") {
		
			System.out.println("The winner of the 2016 Rock, Paper, Scissor Olympics is " + winner);
			fileOutput.println("The winner of the 2016 Rock, Paper, Scissor Olympics is " + winner);
		}
		else {
			
			System.out.println("Its a draw. We need to play again.");
			fileOutput.println("Its a draw. We need to play again.");
		}
	}//Closes AnnounceWinner Method
	
	public static char ContinueOption(String winner, PrintWriter fileOutput, Scanner charIn, int i) throws IOException{
		char d = 'y';
		if(winner !="Draw" && i<9) {
			
			System.out.println("Would you like to play again? y/Y = yes, n/N = no");
			fileOutput.println("Would you like to play again? y/Y = yes, n/N = no\r\n");
			
			d = charIn.nextLine().charAt(0);
			fileOutput.println(d +"\r\n");
			while(d!='y' && d!='Y'&& d!='n'&& d!='N') {
			
				System.out.println("This is an invalid answer. Please type in y/Y or n/N (yes or no).");
				fileOutput.println("This is an invalid answer. Please type in y/Y or n/N (yes or no).\r\n");
				d = charIn.nextLine().charAt(0);
				fileOutput.println(d + "\r\n");
			}//Closes 'while' loop in ContinueOption Method
		}//Closes 'if' statement in ContinueOption Method
		return d;
	}//Closes ContinueOption Method
	
	public static void Greetings(int i, PrintWriter fileOutput) throws IOException {
		
		if(i==0) {
			System.out.println("Welcome to the 2016 Rock Paper Scissors Olympics of the world!");
			fileOutput.println("Welcome to the 2016 Rock Paper Scissors Olympics of the world!");
		}
		else if(i>0 && i<9) {
			System.out.println("Welcome back to the 2016 Rock Paper Scissors Olympics of the world!");
			fileOutput.println("Welcome back to the 2016 Rock Paper Scissors Olympics of the world!");
		}
		else {
			System.out.println("Welcome to the final round of the 2016 Rock Paper Scissors Olympics of the world!");
			fileOutput.println("Welcome to the final round of the 2016 Rock Paper Scissors Olympics of the world!");
		}
	}//Closes Greetings Class
	
	public static void ComputerDecisionOutput(int CompDecis, PrintWriter fileOutput) throws IOException {
		switch(CompDecis){
		case 1:
			System.out.println("The computer has chosen: Rock!");
			fileOutput.println("The computer has chosen: Rock!");
			break;
		case 2:
			System.out.println("The Computer has chosen: Paper!");
			fileOutput.println("The Computer has chosen: Paper!");
			break;
		case 3:
			System.out.println("The Computer has chosen: Scissors!");
			fileOutput.println("The Computer has chosen: Scissors!");
			break;
		}//Closes ComputerDecis Switch Statement

	}//Closes ComputerDecisionOutput Class

}//Closes RockPaperScissors Class
