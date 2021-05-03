
public class TennisMatch {
	
	private String winner;
	Player playerOne = new Player();
	Player playerTwo = new Player();
	String[] pointsCount = {"0", "15", "30", "40"};
	
	//start tennis match and set player names
	public TennisMatch (String playerOneName, String playerTwoName) {
		playerOne.setName(playerOneName);
		playerTwo.setName(playerTwoName);
	}
	
	
	
	//check to see if the game was won by a player
	public boolean checkGame(Player current, Player opponent) {
		if ((current.points == 4 && opponent.points <= 2) || current.points == 5) {
			current.setGames(current.games + 1); 
			return true;
		}
		return false;
	}
	
	
	
	//check to see if the set was won by a player
	public boolean checkSet(Player current, Player opponent) {
		
		//A player wins a set by winning at least 6 games and at least 2 games more than the opponent.
		//If one player has won 6 games and the opponent 5, an additional 1 game is played. If the leading player wins that game, the player wins the set 7–5. If the trailing player wins the game, a tie-break is played.
		//A tie-break, played under a separate set of rules, allows one player to win one more game and thus the set, to give a final set score of 7–6. A tie-break is scored one point at a time. 
		//The tie-break game continues until one player wins 7 points by a margin of 2 or more points. Instead of being scored from 0, 15, 30, 40 like regular games, the score for a tie breaker goes up incrementally from 0 by 1, 2, 3 etc.		
		
		return true;
		
	}
	
	
	
	//adjust points based on parameter given
	public void pointWonBy(String playerName) {
		
		System.out.println("Point won by " + playerName);
		
		//add points
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
		}
		
		//check if set is complete
		if (checkSet(playerOne, playerTwo) || checkSet(playerTwo, playerOne)) {
			System.out.println("Match won by " + this.winner);
		}
		
	}
	
	public String score() {
		
		String pointsScore;
		
		//check for Deuce
		if (playerOne.points == 3 && playerTwo.points == 3) {
			pointsScore = ", Deuce";
		//check for advantage
		} else if (playerOne.points == 4) {
			pointsScore = ", Advantage " + playerOne.name;
		} else if (playerTwo.points == 4) {
			pointsScore = ", Advantage " + playerTwo.name;
		//no output if points are 0-0
		} else if (playerTwo.points == 0 && playerOne.points == 0) {
			pointsScore = "";
		//output regular points
		} else {
			pointsScore = ", " + pointsCount[playerOne.points] + "-" + pointsCount[playerTwo.points];
		}
		
		//create output string
		String output = playerOne.games + "-" + playerTwo.games + pointsScore;
		System.out.println(output + "\n");
		return output;
	}

	
	
	public static void main(String[] args) {	
		
		TennisMatch match = new TennisMatch("player 1", "player 2");
		
		match.pointWonBy("player 1");
		match.score();

		match.pointWonBy("player 2");
		// this will return "0-0, 15-15"
		match.score();

		match.pointWonBy("player 1");
		match.score();

		match.pointWonBy("player 1");
		// this will return "0-0, 40-15"
		match.score();
		  
		match.pointWonBy("player 2");
		match.score();

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
