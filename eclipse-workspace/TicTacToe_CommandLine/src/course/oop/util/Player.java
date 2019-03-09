package course.oop.util;

public class Player {

		int playerNum;
		String marker;
		String username;
		
		public Player(int playerNum, String marker, String username) {
			this.playerNum = playerNum;
			this.marker = marker;
			this.username = username;
		}
		
		public String getMarker() {
			return this.marker;
		}
		
		public String getUsername() {
			return this.username;
		}
		
		public int getPlayerNum(){
			return this.playerNum;
		}
}
