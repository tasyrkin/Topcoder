package srm493_div2;

public class CrouchingAmoebas {
	
	int[] xmain;
	int[] ymain;
	boolean isFirst = true;
	
	public int count(int[] x, int[] y, int A, int T){
		if(isFirst){
			xmain = new int[x.length];
			ymain = new int[y.length];
			for(int i=0;i<x.length;i++){
				xmain[i] = x[i];
				ymain[i] = y[i];
			}
		}
		//System.out.println(T);
		if(T==0){
			int maxWithinRange = -1;
			for(int i=0;i<x.length;i++){
				int currWithinRange = getMaxWithinBox(x, y, maxWithinRange, i, x[i],y[i],x[i]+A,y[i]+A);
				if(currWithinRange>maxWithinRange)maxWithinRange=currWithinRange;
				currWithinRange = getMaxWithinBox(x, y, maxWithinRange, i, x[i]-A,y[i],x[i],y[i]+A);
				if(currWithinRange>maxWithinRange)maxWithinRange=currWithinRange;
				currWithinRange = getMaxWithinBox(x, y, maxWithinRange, i, x[i]-A,y[i]-A,x[i],y[i]);
				if(currWithinRange>maxWithinRange)maxWithinRange=currWithinRange;
				currWithinRange = getMaxWithinBox(x, y, maxWithinRange, i, x[i],y[i]-A,x[i]+A,y[i]);
			}
			return maxWithinRange;
		}

		int[] tmpX = new int[x.length];
		int[] tmpY = new int[y.length];
		for(int i=0;i<x.length;i++){
			tmpX[i]=x[i];
			tmpY[i]=y[i];
		}
		int maxCount = -1;
		for(int i=0;i<x.length;i++){
			int currCount = 0;
			if(distance(tmpX[i]-1,tmpY[i],i)>distance(tmpX[i],tmpY[i],i)){
				tmpX[i]-=1;
				currCount = count(tmpX,tmpY,A,T-1);
				if(currCount > maxCount)maxCount=currCount;
				tmpX[i]+=1;
			}
			if(distance(tmpX[i]+1,tmpY[i],i)>distance(tmpX[i],tmpY[i],i)){
				tmpX[i]+=1;
				currCount = count(tmpX,tmpY,A,T-1);
				if(currCount > maxCount)maxCount=currCount;
				tmpX[i]-=1;
			}
			if(distance(tmpX[i],tmpY[i]-1,i)>distance(tmpX[i],tmpY[i],i)){
				tmpY[i]-=1;
				currCount = count(tmpX,tmpY,A,T-1);
				if(currCount > maxCount)maxCount=currCount;
				tmpY[i]+=1;				
			}
			if(distance(tmpX[i],tmpY[i]+1,i)>distance(tmpX[i],tmpY[i],i)){
				tmpY[i]+=1;
				currCount = count(tmpX,tmpY,A,T-1);
				if(currCount > maxCount)maxCount=currCount;
				tmpY[i]-=1;
			}
		}
		return maxCount;
	}

	private int getMaxWithinBox(int[] x, int[] y, int maxWithinRange,
			int i, int leftbottomx, int leftbottomy, int righttopx, int righttopy) {
		int currWithinRange = 0;
		for(int j=0;j<x.length;j++){
			//if(i==j)continue;
			currWithinRange += checkIfWIthin(x, y, i, leftbottomx, leftbottomy, righttopx, righttopy, j);
		}
		if(maxWithinRange<currWithinRange)maxWithinRange=currWithinRange;
		return maxWithinRange;
	}

	private int checkIfWIthin(int[] x, int[] y, int i,
			int leftbottomx, int leftbottomy, int righttopx, int righttopy,
			int j) {
		if(x[j]>=Math.min(x[i], leftbottomx)&&x[j]<=Math.max(x[i], righttopx)
		&&y[j]>=Math.min(y[i], leftbottomy)&&y[j]<=Math.max(y[i], righttopy)){
			return 1;
		}
		return 0;
	}
	
	private int distance(int x, int y, int i){
		return Math.abs(x-xmain[i])+Math.abs(y-ymain[i]);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		CrouchingAmoebas ca = new CrouchingAmoebas();
		int[] x = {-1000000000,1000000000};
		int[] y = {-1000000000,1000000000};
		int A = 1;
		int T = 8;
		int count = ca.count(x, y, A, T);
		System.out.println(count);
	}
}
