class Node{
	Ataxx game;
	int alpha = MinMax.MINALPHA;
	int beta  = MinMax.MAXBETA;
	
	Node(Ataxx game, AtaxxMove move){
		this.game = new Ataxx();
		this.game.move(move);
	}
}

class MinMax {
	int playerNum;
	int maxDepth = 3;
	static final int MINALPHA = -1000;
	static final int MAXBETA  = 1000;
	int movesConsidered;
	int leafNodes;
	int roots;
	int totalBranchings;
	int totalBoardPositions;
	int numPlayers;

	public int score (Ataxx game){
		int score = 0;
		score -= game.getPlayerScore(2)-game.getPlayerScore(1);
		return score;
	}

	private int minimax_AB(int depth, Node node, int alpha, int beta ){
		node.alpha = alpha;
		node.beta = beta;
		movesConsidered++;
		if( depth >= maxDepth )
		{
			leafNodes++;
			return score(node.game);
		}

		int val;
		if( depth % numPlayers == 0 ) // max node
		{
			MoveCalc me = new MoveCalc(node.game); 
			roots++;
			while(me.hasMoreElements()){
				totalBranchings++;
				Node ni = new Node(node.game, me.nextAtaxxMove());
				val = minimax_AB( depth+1, ni, node.alpha, node.beta );
				node.alpha = Math.max(node.alpha, val);
				if (node.alpha >= node.beta)
					break;
			}
			return node.alpha;
		}
		else                   // min Node
		{
			MoveCalc me = new MoveCalc(node.game); 
			roots++;
			while(me.hasMoreElements()){
				totalBranchings++;
				Node ni = new Node(node.game, me.nextAtaxxMove());
				val = minimax_AB( depth+1, ni, node.alpha, node.beta );
				node.beta = Math.min(node.beta, val);
				if (node.beta <= node.alpha)
					break;
			}
			return node.beta;
		}
	}


	public void init(int playerNum ){
		this.playerNum = playerNum;
	}
	public AtaxxMove getNextMove(Ataxx game0){
		numPlayers = game0.numPlayers;
		movesConsidered = leafNodes = totalBranchings = roots = 0;
		//long startTime = System.currentTimeMillis();
		MoveCalc me = new MoveCalc(game0);
		int alpha = MinMax.MINALPHA;
		final int beta  = MinMax.MAXBETA;
		AtaxxMove bestMove = null;
		while( me.hasMoreElements())
		{
			roots++;
			AtaxxMove m =  me.nextAtaxxMove();
			totalBranchings++;
			Node ni;
			int val;
			try{
				ni = new Node(game0, m);
				val = minimax_AB( 1, ni, alpha, beta );
			}catch(Exception ime){
				ime.printStackTrace();
				continue;
			}
			if( val > alpha)
			{
				alpha = val;
				bestMove = m;
			}
			if (alpha >= beta)
				break;
		}
		//long duration = System.currentTimeMillis() - startTime;
		//System.out.print((duration /1000.0)+" seconds\n");
		return bestMove;
	}
}
