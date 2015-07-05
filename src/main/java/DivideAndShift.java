
import java.util.*;
public class DivideAndShift{
	boolean[] primes = new boolean[1000001];
	ArrayList<Integer> primesList = new ArrayList<Integer>();
	public int getLeast(int N, int M){
		getPrimes();
		int [][]dp = new int[N+1][N+1];
		for(int i = 0; i <= N; i++){
			for(int j = 0; j <= N; j++){
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i = 0; i <= N; i++){
			dp[i][i] = 1;
			dp[i][1] = 0;
		}
		for(int n = 3; n <= N; n++){
			for(int m = 2; m < n; m++){
				int divide = Integer.MAX_VALUE;
				for(Integer p : primesList){
					if(p>n)break;
					if(n%p!=0)continue;
					int newn = n/p;
					int newm = m-(m/newn)*newn;
					if(newm==0)newm = newn;
					if(dp[newn][newm]<divide)divide = dp[newn][newm];
				}
				int left_res = m-1;
				for(int tmp = 1; m-tmp>=1; tmp++){
					if(dp[n][m-tmp]<Integer.MAX_VALUE){
						int left_curr = dp[n][m-tmp]+tmp;
						if(left_res>left_curr)left_res = left_curr;
					}
				}
				int right_res = n-m+1;
				for(int tmp = 1; m+tmp<=n;tmp++){
					if(dp[n][m+tmp]<Integer.MAX_VALUE){
						int right_curr = dp[n][m+tmp]+tmp;
						if(right_res>right_curr)right_res=right_curr;
					}
				}
				dp[n][m] = 1+Math.min(divide,Math.min(left_res,right_res));
			}
		}
		return dp[N][M];
	}
	public void getPrimes(){
		Arrays.fill(primes,true);
		primes[0] = false;
		primes[1] = false;
		for(int i = 2; i*i <= 1000000; i++){
			if(primes[i]){
				int j = 2; 
				while(i*j<=1000000){
					primes[i*j++] = false;
				}
			}
		}
		for(int i = 2; i <= 500000; i++){
			if(primes[i])primesList.add(i);
		}		
	}
	public static void main(String[]args){
		DivideAndShift das = new DivideAndShift();
		int res = das.getLeast(77777, 11111);
		System.out.println(res);
	}
}
