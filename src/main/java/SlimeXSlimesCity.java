

import java.util.HashSet;

public class SlimeXSlimesCity {

	static HashSet<String> names = new HashSet<String>();
	public int merge(int[] population){
		long[] p = new long[population.length];
		for(int i = 0; i < population.length; i++){
			p[i] = population[i];
		}
		merge2(p);
		return names.size();
	}
	private void merge2(long p[]) {
		int cnt = 0;
		int idx = 0;
		for(int i = 0; i < p.length; i++){
			if(p[i]!=-1){
				cnt++;
				idx = i;
			}
		}
		if(cnt==1){
			names.add(""+idx);
			return;
		}
		for(int i = 0; i < p.length-1; i++){
			if(p[i]==-1)continue;
			for(int j = i+1; j < p.length; j++){
				if(p[j]==-1)continue;
				long[] newp = p.clone();
				if(p[i]>p[j]){
					newp[i] = p[j]+p[i];
					newp[j] = -1;
					merge2(newp);
				}
				else if(p[i]<p[j]){
					newp[j] = p[j]+p[i];
					newp[i] = -1;
					merge2(newp);
				}
				else{
					long vi = p[i];
					long vj = p[j];
					newp[j] = vi+vj;
					newp[i] = -1; 
					merge2(newp);
					newp[i] = vi+vj;
					newp[j] = -1;
					merge2(newp);
				}
			}
		}
	}
	
	public static void main(String[]args){
		SlimeXSlimesCity s = new SlimeXSlimesCity();
		int[] p = {2,3,4};
		int i = s.merge(p);
		System.out.println(i);
	}

}
