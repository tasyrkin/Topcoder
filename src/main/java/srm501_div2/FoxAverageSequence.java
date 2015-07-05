package srm501_div2;

public class FoxAverageSequence{

	final int MOD = 1000000007;
	final int MAX = 40;
	public int theCount(int[] seq){
		int numElems = seq.length;
		int maxSum = numElems*MAX+1;
		int[][][][]a = new int[numElems][maxSum][MAX+1][2];
		for(int k=0; k<=MAX;k++){
			a[0][k][k][0]=1;
			a[0][k][k][1]=1;
		}
		for(int i = 1; i < numElems; i++){
			for(int j = 0; j<(i+1)*MAX+1; j++){
				for(int k=0; k<=MAX; k++){
					if(k*(i-1)>j-k||j-k<0)continue;
					int tmpSum = 0;
					for(int x=k+1; x<numElems;x++){
						tmpSum += a[i-1][j-k][x][0];
						if(tmpSum>=MOD)tmpSum-=MOD;
					}
					a[i][j][k][1] = tmpSum;
					tmpSum = 0;
					for(int x=0; x<=k; x++){
						tmpSum += a[i-1][j-k][x][1];
						if(tmpSum>=MOD)tmpSum-=MOD;
					}
					a[i][j][k][0] = tmpSum;
				}
			}
		}

		//(sum of a[N][z][x][y], where 0 <= z <= 40*N, 0 <= x <= 40 and 0 <= y <= 1)
		int sum = 0;
		for(int z = 0; z<maxSum; z++){
			for(int x = 0; x <= MAX; x++){
				for(int y = 0; y <= 1; y++){
					sum += a[seq.length-1][z][x][y];
					if(sum>MOD)sum-=MOD;
				}
			}
		}
		return sum;
	}

	public static void main(String[]args){
		FoxAverageSequence fas = new FoxAverageSequence();
		//int[] seq = {-1, 40, -1, -1, -1, 10, -1, -1, -1, 21, -1};
		int[] seq = {3,3};
		int sum = fas.theCount(seq);
		System.out.println(sum);
	}
}