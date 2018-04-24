
public class AtaxxMove {
	int iFrom;
	int iTo;
	int jFrom;
	int jTo;
	int player;
	boolean isPass;
	int moveDist;

	public AtaxxMove(int player, int iFrom, int jFrom, int iTo, int jTo) {
		super();
		this.iFrom = iFrom;
		this.iTo = iTo;
		this.jFrom = jFrom;
		this.jTo = jTo;
		this.player = player;
		this.isPass = false;

		this.moveDist = Math.max(Math.abs(iTo - iFrom),
				Math.abs(jTo - jFrom));
	}
	@Override
	public String toString() {
		return "Move Made: "+jTo+":"+iTo+":"+jFrom+":"+iFrom;
	}
	public AtaxxMove(int player){
		this.player = player;
		isPass = true;
	}

	static AtaxxMove getNewPassAtaxxMove(int player ){      
		return new AtaxxMove(player);
	}
}
