package srm501_div2;

public class FoxAverageSequence4{
	public int theCount(int[] seq){
		int n = seq.length;
		int MOD = 1000000007;
		int VAL = 4;
		
		if(n == 1){
			if(seq[0]==-1)return VAL;
			else return 1;
		}
		
		int[][][] dp = new int[VAL*(n+1)+1][VAL+1][VAL+1];
		for(int i = 0; i <= VAL; i++){
			if(seq[0]!=-1){
				i = seq[0];
			}
			dp[0][i][0] = 1;
			if(seq[0]!=-1){
				break;
			}
		}
		for(int i = 1; i < n; i++){
			for(int curr = 0; curr <= VAL; curr++){
				if(seq[i]!=-1){
					curr = seq[i];
				}
				for(int sum = 0; sum <= VAL*(i+1); sum++){
					if(curr*i <= sum){
						for(int prev = 0; prev <= VAL; prev++){
							if(seq[i-1]!=-1){
								prev = seq[i-1];
							}
							if(i==1){
								dp[curr+sum][curr][prev] = (dp[curr+sum][curr][prev]+dp[sum][prev][0])%MOD;
							}else {
								for(int prevPrev = 0; prevPrev <= VAL; prevPrev++){
									if(seq[i-2]!=-1){
										prevPrev = seq[i-2];
									}
									if(prevPrev > prev && prev > curr){}
									else {
										dp[curr+sum][curr][prev] = (dp[curr+sum][curr][prev]+dp[sum][prev][prevPrev])%MOD;
									}
									if(seq[i-2]!=-1){
										break;
									}
								}
							}
							if(seq[i-1]!=-1){
								break;
							}
						}
					}
				}				
				if(seq[i]!=-1){
					break;
				}				
			}
		}
		int res = 0;
		for(int sum = 0; sum <= VAL*n; sum++){
			for(int curr = 0; curr <= VAL; curr++){
				if(seq[n-1]!=-1){
					curr = seq[n-1];
				}
				for(int prev = 0; prev <= VAL; prev++){
					if(seq[n-2]!=-1){
						prev = seq[n-2];
					}
					res = (res + dp[sum][curr][prev])%MOD;
					if(seq[n-2]!=-1){
						break;
					}
				}
				
				if(seq[n-1]!=-1){
					break;
				}
			}			
		}
		return res;
	}
	public static void main(String[]args){
		FoxAverageSequence4 fas = new FoxAverageSequence4();
		int[] seq = {3, -1};
		int res = fas.theCount(seq);
		System.out.println(res);
	}
}