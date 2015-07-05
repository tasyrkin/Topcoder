

public class ChessCover {

	private void go(
			  int row
			, int col
			, char[][] chessBoard
			, int colDir
			, int rowDir
			, java.util.Set<String> positions
			, java.util.Set<String> underAttack
			, Integer maxSteps) {		
		
		int tcol = col + colDir;
		int trow = row + rowDir;
		
		int steps = 1;
		
		int coeffRow = rowDir > 0 ? 1 : (rowDir == 0 ? 0 : (-1)*chessBoard.length);
		int coeffCol = colDir > 0 ? 1 : (colDir == 0 ? 0 : (-1)*chessBoard[0].length); 
		
		while(coeffRow*trow < chessBoard.length 
				&& coeffCol*tcol < chessBoard[0].length){
			String index = "" + trow + tcol; 
			if(!positions.contains(index)){
				underAttack.add(index);
			}
			else{
				break;
			}
			trow += rowDir;
			tcol += colDir;
			steps++;
			if(maxSteps != null && steps > maxSteps){
				break;
			}
		}
		
	}	
	
	private void go(
			  int row
			, int col
			, char[][] chessBoard
			, int colDir
			, int rowDir
			, java.util.Set<String> positions
			, java.util.Set<String> underAttack) {		
		go(row, col, chessBoard, colDir, rowDir, positions, underAttack, null);
	}
	
	
	public int getSafe (String[] boardLayout){
		
		int horizLength = boardLayout[0].length();
		int verticalLength = boardLayout.length;
		char[][] chessBoard = new char[boardLayout.length][horizLength];
		
		java.util.Set<String> positions = new java.util.HashSet<String>();
		
		for(int row = 0; row < boardLayout.length; row++){			
			for(int col = 0; col < horizLength; col++){
				chessBoard[row][col] = boardLayout[row].charAt(col);
				if(chessBoard[row][col] != 'U'){
					positions.add(""+row+col);
				}
			}
		}		
		//printBoard(chessBoard);
		java.util.Set<String> underAttack = new java.util.HashSet<String>();
		
		for(int row = 0; row < chessBoard.length; row++){			
			for(int col = 0; col < chessBoard[0].length; col++){
				switch (chessBoard[row][col]){
					case 'Q':{
						for(int rowDir = -1; rowDir <= 1; rowDir++){
							for(int colDir = -1; colDir <= 1; colDir++){
								if(!(rowDir==0 && colDir==0)){
									go(row, col, chessBoard, colDir, rowDir, positions, underAttack);
								}
							}
						}
						break;
					}
					case 'R':{
						go(row, col, chessBoard, -1, 0, positions, underAttack);
						go(row, col, chessBoard, 1, 0, positions, underAttack);
						go(row, col, chessBoard, 0, -1, positions, underAttack);
						go(row, col, chessBoard, 0, 1, positions, underAttack);
						break;
					}
					case 'B':{
						go(row, col, chessBoard, -1, -1, positions, underAttack);
						go(row, col, chessBoard, -1, 1, positions, underAttack);
						go(row, col, chessBoard, 1, -1, positions, underAttack);
						go(row, col, chessBoard, 1, 1, positions, underAttack);
						break;
					}
					case 'P':{
						go(row, col, chessBoard, -1, -1, positions, underAttack, 1);
						go(row, col, chessBoard, -1, 1, positions, underAttack, 1);
						go(row, col, chessBoard, 1, -1, positions, underAttack, 1);
						go(row, col, chessBoard, 1, 1, positions, underAttack, 1);
						break;
					}
					case 'K':{
						goKnight(row, col, positions, underAttack, verticalLength, horizLength);
						break;
					}
				}
			}
		}		
		
		underAttack.addAll(positions);
		
		int res = 0;
		
		for(int row = 0; row < chessBoard.length; row++){			
			for(int col = 0; col < chessBoard[0].length; col++){
				String index = "" + row + col;
				if(!underAttack.contains(index)){
					res++;
					chessBoard[row][col] = '+';
				}
			}
		}
		
		//printBoard(chessBoard);
		
		return res;
	}

	private void goKnight(int row, int col, java.util.Set<String> positions, java.util.Set<String> underAttack, int verticalLength, int horizontalLength) {
		String knightIndex = null;
		
		for(int rowDir = -1; rowDir <= 1; rowDir+=2){
			for(int colDir = -2; colDir <= 2; colDir+=4){
				
				if(rowDir == 0 && colDir == 0){
					continue;
				}
				
				if((row + rowDir) >= 0 
						&& (row + rowDir) <= verticalLength
						&& (col + colDir) >= 0
						&& (col + colDir) <= horizontalLength){
					int trow = row+rowDir;
					int tcol = col+colDir;
					knightIndex = "" + trow + tcol;
					if(!positions.contains(knightIndex)){
						underAttack.add(knightIndex);
					}
				}
			}
		}
		for(int rowDir = -2; rowDir <= 2; rowDir+=4){
			for(int colDir = -1; colDir <= 1; colDir+=2){
				if(rowDir == 0 && colDir == 0){
					continue;
				}
				
				if((row + rowDir) >= 0 
						&& (row + rowDir) <= verticalLength
						&& (col + colDir) >= 0
						&& (col + colDir) <= horizontalLength){
					int trow = row+rowDir;
					int tcol = col+colDir;
					knightIndex = "" + trow + tcol;
					if(!positions.contains(knightIndex)){
						underAttack.add(knightIndex);
					}
				}
			}
		}
		
	}

	private void printBoard(char[][] chessBoard) {
		
		for(int col = 0; col < chessBoard[0].length; col++){
			if(col == 0){
				System.out.print("   ");
			}
			System.out.print((col+1) + " ");
		}
		System.out.println();
		
		for(int row = 0; row < chessBoard.length; row++){			
			System.out.print((row+1) + "  ");
			for(int col = 0; col < chessBoard[row].length; col++){
				System.out.print(chessBoard[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChessCover cc = new ChessCover();		
		//String[] boardLayout = {"UUU","UPU", "UUU"};
		//String[] boardLayout = {"UUUUU","UQUQU", "UUUUU"};
		//String[] boardLayout = {"UUUU","UUUU","QUUU", "UUUU"};
		//String[] boardLayout = {"UUUUU","UUBUU", "UUUUU"};
		//String[] boardLayout = {"UUUUU","UURUU", "UUUUU"};
		String[] boardLayout = {"UUUUU","UUUUU","UPKUU", "UUUUU","QUUUU"};
		int res = cc.getSafe(boardLayout);
		System.out.println(res);

	}
}
