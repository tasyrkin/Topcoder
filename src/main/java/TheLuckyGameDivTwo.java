
import java.util.*;
public class TheLuckyGameDivTwo{
	public int find(int a, int b, int jLen, int bLen){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		HashSet<Integer> mins = new HashSet<Integer>();
		for(int i = a; i<=b-jLen+1; i++){
			int min = Integer.MAX_VALUE;
			for(int ben = i; ben<=i+jLen-1-bLen+1;ben++){
				Integer curr = map.get(ben+","+(ben+bLen-1));
				if(curr==null){
					curr = 0;
					for(int j = ben; j<ben+bLen;j++){
						if(isLucky(j))curr++;
					}
					map.put(ben+","+(ben+bLen-1),curr);
				}
				if(min>curr)min = curr;
			}
			mins.add(min);
		}
		Integer[] minsArr = mins.toArray(new Integer[mins.size()]);
		Arrays.sort(minsArr);
		return minsArr[minsArr.length-1];
	}
	public boolean isLucky(int num){
		char[] chars = ("" + num).toCharArray();
		int len = chars.length;
		for(int i = 0; i < chars.length; i++){
			if(chars[i]=='4'||chars[i]=='7'){
				len--;
			}
		}		
		return len == 0 ? true : false;
	}
	public static void main(String[]args){
		long start = System.currentTimeMillis();
		TheLuckyGameDivTwo cl = new TheLuckyGameDivTwo();
		int res = cl.find(694, 4474, 1890, 945);
		System.out.println("dur:"+ (System.currentTimeMillis()-start));
		System.out.println(res);		
	}
}