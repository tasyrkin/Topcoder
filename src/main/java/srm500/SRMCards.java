package srm500;

public class SRMCards {

	java.util.Map<Integer, Integer> cache = new java.util.HashMap<Integer, Integer>();
	
	public int maxTurns(int[] cards){
		int maxTurns = Integer.MIN_VALUE;
		
		java.util.ArrayList<Integer> cardsList = new java.util.ArrayList<Integer>();
		for(int cnt = 0; cnt < cards.length; cnt++){
			cardsList.add(cards[cnt]);
		}
		
		java.util.Iterator<Integer> iter = cardsList.iterator();
		while(iter.hasNext()){
			Integer nextElem = iter.next();
			Integer currTurns = cache.get(nextElem);
			if(currTurns == null){
				currTurns = maxTurns(cardsList, nextElem);
				cache.put(nextElem, currTurns);
			}
			if(currTurns > maxTurns){
				maxTurns = currTurns; 
			}
		}
		return maxTurns;
	}
	
	public int maxTurns(java.util.ArrayList<Integer> cards, Integer j){
		
		if(cards.isEmpty()){
			return 0;
		}
		
		int maxTurns = 0;
		
		java.util.ArrayList<Integer> currList = (java.util.ArrayList<Integer>)cards.clone();
		
		currList.remove(j);
		currList.remove(new Integer(j-1));
		currList.remove(new Integer(j+1));
		
		java.util.Iterator<Integer> iter = currList.iterator();
		while(iter.hasNext()){
			Integer nextElem = iter.next();
			Integer currTurns = cache.get(nextElem);
			if(currTurns == null){
				currTurns = maxTurns(currList, nextElem);
				cache.put(nextElem, currTurns);
			}
			if(currTurns > maxTurns){
				maxTurns = currTurns;
			}
		}		
		
		return maxTurns + 1;
	}
	
	public static void main(String[] args){
		SRMCards srmcards = new SRMCards();		
		//int[] cards = {498, 499};
		//int[]cards = {491, 492, 495, 497, 498, 499};
		//int[]cards = {100, 200, 300, 400};
		//int[]cards = {11, 12, 102, 13, 100, 101, 99, 9, 8, 1};
		//int[]cards = {118, 321, 322, 119, 120, 320};
		//int[]cards = {10, 11, 12, 13, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[]cards = {55, 52, 61, 204, 207, 54, 60, 202, 57, 58, 53, 210, 51, 59, 209, 205, 208, 201, 206, 211, 203, 56};
		int result = srmcards.maxTurns(cards);
		System.out.println(result);
	}
	
}
