package srm493_div2;

import java.util.ArrayList;
import java.util.Collections;

public class CrouchingAmoebas3 {

	public int count(int[]x,int y[],int A, int T){
		int N = x.length;
		
		int[] nx = new int[2*N];
		int[] ny = new int[2*N];
		for(int i=0; i<N; i++){
			nx[2*i] = x[i]; nx[2*i+1] = x[i]-A;
			ny[2*i] = y[i]; ny[2*i+1] = y[i]-A;			
		}
		int ans = 0;
		for(int i = 0; i < nx.length; i++){
			for(int j = 0; j<ny.length; j++){
				int X=nx[i], Y = ny[j];
				ArrayList<Long> s = new ArrayList<Long>();
				for(int k = 0; k < N; k++){
					long steps = 0;
					if(x[k] < X)steps += X - x[k];
					if(x[k] > X + A) steps += x[k] - X - A;
					if(y[k] < Y) steps += Y - y[k];
					if(y[k] > Y + A) steps += y[k] - Y - A;
					s.add(steps);
				}
				Collections.sort(s);
				int kill = 0;
				long sum = 0;
				for(long steps : s){
					if(sum + steps > T)break;
					sum += steps; kill++;
				}
				ans = Math.max(ans, kill);
			}
		}
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrouchingAmoebas3 ca3 = new CrouchingAmoebas3();
		int[] x = {-1000000000,1000000000};
		int[] y = {-1000000000,1000000000};
		int A = 1;
		int T = 1;
		int cnt = ca3.count(x, y, A, T);
		System.out.println(cnt);
	}

}
