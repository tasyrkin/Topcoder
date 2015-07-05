

public class Tothello {

	public static final int EMPTY_PLACE = 0;
	public static final int RED_PLACE = 1;
	public static final int BLACK_PLACE = 2;
	
	public static int convertCharToInt(Character character){
		switch (character){
			case 'A':return 0;
			case 'B':return 1;
			case 'C':return 2;
			case 'D':return 3;
			case 'E':return 4;
			case 'F':return 5;
			case 'G':return 6;
			case 'H':return 7;
		}
		return -1;
	}
	public static char convertIntToChar(Integer integer){
		switch (integer){
			case 0:return 'A';
			case 1:return 'B';
			case 2:return 'C';
			case 3:return 'D';
			case 4:return 'E';
			case 5:return 'F';
			case 6:return 'G';
			case 7:return 'H';
		}
		return 'Z';
	}
	
	private int checkPossibility(int row, int col, int[][] board, int whatPlace, int[][] boardAfterBestMove) {
		
		int [][] tempBoard = copyBoard(board);
		
		int changed = 0;
		tempBoard[row][col] = whatPlace;
		
		changed += calculateChanges(row, col, whatPlace, tempBoard);
		
		int additionalChanges = 0;
		do{
			additionalChanges = 0;
			for(int trow = 0; trow < 8; trow++){
				for(int tcol = 0; tcol < 8; tcol++){
					if(board[trow][tcol]==whatPlace){
						additionalChanges += calculateChanges(trow, tcol, whatPlace, tempBoard);					
					}
				}
			}
			changed += additionalChanges;			
		}while(additionalChanges != 0);		
		
		for(int trow = 0; trow < 8; trow++){
			for(int tcol = 0; tcol < 8; tcol++){
				boardAfterBestMove[trow][tcol] = tempBoard[trow][tcol];
			}
		}
		
		return changed;
	}
	private int calculateChanges(int row, int col, int whatPlace, int[][] tempBoard) {
		int changed = 0;
		
		for(int rowDir = -1; rowDir <= 1; rowDir++){
			for(int colDir = -1; colDir <= 1; colDir++){
				if(!(rowDir == 0 && colDir == 0)){
					changed += go(row, col, whatPlace, tempBoard, colDir, rowDir);					
				}
			}
		}
		return changed;
	}
	private int go(int row, int col, int whatPlace, int[][] tempBoard, int colDir, int rowDir) {
		int tcol = col + colDir;
		int trow = row + rowDir;
		
		int coeffCol = colDir > 0 ? 1 : (colDir == 0 ? 0 : -8); 
		int coeffRow = rowDir > 0 ? 1 : (rowDir == 0 ? 0 : -8);
		
		boolean changeIsNeeded = false;
		while(coeffRow*trow < 8 && coeffCol*tcol < 8){

			if(tempBoard[trow][tcol] == getOpponentPlace(whatPlace)){
				trow += rowDir;
				tcol += colDir;
				continue;
			}
			else if(tempBoard[trow][tcol] == whatPlace){
				changeIsNeeded = true;
				break;
			}
			break;
		}
		int changed = 0; 

		trow -= rowDir;
		tcol -= colDir;
		while(changeIsNeeded && 
				(rowDir == 0 || rowDir*trow > rowDir*row) && 
				(colDir == 0 || colDir*tcol > colDir*col)){
			changed++;
			tempBoard[trow][tcol] = whatPlace;			

			trow -= rowDir;
			tcol -= colDir;
		}
		return changed;
	}
	
	private int getOpponentPlace(int whatPlace){
		if(whatPlace == EMPTY_PLACE){
			return EMPTY_PLACE;
		}
		return whatPlace == RED_PLACE ? BLACK_PLACE : RED_PLACE;
	}

	private int [][] copyBoard(int[][] board) {
		int [][] tempBoard = new int[8][8];
		for(int trow = 0; trow < 8; trow++){
			for(int tcol = 0; tcol < 8; tcol++){
				tempBoard[trow][tcol] = board[trow][tcol];
			}
		}
		return tempBoard;
	}
	private void printBoard(int[][] board) {		
		for(int cnt = 0; cnt < 8; cnt++){
			if(cnt == 0){
				System.out.print("  " + convertIntToChar(cnt) + " ");
			}
			else{
				System.out.print(convertIntToChar(cnt) + " ");
			}
		}
		System.out.println();
		
		for(int row = 0; row < board.length; row++){
			System.out.print((row+1) + " ");
			for(int col = 0; col < board.length; col++){
				char whatPlace = board[row][col] == RED_PLACE ? 'R' : (board[row][col] == BLACK_PLACE?'B':'-');
				System.out.print(whatPlace + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private void setPosition(String[] places, int[][] board, int whatSet) {
		for(int cnt = 0; cnt < places.length; cnt++){
			String redPlace = places[cnt];
			int row = Integer.parseInt(redPlace.substring(1))-1;
			int col = convertCharToInt(redPlace.substring(0,1).charAt(0));
			board[row][col] = whatSet;
		}
	}
	
	public int bestMove(String[] redPlaces, String[] blackPlaces, String move){
		
//		Set<Integer> reds = new HashSet<Integer>();
//		Set<Integer> blacks = new HashSet<Integer>();
		int[][] board = new int[8][8];
		setPosition(redPlaces, board, RED_PLACE);
		setPosition(blackPlaces, board, BLACK_PLACE);
		int whatPlace = move.equals("Black") ? BLACK_PLACE : RED_PLACE;
		int initialPlaces = move.equals("Black") ? blackPlaces.length : redPlaces.length;
		
		printBoard(board);
		
		int[][] boardAfterBestMove = null;
		int[][] tempBoard = new int[8][8];
		
		int max = -1;
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(board[row][col]!=EMPTY_PLACE){
					continue;
				}
				int res = checkPossibility(row, col, board, whatPlace, tempBoard);
				if(res > max){
					max = res;
					boardAfterBestMove = copyBoard(tempBoard);
				}
			}
		}
		
		int res = initialPlaces+max+1;
		System.out.println("Maximum: " + res);
		
		printBoard(boardAfterBestMove);
		
		return res;
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Tothello t = new Tothello();
//		String [] redPlaces = {"C2","C3","C4","C5","D4","E4","F2","F3","F4","F5","G6"};
//		String [] blackPlaces = {"B1","E1","G1","C6","H7","G4"};
		String [] redPlaces = {"A1","B8","C6","C8","D8"};
		String [] blackPlaces = {"B2","C2","C3","C4","C5"};
		t.bestMove(redPlaces, blackPlaces, "Red");
	
	}

}
