import java.util.Arrays;


public class MergersDivTwo {
	public double findMaximum(int[] revenues, int k){	
		int n = revenues.length;
		Arrays.sort(revenues);
		double[] dp = new double[n+1];
		boolean[]flag = new boolean[n+1];
		Arrays.fill(flag, false);
		for(int i = k; i <= n; i++){
			if(i==n||n-i+1>=k){
				int sum = 0;
				for(int j = 0; j < i; j++){
					sum += revenues[j];
				}
				if(!flag[i]){
					dp[i] = (double)sum / i;
					flag[i] = true;
				} else {
					dp[i] = Math.max((double)sum / i, dp[i]);
				}
				for(int j = k-1; j <= n; j++){
					if(i+j==n || n-i-j+1>=k){
						int sum1 = 0;
						for(int m = i; m < i+j; m++){
							sum1 += revenues[m];
						}
						if(!flag[i+j]){
							dp[i+j] = (dp[i] + (double)sum1) / (j+1);
							flag[i+j] = true;
						} else {							
							dp[i+j] = Math.max((dp[i] + (double)sum1) / (j+1), dp[i+j]);
						}
					}
				}
			}			
		}
		
		return dp[n];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MergersDivTwo m = new MergersDivTwo();
		int[] revenues = {-100, -100, -100, -100, -100, 100};
		int k = 4;
		double r = m.findMaximum(revenues , k);
		System.out.println(r);
	}

}
