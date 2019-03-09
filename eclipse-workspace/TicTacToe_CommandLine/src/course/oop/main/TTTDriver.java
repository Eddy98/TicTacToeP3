package course.oop.main;

import course.oop.controller.*;
import java.util.Scanner;

public class TTTDriver {

	public static void main(String[] args) {
		
		//Instantiating game  
		TTTControllerImpl game = new TTTControllerImpl();
		int winner = 3;
		
		//input scanner
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("HELLO, WELCOME TO THE BEST TIC TAC TOE GAME!");
		System.out.println();
		System.out.println("Rules: ");
		System.out.println("1. Enter 'start' to start game");
		System.out.println("2. Enter 'quit' to quit game at any time");
		System.out.println("3. Enter mark by specifying row and column postion from 0-2");
		
		String idk;
		//Prompts user to start game
		while(true) {
			idk = sc.next();
			if(idk.equalsIgnoreCase("quit")) {
				System.exit(0);
			}
			if (idk.equalsIgnoreCase("start"))
				break;
			System.out.println("Enter a valid option");
		}
		
		int numP = 2;
		//prompts user to enter the number of players 
		while (true) {
			System.out.print("Please, enter the number of players: ");
			numP =  sc.nextInt();
			if (numP == 1 || numP == 2) {
				break;
			} else {
				System.out.println("Invalid Input. Try again");
				System.out.println();
				
			}
		}
		

		//starts the game
		game.startNewGame(numP, 0);
		
		//creates the number of human players necessary
		for (int i = 1; i <= numP; i++) {
			System.out.print("Enter Player " + i + " name: ");
			String username = sc.next();
			
			System.out.print("Enter Player " + i + " marker (example - X or O): ");
			String mark = sc.next();
			
			game.createPlayer(username, mark, i);
		}
		
		//if only one human player, then creates a PC player 
		if (numP == 1) {
			game.createPlayer("PC", "$", 2);
			System.out.println("\nThe computer will be using this symbol --> $");
		}
		
		//loop with 9 iterations for the maximum number of turns
		for (int i = 0; i < 9; i++) {
			
			System.out.println();
			int turn  = (i%2)+1;		//determines who's turn it is, either player 1 or 2 
			
			//if it is the second player's turn, and there is only one player, then PC makes a selection 
			if (turn == 2 && numP == 1) {
				game.PCsetSelection();
				if(game.determineWinner() == 2) {
					winner = 2;
					break;
				}
				continue;
			}
			
			
			//display board 
			int row = 0, col = 0;
			System.out.println("--------------------------------------------------");
			System.out.println(game.getGameDisplay());
			System.out.print("Your turn " + game.getPlayer(turn).getUsername() + "! \n\nEnter row number of mark (starting at 0): ");
			try {	
				//get user input for row number
				idk = sc.next();
				//check if user wants to quit game
				if(idk.equalsIgnoreCase("quit")) {
					System.out.println("Thank you for playing! ");
					System.exit(0);	
				}
				row = Integer.parseInt(idk);  //cast string to int
		
				System.out.println();
				System.out.print("Enter column number of mark (starting at 0): ");
				
				//get user input for col number
				idk = sc.next();
				
				//check if user wants to quit game
				if(idk.equalsIgnoreCase("quit")) {
					System.out.println("Game ended. Thank you for playing! ");
					System.exit(0);	
				}
				col = Integer.parseInt(idk);  //cast string to int
			
				//make selection, if it not valid, then repeat iteration
				if (!game.setSelection(row, col, game.getPlayer(turn).getPlayerNum())) {
					System.out.println();
					System.out.println("SORRY INVALID MOVE! TRY AGAIN");
					i--;
				}
				
				//if there is a invalid input, repeat iteration 
			} catch (Exception e) {
				System.out.println();
				System.out.println("SORRY INVALID MOVE! TRY AGAIN");
				i--;
			}
			
			//determine if p1 is winner, and determine if p2 is winner 
			if(game.determineWinner() == 1) {
				winner = 1;
				break;
			} else if(game.determineWinner() == 2) {
				winner = 2;
				break;
			}
			
			
		}
		
		//print out game board at the end 
		System.out.println();
		System.out.println(game.getGameDisplay());
		
		//announce who is the winner or if there is a draw
		if (winner != 3) {
			if (numP == 2)
				System.out.println("CONGRATULATIONS " + game.getPlayer(winner).getUsername() + " YOU ARE THE WINNER!!!");
			else {
				if (winner == 1) {
					System.out.println("CONGRATULATIONS " + game.getPlayer(winner).getUsername() + " YOU ARE THE WINNER!!!");
				} else {
					System.out.println("Computer Wins! At this pace, they will take over the world!");
				}
			}
		}
			
		else if (winner == 3){
			System.out.println("It's a draw!");
		} 
		System.out.println("Thank you for playing");
		
	}

}
