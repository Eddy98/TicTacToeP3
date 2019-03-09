package course.oop.controller;

import course.oop.util.*;
import java.util.Random;

public class TTTControllerImpl implements TTTControllerInterface {
	
	TwoDArray board;
	Player p1;
	Player p2;
	int playerCount;
	int numPlayers;
	int timeout;
	int moveCount;
	
	public Player getPlayer(int num) {
		if (num ==1)
			return p1;
		else 
			return p2;
	}

	@Override
	public void startNewGame(int numPlayers, int timeoutInSecs) {
		
		this.board = new TwoDArray(3, 3, "_");
		this.numPlayers = numPlayers;
		this.timeout = timeoutInSecs;   //NEED TO DO THIS
		this.playerCount = 0;
		this.moveCount = 0;
		
	}

	@Override
	public void createPlayer(String username, String marker, int playerNum) {
		if (playerCount == 0 ) {
			p1 = new Player(playerNum, marker, username);
		} else {
			p2 = new Player(playerNum, marker, username);
		}
		playerCount++;
	}
	
	
	public boolean PCsetSelection() {
		String mark = p2.getMarker();
		Random rand = new Random();
	
		
		while (true) {
			int row = rand.nextInt(3);
			int col = rand.nextInt(3);
			if (board.insertMark(row, col, mark)){
				break;
			}
			
		}
		moveCount++;
		return true;
	}
	
	@Override
	public boolean setSelection(int row, int col, int currentPlayer) {
		if (moveCount == 9)
			return false;
		
		String mark = "";
		if(this.numPlayers == 2) {
			if (currentPlayer == 1) {
				mark = p1.getMarker();
			} else {
				mark = p2.getMarker();
			}
		} else {
			mark = p1.getMarker(); ///what about when 1 player? PC marker??
		}
		moveCount++;
		return board.insertMark(row, col, mark); 
		
		
	}

	@Override
	public int determineWinner() {
		if (winnerHelper(p1)) {
			return 1;
		} else if (winnerHelper(p2)) {
			return 2;
		} else if (moveCount == 9) {
			return 3;
		}
		return 0;
	}
	
	public boolean winnerHelper(Player p) {
		String check = p.getMarker();
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		int c4 = 0;
		
		//check rols and cols
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.getMark(i, j).equalsIgnoreCase(check)) {
					c1++;
				}
				if (board.getMark(j, i).equalsIgnoreCase(check)) {
					c2++;
				}
			}
			if (c2 == 3 || c1 == 3) {
				return true;
			}
			c2 = 0;
			c1 = 0;
		}
		
		//check diagonal
		for (int i= 0, j = 0; i < 3; i++, j++) {
			if (board.getMark(i, j).equalsIgnoreCase(check)) {
				c3++;
			}
			if (c3 == 3) {
				return true;
			}
		}
		
		for (int i= 2, j = 0; j < 3; i--, j++) {
			if (board.getMark(i, j).equalsIgnoreCase(check)) {
				c4++;
			}
			if (c4 == 3) {
				return true;
			}
		}
		
		return false;
	}
	

	@Override
	public String getGameDisplay() {
		return board.getArrayDisplay();
	}

}
