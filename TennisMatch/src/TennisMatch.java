
public class TennisMatch {
	
	private String winner;
	private Player playerOne = new Player();
	private Player playerTwo = new Player();
	private boolean tieBreakGame = false;
	private String[] pointsCount = {"0", "15", "30", "40"};
	private String message;
	
	//start tennis match and set player names
	public TennisMatch (String playerOneName, String playerTwoName) {
		playerOne.setName(playerOneName);
		playerTwo.setName(playerTwoName);
	}
	
	//check to see if the game was won by a player
	public boolean checkGame(Player current, Player opponent) {
		if ((current.points == 4 && opponent.points <= 2) || current.points == 5) {
			current.setGames(current.games + 1); 
			winner = current.name;
			return true;
		}
		return false;
	}
	
	//check to see if the set was won by a player
	public boolean checkSet(Player current, Player opponent) {
		
		//regular win or 5-7 win
		if ((current.games >= 6 && opponent.games <= 4) || (current.games == 7 && opponent.games == 5)) {
			winner = current.name;
			return true;
		}
		
		//tie break game 
		if (current.games == 6 && opponent.games == 6) {
			tieBreakGame = true;
			return false;
		}
		
		return false;			
	}
	
	//check to see if the tie break was won by a player 
	public boolean checkTieBreak(Player current, Player opponent) {
		if (current.tiePoints >=7 && opponent.tiePoints < current.tiePoints-1) {
			winner = current.name;
			return true;
		}
		return false;
	}
	
	//add points based on parameter name given
	public void pointWonBy(String playerName) {
		
		message = "Point won by " + playerName;
		
		if (!tieBreakGame) {
		
			//add regular points
			if (playerName.equals(playerOne.name)) {
				playerOne.setPoints(playerOne.points + 1);
			} else {
				playerTwo.setPoints(playerTwo.points + 1);
			}
			
			//check if both are advantage, if so then reset to Deuce
			if (playerOne.points == 4 && playerTwo.points == 4) {
				playerOne.setPoints(3);
				playerTwo.setPoints(3);
			}		
			
			//check if game was won by either player, if so then reset to 0
			if (checkGame(playerOne, playerTwo) || checkGame(playerTwo, playerOne)) {		
				playerOne.setPoints(0);
				playerTwo.setPoints(0);
				message = message + "\nGame won by " + this.winner;
				winner = "";
			}
				
			//check if set is complete
			if (checkSet(playerOne, playerTwo) || checkSet(playerTwo, playerOne)) {
				message = message + "\nMatch won by " + this.winner;
				winner = "";
			} else if (tieBreakGame) {
				message = message + "\nTie Break Game is Starting";
			}
		} else {
			//add tie breaker points
			if (playerName.equals(playerOne.name)) {
				playerOne.setTiePoints(playerOne.tiePoints + 1);
			} else {
				playerTwo.setTiePoints(playerTwo.tiePoints + 1);
			}
			//check if tie breaker is complete
			if (checkTieBreak(playerOne, playerTwo) || checkTieBreak(playerTwo, playerOne)) {
				message = message + "\nTie Break Game won by " + this.winner;
				winner = "";
			} 	
		}
				
		System.out.println(message + "\n");		
	}
	
	public String score() {
		
		String pointsScore;
		
		if (!tieBreakGame) {
		
			//check for Deuce
			if (playerOne.points == 3 && playerTwo.points == 3) {
				pointsScore = ", Deuce";
			//check for advantage
			} else if (playerOne.points == 4) {
				pointsScore = ", Advantage " + playerOne.name;
			} else if (playerTwo.points == 4) {
				pointsScore = ", Advantage " + playerTwo.name;
			//output regular points
			} else {
				pointsScore = ", " + pointsCount[playerOne.points] + "-" + pointsCount[playerTwo.points];
			}
		} else {
			pointsScore = ", " + playerOne.tiePoints + "-" + playerTwo.tiePoints;
		}
		
		//create output string
		String output = playerOne.games + "-" + playerTwo.games + pointsScore + "\n";
		System.out.println(output);
		return output;
	}

	
	
	public static void main(String[] args) {	
		
		//create players
		TennisMatch match = new TennisMatch("player 1", "player 2");

		//list of points won
		match.pointWonBy("player 1");
		match.pointWonBy("player 2");
		// this will return "0-0, 15-15"
		match.score();

		match.pointWonBy("player 1");
		match.pointWonBy("player 1");
		// this will return "0-0, 40-15"
		match.score();
		  
		match.pointWonBy("player 2");
		match.pointWonBy("player 2");
		// this will return "0-0, Deuce"
		match.score();
		  
		match.pointWonBy("player 1");
		// this will return "0-0, Advantage player 1"
		match.score();
		  
		match.pointWonBy("player 1");
		// this will return "1-0"
		match.score();
		
	}

}
