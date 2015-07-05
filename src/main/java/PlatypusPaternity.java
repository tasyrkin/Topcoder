public class PlatypusPaternity{
	public int maxFamily(String[] femaleCompatibility, String[] maleCompatibility, String[] siblingGroups){
		int max = 0;
		for(int i = 0; i < siblingGroups.length; i++){
			char[]childs = siblingGroups[i].toCharArray();
			int chn = 0;
			for(int j = 0; j < childs.length; j++){
				boolean fath = false;
				boolean moth = false;			
				if(childs[j] == 'Y'){
					for(int k = 0; k < femaleCompatibility.length; k++){
						if(/*i < femaleCompatibility[j].length() && */femaleCompatibility[k].charAt(j) == 'Y')moth = true;
						else moth = false;						
						if(/*i < maleCompatibility[j].length() && */maleCompatibility[k].charAt(j) == 'Y')fath = true;
						else fath = false;
						if(moth&&fath)break;
					}
					if(fath&&moth)chn++;
				}
			}
			if(max<chn)max=chn;
		}
		if(max == 0)return 0;
		return max+2;
	}
	public static void main(String[] args) {
		PlatypusPaternity pp = new PlatypusPaternity();
		String[] femaleCompatibility = {"YYYYYYYYN"}; 
		String[] maleCompatibility = {"YYYYYYYYY"};
		String[] siblingGroups = {"YNYNYNYNY",
		 "NNNYNYNNN",
		 "NYNNNNNYN"};
		int i = pp.maxFamily(femaleCompatibility, maleCompatibility, siblingGroups);
		System.out.println(i);
	}

}
