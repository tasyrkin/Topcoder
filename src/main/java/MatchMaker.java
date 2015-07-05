

public class MatchMaker {

	public static class Member{
		String name;
		String g;
		String d;
		String[] ans;		
		public Member(String name, String g, String d, String[] ans){
			this.name = name;
			this.g = g;
			this.d = d;
			this.ans = ans;		
		}		
		public String toString(){
			StringBuffer sb = new StringBuffer();
			sb.append(name + " ");
			sb.append(g + " ");
			sb.append(d + " ");
			for(int cnt = 0; cnt < ans.length; cnt++){
				sb.append(ans[cnt] + " ");
			}
			return sb.toString();
		}
	}
	
	
	public String[] getBestMatches(String[] members, String currentUser, int sf){
		
		java.util.ArrayList<Member> membs = new java.util.ArrayList<Member>();
		Member currentMember = null;
		
		for(int cnt = 0; cnt < members.length; cnt++){
			String[] parts = members[cnt].split("\\s+");
			int pos = 0;
			String name = parts[pos++];
			String g = parts[pos++];
			String d = parts[pos++];
			String[] ans = new String[parts.length-pos];
			for(;pos < parts.length; pos++){
				ans[pos-3] = parts[pos];
			}
			Member newMember = new Member(name, g, d, ans);
			if(newMember.name.equals(currentUser)){
				currentMember = newMember; 
			}
			membs.add(newMember);
		}
		
		java.util.Iterator<Member> memberIter = membs.iterator();
		while(memberIter.hasNext()){
			System.out.println(memberIter.next().toString());
		}
		
		String [] sortedArray = new String[10]; 
		memberIter = membs.iterator();
		while(memberIter.hasNext()){
			Member memb = memberIter.next();
			if(!currentMember.name.equals(memb.name) && currentMember.d.equals(memb.g)){
				int similarity = 0;
				for(int cnt = 0; cnt < currentMember.ans.length; cnt++){
					if(currentMember.ans[cnt].equals(memb.ans[cnt])){
						similarity++;
					}
				}
				if(similarity>=sf){
					if(sortedArray[similarity] == null){
						sortedArray[similarity] = memb.name;
					}
					else{
						sortedArray[similarity] += "!" + memb.name;
					}
				}
			}
		}
		java.util.ArrayList<String> result = new java.util.ArrayList<String>();
		for(int cnt = sortedArray.length-1; cnt >= 0; cnt--){
			if(sortedArray[cnt]!=null){
				if(sortedArray[cnt].contains("!")){
					String[] parts = sortedArray[cnt].split("!");
					for(int partCnt = 0; partCnt < parts.length; partCnt++){
						result.add(parts[partCnt]);						
					}
				}
				else{
					result.add(sortedArray[cnt]);
				}					
			}
		}
				
		return result.toArray(new String [result.size()]);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MatchMaker mm = new MatchMaker();
		
		String[] members = {"BETTY F M A A C C",
				 "TOM M F A D C A",
				 "SUE F M D D D D",
				 "ELLEN F M A A C A",
				 "JOE M F A A C A",
				 "ED M F A D D A",
				 "SALLY F M C D A B",
				 "MARGE F M A A C C"};
		String [] res = mm.getBestMatches(members , "JOE", 1);
		
		if(res.length == 0){
			System.out.println("[]");
		}
		
		for(int cnt = 0; cnt < res.length; cnt++){
			System.out.print(res[cnt] + " ");			
		}
		System.out.println();
	}

}
