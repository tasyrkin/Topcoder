package srm493_div2;

import java.util.Arrays;

public class CrouchingAmoebas2 {
	public int count(int[] x, int[] y, int A, int T){
		long[] xl = new long[x.length]; 
		long[] yl = new long[x.length];
		for(int i = 0; i < x.length; i++){
			xl[i] = x[i];
			yl[i] = y[i];
		}
		int maxCount = -1;
		for(int i=0; i<xl.length;i++){
			int currCount = 0;
			currCount = getCountForBox(xl, yl, xl[i], yl[i], xl[i]+A, yl[i]+A, T);
			if(currCount>maxCount)maxCount=currCount;
			currCount = getCountForBox(xl, yl, xl[i]-A, yl[i], xl[i], yl[i]+A, T);
			if(currCount>maxCount)maxCount=currCount;
			currCount = getCountForBox(xl, yl, xl[i]-A, yl[i]-A, xl[i], yl[i], T);
			if(currCount>maxCount)maxCount=currCount;
			currCount = getCountForBox(xl, yl, xl[i], yl[i]-A, xl[i]+A, yl[i], T);
			if(currCount>maxCount)maxCount=currCount;
		}
		return maxCount;
	}

	public int getCountForBox(long[] x, long[] y, long botx, long boty, long topx, long topy, int T){
		long[] steps = new long[x.length]; 
		for(int j=0; j<x.length; j++){
			if(x[j]>=botx&&x[j]<=topx&&y[j]>=boty&&y[j]<=topy){
				steps[j] = 0;
				continue;
			}
			if(x[j]>=botx&&x[j]<=topx){
				long stepsy = Math.min(Math.abs(y[j]-boty),Math.abs(y[j]-topy));
				steps[j] = stepsy;
				continue;
			}
			if(y[j]>=boty&&y[j]<=topy){
				long stepsx = Math.min(Math.abs(x[j]-botx),Math.abs(x[j]-topx));
				steps[j] = stepsx;
				continue;
			}
			long stepsx = Math.min(Math.abs(x[j]-botx),Math.abs(x[j]-topx));
			long stepsy = Math.min(Math.abs(y[j]-boty),Math.abs(y[j]-topy));
			steps[j] = stepsx+stepsy;
		}
		Arrays.sort(steps);		
		int currCount = 0;
		for(int j = 0; j < steps.length; j++){
			if(T>=steps[j]){
				currCount++;
				T-=steps[j];
			}
			else{
				break;
			}
		}
		return currCount;
	}	
	
	public static void main(String args[]){
		CrouchingAmoebas2 ca2 = new CrouchingAmoebas2();
		int[] x = {-1000000000,1000000000};
		int[] y = {-1000000000,1000000000};
		int A = 1;
		int T = 1;
		int cnt = ca2.count(x, y, A, T);
		System.out.println(cnt);
	}

}
