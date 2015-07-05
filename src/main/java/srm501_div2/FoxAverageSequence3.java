package srm501_div2;

public class FoxAverageSequence3 {

	static final int MODULO = 1000000007;
	
	public int theCount(int seq[]){
		int maxNumber = 40;
		int maxSum = maxNumber * seq.length;
		int[][][] am = new int[maxSum+1][2][maxNumber+1];
		am[0][0][0] = 1;
		int pos = 0;
		for(int x : seq){
			int[][][] nam = new int [maxSum+1][2][maxNumber+1];
			for(int oldSum = 0; oldSum <= maxSum; ++oldSum){
				for(int oldNumDecreasing = 0; oldNumDecreasing < 2; ++oldNumDecreasing){
					int[]quick = am[oldSum][oldNumDecreasing];
					for(int oldLast = 0; oldLast <= maxNumber;++oldLast){
						int cnt = quick[oldLast];
						if(cnt==0)continue;
						int max = maxNumber;
						if(pos > 0)
							max = Math.min(max, oldSum / pos);
						int min = 0;
						if(oldNumDecreasing > 0)
							min = oldLast;
						for(int last = min; last <= max; ++last){
							if(x >= 0 && x != last) continue;
							int [] nquick = nam[oldSum+last][last < oldLast ? 1 : 0];
							nquick[last] += cnt;
							if(nquick[last] >= MODULO)nquick[last] -= MODULO;
						}
					}
				}
			}
			++pos;
			am = nam;
		}
		int res = 0;
		for(int sum = 0; sum <= maxSum; ++sum){
			for(int numDecreasing = 0; numDecreasing < 2; ++numDecreasing){
				for(int x : am[sum][numDecreasing]){
					res += x;
					if(res >= MODULO) res -= MODULO;
				}
			}
		}
		return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxAverageSequence3 fas3 = new FoxAverageSequence3();
		int[] seq = {3, -1};
		int count = fas3.theCount(seq);
		System.out.println("count:" + count);
	}

}
