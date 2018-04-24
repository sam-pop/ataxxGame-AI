import java.util.NoSuchElementException;

class MoveCalc {
	private Ataxx game;
	private AtaxxMove lastAtaxxMove = null;
	private AtaxxMove nextAtaxxMove = null;

	public boolean hasMoreElements(){
		try{
			calcNextAtaxxMove(false);
		}catch ( NoSuchElementException nsee ){
			return false;
		}
		return nextAtaxxMove != null;
	}
	public Object nextElement(){
		return (Object)nextAtaxxMove();
	}

	private boolean infoDirty = true;
	private int j= 0, i =0;
	private int h = -2, v =-2;
	private boolean adjacentConsidered = false;
	private void calcNextAtaxxMove(boolean makeDirty) throws NoSuchElementException {
		if( !infoDirty ){
			infoDirty = makeDirty;
			return;
		}
		infoDirty = makeDirty;

		lastAtaxxMove = nextAtaxxMove;

		if( lastAtaxxMove != null && lastAtaxxMove.isPass )
		{
			nextAtaxxMove = null;
			return;
		}

		int height = Constants.HEIGHT;
		int width = Constants.WIDTH;
		for( ; j < height; j++)
		{
			for( ; i < width; i++, adjacentConsidered = false)
			{
				if( game.board[i][j] != 0 )
					continue;

				// skip adjacent AtaxxMove if already considered 
				if( adjacentConsidered == false ){
					adjacentConsidered = true;
					// find adjacent AtaxxMove
					for( int h = -1; h <= 1; h++)
						for( int v = -1; v <= 1; v++){
							int col = i + h;
							int row = j + v;
							if( col < 0 || col >= width ||
									row < 0 || row >= height)
								continue;
							if( game.board[i][j] == game.playerToGo ){
								nextAtaxxMove =  new AtaxxMove(game.playerToGo,col, row,i, j);
								return;
							}
						}
				}


				if( null == lastAtaxxMove){
					h = v = -3 ;
				}
				else{
					if( lastAtaxxMove.moveDist <3 )
					{
						h = v = -2;
					}
					else{            
						if( lastAtaxxMove.iTo == i &&
								lastAtaxxMove.jTo == j )
						{
							v++;
						}
						else{
							h = v = -3;
						}
					}
				}
				// find next jump AtaxxMove
				for( ; h <= 3; h++ ) {
					for( ; v <= 3; v++ ){
			

						if( Math.max(Math.abs(h),Math.abs(v)) < 3 )
							continue;

						int col = i + h;
						int row = j + v;
						if( col < 0 || col >= width ||
								row < 0 || row >= height)
							continue;
						if( game.board[col][row] == game.playerToGo ){
							nextAtaxxMove =  new AtaxxMove(game.playerToGo,
									col, row,
									i, j);
							return;
						}
					}
					v = -3;
				}
			}
			i =0;
			adjacentConsidered = false;
		}

		if( lastAtaxxMove == null )
			nextAtaxxMove = AtaxxMove.getNewPassAtaxxMove(game.playerToGo);
		else
			nextAtaxxMove = null;
	}

	public AtaxxMove nextAtaxxMove() throws NoSuchElementException{
		calcNextAtaxxMove(true);
		return nextAtaxxMove;
	}
	MoveCalc(Ataxx g){
		game = new Ataxx( g );
	}
}

