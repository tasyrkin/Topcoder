

public class FoxListeningToMusic {

	public double[] getProbabilities(int[] length, int T){
		int n = length.length;
		double[] dp = new double[T + 1];
		dp[0] = 1. / n;
		for(int i = 1; i <= T; i++){
			for(int j = 0; j < n; j++){
				int l = length[j];
				if(l <= i && dp[i - l] > 1e-16){
					dp[i] += dp[i - l] / n;
				}
			}
		}
		double[] result = new double[n];
		for(int j = 0; j < n; j++){
			for(int i = Math.max(0, T - length[j] + 1); i <= T; i++){
				result[j] += dp[i];
			}
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxListeningToMusic f = new FoxListeningToMusic();
		int[] length = {1,2};
		f.getProbabilities(length , 2);

	}

}
