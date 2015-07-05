package srm501_div2;

public class FoxPlayingGame{
	public double theMax(int nA, int nB, int paramA, int paramB){
		double scoreA = paramA/1000.0;
		double scoreB = paramB/1000.0;
		double ret = 0.0;
		if(scoreA>0){
			if(scoreB<0){
				if(nB%2==0){
					ret = scoreA*nA;
					for(int i = 0; i<nB; i++){
						ret *= scoreB;
					}
				}
				else{
					ret = scoreA*nA;
					for(int i = 0; i<nB-1; i++){
						ret *= scoreB;
					}
				}
			}
			else if(scoreB==0)ret = scoreA*nA;
			else{
				ret = scoreA*nA;
				for(int i = 0; i<nB; i++){
					ret *= scoreB;
				}
			}
		}		
		return ret;
	}
}