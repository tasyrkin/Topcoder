package srm493_div2;

public class StonesGame {


	public String winner(int N, int M, int K, int L){
		if(K==1)return "Draw";
		if(isWinning(M,L,K)){
			return "Romeo";
		}
		for(int i = M-K+1;i<=M+K-1; i+=2){
			if(i<1){
				i+=(-1)*i/2*2;
				continue;
			}
			if(i>N)break;
			if(K%2==1){
				int center = Math.min(i,L)+Math.abs(i-L)/2;
				if(center-(K-1)/2<1||center+(K-1)/2>N)continue;
			}
			else{
				int center = Math.min(i,L)+(Math.abs(i-L)+1)/2;
				if(center-K/2+1<1||center+K/2>N)continue;
			}
			if(!isWinning(i,L,K))return "Draw";
		}
		return "Strangelet";
	}
	public boolean isWinning(int M, int L, int K){
		if(Math.abs(M-L)<=K-1){
			if(K%2==0&&(Math.abs(M-L))/2*2+1==Math.abs(M-L))return true;
			else if(L%2==1&&(M-L)/2*2==M-L)return true;
		}		
		return false;	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StonesGame sg = new StonesGame();
		String str = sg.winner(1000000, 100000, 500000, 600000);
		System.out.println(str);

	}

}
