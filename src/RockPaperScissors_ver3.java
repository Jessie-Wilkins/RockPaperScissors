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
		String winner = new String("Nothing");									//Output String for who wins
		Random numGenerator = new Random();						//Initialization for Random Generator
		
		//**********************
		//	START OF PROGRAM   *
		//**********************
		
		PrintWriter deleteContents = new PrintWriter("outputfile.txt");
		deleteContents.println("Start:");
		deleteContents.close();
		FileWriter appendDetermine = new FileWriter("outputfile.txt", true);
		PrintWriter fileAppend = new PrintWriter(appendDetermine);
		
		//While loop for Game Continuation
		while(i<10 && continueChoice == 'y') {
			//This outputs the greeting of the program
			if(winner != "Draw" ) {
				Greetings(i);
			}
			//The computer selects a random number for the rock, paper, scissors game
			computerChoice = ComputerSelection(numGenerator);
			//The user selects the choice of either rock, paper, scissors
			userChoice = UserSelection();
			//This shows the computer's choice
			ComputerDecision(computerChoice);
			//This decides which one is the winner
			winner = DecideWinner(userChoice, computerChoice);
			//This one declares the winner
			AnnounceWinner(winner);
		
			//Increments i each time the program User decides to play a new game
			if(winner!="Draw") {
				i++;
			}
			
			//This determines if the user wants to continue
			continueChoice = ContinueOption(winner);
			
		}//Closes While loop (continueChoice)
		System.out.println("Thanks for playing. Bye!");
		fileAppend.println("Thanks for playing. Bye!");
	}//Closes Main
	
	//*****************************
	//     Function Definitions	  *
	//*****************************
	
	public static int UserSelection() throws IOException{
		int select; //Storing the input from user for userSelection
		Scanner input = new Scanner(System.in);
		FileWriter appendFile = new FileWriter("outputfile.txt",true);
		PrintWriter fileOutput = new PrintWriter(appendFile);
		
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
		}
		return select;
	}
	
	public static int ComputerSelection(Random rand) throws IOException{
		int randInt;
		
		randInt = rand.nextInt(3)+1;
		
		return randInt;
	}
	
	public static String DecideWinner(int userChoice, int computerChoice) throws IOException{
		String w = null;
		//Switch statement based on what User entered
		//Will do the error checking and let user know who wins (user or computer)
		
		FileWriter appendFile = new FileWriter("outputfile.txt",true);
		PrintWriter fileOutput = new PrintWriter(appendFile);
		
		
		
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
		}
		return w;
	}
	
	public static void AnnounceWinner(String winner) throws IOException{
		
		FileWriter appendFile = new FileWriter("outputfile.txt",true);
		PrintWriter fileOutput = new PrintWriter(appendFile);
		
		if(winner != "Draw") {
		
			System.out.println("The winner of the 2016 Rock, Paper, Scissor Olympics is " + winner);
			fileOutput.println("The winner of the 2016 Rock, Paper, Scissor Olympics is " + winner);
		}
		else {
			
			System.out.println("Its a draw. We need to play again.");
			fileOutput.println("Its a draw. We need to play again.");
		}
	}
	
	public static char ContinueOption(String winner) throws IOException{
		char d = 'y';
		
		if(winner !="Draw") {
			Scanner charIn = new Scanner(System.in);
			
			System.out.println("Would you like to play again? y/Y = yes, n/N = no");
			fileOutput.println("Would you like to play again? y/Y = yes, n/N = no");
			
			d = charIn.nextLine().charAt(0);
			while(d!='y' && d!='Y'&& d!='n'&& d!='N') {
			
				System.out.println("This is an invalid answer. Please type in y/Y or n/N (yes or no).");
				d = charIn.nextLine().charAt(0);
			}
		}
		return d;
	}
	
	public static void Greetings(int i) throws IOException {
		if(i==0) {
			System.out.println("Welcome to the 2016 Rock Paper Scissors Olympics of the world!");
		}
		else if(i>0 && i<9) {
			System.out.println("Welcome back to the 2016 Rock Paper Scissors Olympics of the world!");
		}
		else {
			System.out.println("Welcome to the final round of the 2016 Rock Paper Scissors Olympics of the world!");
		}
	}
	
	public static void ComputerDecision(int CompDecis) throws IOException {
		switch(CompDecis){
		case 1:
			System.out.println("The computer has chosen: Rock!");
			break;
		case 2:
			System.out.println("The Computer has chosen: Paper!");
			break;
		case 3:
			System.out.println("The Computer has chosen: Scissors!");
			
			break;
		}

	}

}//Closes RockPaperScissors Class
