
import java.util.*;
public class Over9000Rocks{
	public static class Pair{
		int s;
		int f;
		public Pair(int si, int fi){
			s = si;
			f = fi;
		}
		public String toString(){
			return "[" + s + "," + f + "]";
		}
	}
	public int countPossibilities(int[] lowerBound, int[] upperBound){
		int n = lowerBound.length;
		int size = (int)Math.pow(2,n);
		//Set<Integer>set = new HashSet<Integer>();
		List<Pair> res = new ArrayList<Pair>();
		for(int i = 1; i <= size; i++){
			List<Integer> box = new ArrayList<Integer>();
			for(int j = 0; j < n; j++){
				if(((i>>j)&0x1)==1){
					box.add(j);
				}
			}
			int max = 0;
			int min = 0;
			for(int num : box){
				max += upperBound[num];
				min += lowerBound[num];
			}
			if(max>9000){
				if(min < 9001)min = 9001;
				Pair np = new Pair(min, max);
				boolean f = false;
				for(Pair p : res){
					if(p.s>np.f||p.f<np.s){
						continue;
					}
					p.s = Math.min(p.s,np.s);
					p.f = Math.max(p.f,np.f);
					f = true;
					break;
				}
				if(!f)res.add(np);
			}
		}
		int sizeR = 0;
		for(Pair p : res){
			sizeR += p.f-p.s+1;
		}
		return sizeR;
	}
	public static void main(String[] args) {
		Over9000Rocks o = new Over9000Rocks();
		int[] lowerBound = {9000,90000,1,10};
		int[] upperBound = {9000,90000,3,15};
		int r = o.countPossibilities(lowerBound, upperBound);
		System.out.println(r);
	}

}
