import java.util.Scanner;


public class Ataxx {
	int[][] board;
	int playerScores[];
	int numPlayers;
	int emptySquares;
	boolean movedYet;
	int playerToGo;
	int boardHeight = Constants.HEIGHT;
	int boardWidth = Constants.WIDTH;
	int countTurns;
	int winner;
	int moveType;
	static AtaxxMove move;


	public Ataxx(){
		initialize();
	}

	public void initialize(){ //initialize board and game definitions
		board=new int[boardHeight][boardWidth];
		for (int i=0;i<boardHeight;i++)
			for(int j=0;j<boardWidth;j++)
				board[i][j]=Constants.BOARD[i][j];

		movedYet=false;
		numPlayers=Constants.PLAYERS;
		emptySquares=Constants.EMPTY_TILES;
		playerToGo=Constants.FIRST_PLAYER;
		countTurns=0;
		playerScores = new int[numPlayers+1];
	}

	Ataxx(Ataxx oldGame) {
		playerToGo= oldGame.playerToGo;
		numPlayers= oldGame.numPlayers;
		emptySquares = oldGame.emptySquares;
		playerScores = new int[numPlayers];
		board = new int[boardWidth][boardHeight];
		System.arraycopy(oldGame.playerScores, 0,
				playerScores, 0,
				numPlayers);

		for( int c = 0; c < boardWidth; c++){
			System.arraycopy(oldGame.board[c], 0, board[c], 0, boardHeight);
		}

	}

	public int getPlayerScore(int player){
		calcPlayerScores();
		return playerScores[(player)];
	}

	public void calcPlayerScores(){
		playerScores[0]=0;
		playerScores[1]=0;
		for (int i=0;i<boardHeight;i++){
			for(int j=0;j<boardWidth;j++){
				if (board[i][j]==1)
					playerScores[1]++;
				if (board[i][j]==2)
					playerScores[2]++;
			}
		}
	}
	public int getEmptySquares(){
		calcEmptySquares();
		return emptySquares;
	}

	public void calcEmptySquares(){
		emptySquares=0;
		for (int i=0;i<boardHeight;i++){
			for(int j=0;j<boardWidth;j++){
				if (board[i][j]==0)
					emptySquares++;
			}
		}
	}

	public void cloneToken(int player, int i, int j){ //i j the new movement coordinates to clone to
		if (board[i][j] == 0){
			board[i][j] = player;
			if (i+1 < boardWidth && j+1 < boardHeight){
				if ((board[i+1][j+1] != 0) && (board[i+1][j+1] != player)){
					board[i+1][j+1] = player;
				}
			}
			if (i+1 < boardWidth){
				if ((board[i+1][j] != 0) && (board[i+1][j] != player)){
					board[i+1][j] = player;
				}
			}
			if (i+1 < boardWidth && j-1 >= 0){
				if ((board[i+1][j-1] != 0) && (board[i+1][j-1] != player)){
					board[i+1][j-1] = player;
				}
			}
			if (j-1 >= 0){
				if ((board[i][j-1] != 0) && (board[i][j-1] != player)){
					board[i][j-1] = player;
				}
			}
			if (i-1 >=0 && j-1 >= 0){	
				if ((board[i-1][j-1] != 0) && (board[i-1][j-1] != player)){
					board[i-1][j-1] = player;
				}
			}
			if (i-1 >= 0){
				if ((board[i-1][j] != 0) && (board[i-1][j] != player)){
					board[i-1][j] = player;
				}
			}
			if (i-1 >= 0 && j+1 < boardHeight){
				if ((board[i-1][j+1] != 0) && (board[i-1][j+1] != player)){
					board[i-1][j+1] = player;
				}
			}
			if (j+1 < boardHeight){
				if ((board[i][j+1] != 0) && (board[i][j+1] != player)){
					board[i][j+1] = player;
				}
			}
		}
	}

	public boolean move(AtaxxMove m){ //do the player's chosen move
		int mType = validMove(m);
		if (mType != -1){
			if (mType == 1){
				cloneToken(m.player, m.iTo, m.jTo);
				return true;
			}
			if ((mType == 2) || (mType == 3)){
				board[m.iFrom][m.jFrom]=0;
				cloneToken(m.player, m.iTo, m.jTo);
				return true;
			}	
		}
		return false;
	}

	public int validMove(AtaxxMove m){ //check if a valid move has been issued and return the type of movement (how many steps)
		if ((m.iFrom >= 0 && m.iFrom < boardWidth)  && (m.jFrom >= 0 && m.jFrom < boardHeight) && (m.iTo>=0 && m.iTo < boardWidth) && (m.jTo >= 0 && m.jTo < boardHeight)){
			if (board[m.iFrom][m.jFrom] != m.player){
				return -1;
			}
			if (board[m.iTo][m.jTo] != 0){
				return -1;
			}
			if (((m.iTo-m.iFrom) == 1) || ((m.iTo-m.iFrom)== -1) || ((m.iTo-m.iFrom) == 0)){
				if (((m.jTo-m.jFrom) == 0) || ((m.jTo-m.jFrom) == 1) || ((m.jTo-m.jFrom) == -1)){
					return 1;	
				}
			}
			if (((m.iTo-m.iFrom) == 2) || ((m.iTo-m.iFrom)== -2) || ((m.iTo-m.iFrom) == 0)){
				if (((m.jTo-m.jFrom) == 0) || ((m.jTo-m.jFrom) == 2) || ((m.jTo-m.jFrom) == -2)){
					return 2;	
				}
			}
			if (((m.iTo-m.iFrom) == 3) || ((m.iTo-m.iFrom)== -3) || ((m.iTo-m.iFrom) == 0)){
				if (((m.jTo-m.jFrom) == 0) || ((m.jTo-m.jFrom) == 3) || ((m.jTo-m.jFrom) == -3)){
					return 3;	
				}
			}		
		}
		return -1;
	}

	public void printBoard(){//print the board state
		for (int i=0;i<boardHeight;i++){
			for(int j=0;j<boardWidth;j++)
				System.out.print(board[i][j]+" ");
			System.out.println();
		}
	}

	public boolean gameOver(){ //Goal function: is game over?
		int count1=0,count2=0,count0=0; //count appearances of each soldier/empty tile on board
		for (int i=0;i<boardHeight;i++)
			for(int j=0;j<boardWidth;j++){
				if(board[i][j]==0)
					count0++;
				else if (board[i][j]==1)
					count1++;
				else
					count2++;
			}

		if(count0==0||count1==0|| count2==0||this.countTurns==100){
			if (count1>count2)
				this.winner=1;
			if (count2>count1)
				this.winner=2;
			else this.winner = -1;
			return true;
		}
		return false;
	}

	@SuppressWarnings("resource")
	public void getInput(){ //get input move from white player (2)
		int player = 2;
		int iFrom = 0,jFrom = 0;
		int iTo = 0, jTo = 0;
		int tempCount=1;
		Scanner s = new Scanner(System.in); 
		String temp;
		System.out.println("Please enter your move: (FORMAT EXAMPLE: 6:1:6:2)");
		temp=s.next();
		for(int i=0;i<temp.length();i++)
			if(temp.charAt(i)!=':'){
				if(tempCount==1)
					jFrom=Character.getNumericValue(temp.charAt(i));
				else if (tempCount==2)
					iFrom=Character.getNumericValue(temp.charAt(i));
				else if (tempCount==3)
					jTo=Character.getNumericValue(temp.charAt(i));
				else 
					iTo=Character.getNumericValue(temp.charAt(i));
				tempCount++;
			}
		move = new AtaxxMove(player, iFrom,jFrom,iTo,jTo);
	}

	public static void main(String[] args){ //Main
		Ataxx game = new Ataxx();
		MinMax mm = new MinMax();
		mm.init(1);

		while(!game.gameOver()){ //game still on
			mm.init(1);
			game.printBoard();
			game.getInput();	
			int vP = game.validMove(move);
			if (vP == -1){
				while (vP == -1){
					game.getInput();
					vP = game.validMove(move);
				}
			}
			if (vP != -1){
				game.move(move);
				game.calcPlayerScores();
				game.countTurns++;
			}
			int vAi = game.validMove(mm.getNextMove(game));
			if (vAi == -1){
				game.validMove(mm.getNextMove(game));
			}
			if (vAi != -1){
				game.move(mm.getNextMove(game));
				game.calcPlayerScores();
				game.countTurns++;
			}

			System.out.println(mm.getNextMove(game).toString());


		}
		game.printBoard();
		//print the winner
		if (game.winner == -1)
			System.out.println("THE GAME IS TIED!");
		if (game.winner == 1)
			System.out.println("THE WINNER IS: Black!");
		else System.out.println("THE WINNER IS: White!");
	}

}
