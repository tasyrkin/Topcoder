
import java.util.*;

public class NetworkXMessageRecovery {
	public String recover(String[] messages) {
		Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
		Set<Character> initialSet = new HashSet<Character>();
		for(int i = 0; i < messages.length; i++){
			char[]chars = messages[i].toCharArray();
			for(int j = 0; j < chars.length; j++){
				initialSet.add(chars[j]);
			}
		}	
		for(int i = 0; i < messages.length; i++){
			char[]chars = messages[i].toCharArray();
			for(int j = 0; j < chars.length-1; j++){
				List<Character> list = graph.get(chars[j]);				
				if(list==null){
					list = new ArrayList<Character>();					
				}				
				list.add(chars[j+1]);
				graph.put(chars[j],list);
				initialSet.remove(chars[j+1]);
			}
		}
		List<Character> result = new ArrayList<Character>();
		while(!initialSet.isEmpty()){
			Character[]charArr = initialSet.toArray(new Character[initialSet.size()]);
			Arrays.sort(charArr);
			mylabel:
			for(Character ch : charArr){
				result.add(ch);
				initialSet.remove(ch);				
				List<Character> list = graph.get(ch);
				graph.remove(ch);
				if(list!=null){
					for(Character toTest : list){
						boolean flag = true;
						Iterator<Character> iter = graph.keySet().iterator();
						while(iter.hasNext()){
							List<Character> listToTest = graph.get(iter.next());
							if(listToTest!=null&&listToTest.contains(toTest)){
								flag = false;
								break;
							}
						}
						if(flag){
							initialSet.add(toTest);
							break mylabel;
						}
					}
				}				
			}
		}		
		StringBuffer resSb = new StringBuffer();
		for(Character ch : result){
			resSb.append(ch);
		}
		return resSb.toString();
 	}
	
	public static void main(String[]args){
		NetworkXMessageRecovery n = new NetworkXMessageRecovery();
		String[] messages = {"acd", "bce"};
		String res = n.recover(messages);
		System.out.println(res);
	}
}