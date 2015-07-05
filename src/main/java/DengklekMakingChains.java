public class DengklekMakingChains{
	public int maxBeauty(String[] chains){
		int len = chains.length;
		int[]left = new int[len];
		int[]right = new int[len];
		int sumFull = 0;
		int maxLeft = 0;
		int maxLeftIdx = -1;
		int maxRight = 0;
		int maxRightIdx = -2;
		int maxMiddle = 0;
		for(int i = 0; i < len; i++){
			char[]a = chains[i].toCharArray();
			if(a[0]!='.'&&a[1]!='.'&&a[2]!='.'){
				sumFull += Integer.parseInt(a[0]+"");
				sumFull += Integer.parseInt(a[1]+"");
				sumFull += Integer.parseInt(a[2]+"");
			} else if(a[0]!='.'&&a[1]!='.'&&a[2]=='.'){
				int curr = 0;
				curr += Integer.parseInt(a[0]+"");
				curr += Integer.parseInt(a[1]+"");
				left[i] = curr;
				if(maxLeft<curr){
					maxLeft = curr;
					maxLeftIdx = i;
				}
			} else if(a[0]!='.'&&a[1]=='.'&&a[2]=='.'){
				int curr = 0;
				curr += Integer.parseInt(a[0]+"");
				left[i] = curr;
				if(maxLeft<curr){
					maxLeft = curr;
					maxLeftIdx = i;
				}
			} else if(a[0]=='.'&&a[1]!='.'&&a[2]!='.'){
				int curr = 0;
				curr += Integer.parseInt(a[1]+"");
				curr += Integer.parseInt(a[2]+"");
				right[i] = curr;
				if(maxRight<curr){
					maxRight = curr;
					maxRightIdx = i;
				}
			} else if(a[0]=='.'&&a[1]=='.'&&a[2]!='.'){
				int curr = 0;
				curr += Integer.parseInt(a[2]+"");
				right[i] = curr;
				if(maxRight<curr){
					maxRight = curr;
					maxRightIdx = i;
				}
			} else if(a[0]=='.'&&a[1]!='.'&&a[2]=='.'){
				int curr = Integer.parseInt(a[1]+"");
				if(maxMiddle<curr)maxMiddle = curr;
			} else if(a[0]!='.'&&a[1]=='.'&&a[2]!='.'){
				left[i] = Integer.parseInt(a[0]+"");
				if(maxLeft<left[i]){
					maxLeft = left[i];
					maxLeftIdx = i;
				}
				if(maxRight<right[i]){
					maxRight = right[i];
					maxRightIdx = i;
				}				
			}
		}
		if(maxLeftIdx == maxRightIdx){			
			int maxLeftCurr = -1;
			for(int i = 0; i < len; i++){
				if(i==maxLeftIdx)continue;
				if(maxLeftCurr<left[i]&&left[i]>0)maxLeftCurr=left[i];
			}
			int maxRightCurr = -1;
			for(int i = 0; i < len; i++){
				if(i==maxRightIdx)continue;
				if(maxRightCurr<right[i]&&right[i]>0)maxRightCurr=right[i];
			}
			if(maxLeftCurr!=-1&&maxRightCurr!=-1){
				int mid = Math.max(maxRight+maxLeftCurr,maxLeft+maxRightCurr);
				return Math.max(maxMiddle, mid+sumFull);
			}
			if(maxLeftCurr!=-1){
				int mid = Math.max(maxRight+maxLeftCurr,maxLeft);
				return Math.max(maxMiddle, mid+sumFull);
			}
			if(maxRightCurr!=-1){
				int mid = Math.max(maxLeft+maxRightCurr,maxRight);
				return Math.max(maxMiddle, mid+sumFull);
			}
			return Math.max(maxMiddle, Math.max(maxLeft,maxRight)+sumFull);
		} else {
			return Math.max(maxMiddle, maxRight+maxLeft+sumFull);
		}
	}
	public static void main(String[]args){
		DengklekMakingChains dmc = new DengklekMakingChains();
		String[] chains = {"1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1", "1.1"};
		int res = dmc.maxBeauty(chains);
		System.out.println(res);
	}
}