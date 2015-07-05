

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Prerequisites2 {

	public static final int MAX_VERTICES = 20;
	
	public static class Mapper{
		private Integer currInteger = 0;
		private Map<Integer, String> intToStr = new HashMap<Integer, String>();
		private Map<String, Integer> strToInt = new HashMap<String, Integer>();
		
		public Integer get(String str){
			Integer toReturn = strToInt.get(str);
			if(null != toReturn){
				return toReturn;
			}
			else{
				toReturn = currInteger;
				intToStr.put(toReturn, str);
				strToInt.put(str, toReturn);
				currInteger++;
			}
			return toReturn;
		}
	
		public String get(Integer integer){
			return (String)intToStr.get(integer);
		}
		
		public int getSize(){
			return currInteger;
		}
	}
	
	private static class ListNode implements Comparator{
		String data;
		ListNode next;
		ListNode prev;		
		
		public ListNode(String data, ListNode prev, ListNode next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
		public static ListNode init(String data){
			return new ListNode(data, null, null);
		}
		
		public String[] getContent(){
			ListNode iterator = this;
			int count = 0;
			while(iterator!=null){
				count++;
				iterator = iterator.next;
			}
			String[] result = new String[count];
			int cnt = 0;
			iterator = this;
			while(iterator!=null){
				result[cnt++] = iterator.data;
				iterator = iterator.next;
			}
			return result;
		}
		
		public ListNode addBefore(String data){
			ListNode ln = new ListNode(data, this.prev, this);
			this.prev = ln;
			return ln;
		}
		
		public void addAfter(String data){			
			ListNode ln = new ListNode(data, this, this.next);
			this.next = ln;
		}

		public ListNode addSorted(String data){
			ListNode iterator = this;
			ListNode prev = this;
			while(iterator != null){
				if(iterator.compare(data, iterator.data) >= 0){
					iterator.addBefore(data);
					break;
				}
				prev = iterator;
				iterator = iterator.next;
			}
			if(iterator == null){
				iterator = prev;
				iterator.addAfter(data);
			}
			while(iterator.prev != null){
				iterator = iterator.prev;
			}
			return iterator;
			
		}
				
		public ListNode remove(){
			ListNode nextln = this.next;
			ListNode prevln = this.prev;
			if(prevln!=null){
				prevln.next = nextln;
			}
			if(nextln!=null){
				nextln.prev = prevln;				
				return nextln;
			}
			return null;
		}
		
		public boolean contains(ListNode ln){
			ListNode iterator = this;
			while(iterator!=null){
				if(iterator.equals(ln)){
					return true;
				}
				iterator = iterator.next;
			}
			return false;
		}
		
		public boolean contains(String data){
			ListNode iterator = this;
			while(iterator!=null){
				if(iterator.data.equals(data)){
					return true;
				}
				iterator = iterator.next;
			}
			return false;			
		}
		
		public boolean equals(String data){
			return this.data.equals(data);
		}
		
		public boolean equals(Object o){
			if(o != null && o instanceof ListNode){
				ListNode ln = (ListNode)o;
				return this.data.equals(ln.data);
			}
			return false;
		}
		
		public void print(){
			ListNode ln = this;
			while(ln!=null){
				System.out.print(ln.data + " ");
				ln = ln.next;
			}
		}

		public int compare(String data0, String data1){
			Integer num0 = Integer.parseInt(data0.substring(data0.length()-3, data0.length()));
			Integer num1 = Integer.parseInt(data1.substring(data1.length()-3, data1.length()));
			if (!num0.equals(num1)){
				return num0.compareTo(num1);
			}
			String dep0 = data0.substring(0, data0.length()-3); 
			String dep1 = data1.substring(0, data1.length()-3);
			return dep0.compareTo(dep1);
		}	
		
		@Override
		public int compare(Object arg0, Object arg1) {
			if(arg0 instanceof ListNode && arg1 instanceof ListNode){
				return compare(((ListNode)arg0).data, ((ListNode)arg1).data);
			}
			throw new IllegalArgumentException("One of arguments is not of type ListNode");
		}
	}
	
	public void printGraph(List<ListNode> graph){
		for(int cnt = 0; cnt < graph.size(); cnt++){
			graph.get(cnt).print();
			System.out.println();
		}
	}
	
	public void exploreGraph(ListNode ln, List<ListNode> graph){
		
	}
	
	public String[] orderClasses(String[] classes){
		
		Mapper mapper = new Mapper();
		
		List<ListNode> graph = new ArrayList<ListNode>();
		
		for(int cnt = 0; cnt < classes.length; cnt++){
			String [] classesToOrder = classes[cnt].split("\\s+");
			String dependentClass = classesToOrder[0].substring(0,classesToOrder[0].indexOf(":"));
			mapper.get(dependentClass);
			ListNode ln = new ListNode(dependentClass, null, null);
			graph.add(ln);
			for(int cntClasses = 1; cntClasses < classesToOrder.length; cntClasses++){
				String prerequisite = classesToOrder[cntClasses];
				mapper.get(prerequisite);
				ln.addAfter(prerequisite);
			}
		}

		printGraph(graph);
		System.out.println();
		
		int[][] graphMatrix = new int[mapper.getSize()][mapper.getSize()];
		
		for(int cnt = 0; cnt < graph.size(); cnt++){
			ListNode head = graph.get(cnt);
			ListNode iterator = head.next;
			while(iterator!=null){
				graphMatrix[mapper.get(head.data)][mapper.get(iterator.data)] = 1;
				iterator = iterator.next;
			}
		}		
		//printGraph(graphMatrix);		
		
		ListNode orderedListHead = null;
		ListNode startNodesListHead = null;
		
		//initialization of startNodesListHead
		for(int cntJ = 0; cntJ < graphMatrix.length; cntJ++){
			boolean isOneMet = false;
			for(int cntI = 0; cntI < graphMatrix.length; cntI++){
				if(graphMatrix[cntI][cntJ] == 1){
					isOneMet = true;
				}
			}
			if(!isOneMet){				
				if(startNodesListHead == null){
					ListNode ln = new ListNode(mapper.get(cntJ), null, null);
					startNodesListHead = ln;					
				}
				else{
					startNodesListHead = startNodesListHead.addSorted(mapper.get(cntJ));
				}
			}
		}
		
		while(startNodesListHead != null){
			int intToProcess = mapper.get(startNodesListHead.data); 
			if(orderedListHead == null){
				orderedListHead = ListNode.init(startNodesListHead.data);
			}
			else{
				orderedListHead = orderedListHead.addBefore(startNodesListHead.data);					
			}
			startNodesListHead = startNodesListHead.remove();
			
			for(int cntJ = 0; cntJ < graphMatrix.length; cntJ++){
				if(graphMatrix[intToProcess][cntJ] == 1){
					graphMatrix[intToProcess][cntJ] = 0;
					
					boolean isOneMet = false;
					for(int cntI = 0; cntI < graphMatrix.length; cntI++){
						if(graphMatrix[cntI][cntJ] == 1){
							isOneMet = true;
							break;
						}
					}
					if(!isOneMet){
						if(null == startNodesListHead){
							startNodesListHead = ListNode.init(mapper.get(cntJ));
						}
						else{
							startNodesListHead = startNodesListHead.addSorted(mapper.get(cntJ));							
						}
					}
				}
			}
		}
		
		boolean ifNoOrdering = false;
		for(int cntI = 0; cntI < graphMatrix.length; cntI++){
			for(int cntJ = 0; cntJ < graphMatrix.length; cntJ++){
				if(graphMatrix[cntI][cntJ] == 1){
					ifNoOrdering = true;
					break;
				}
			}
		}
		
		String[] result = {};
		if(!ifNoOrdering){
			result = orderedListHead.getContent();
		}	
		return result;
	}
	
	 
	private void printGraph(int[][] graphMatrix) {
		int length = graphMatrix.length;
		for(int cntI = 0; cntI < length; cntI++){
			for(int cntJ = 0; cntJ < length; cntJ++){
				System.out.print(graphMatrix[cntI][cntJ]);
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//String[] classes = new String[]{"CSE121: CSE110","CSE110:","MATH122:"};
		//String[] classes = new String[]{"ENGL111: ENGL110","ENGL110: ENGL111"};
		//String[] classes = new String[]{"ENGL111: ENGL110"};
		String[] classes = new String[]{"CSE258: CSE244 CSE243 INTR100"
				,"CSE221: CSE254 INTR100"
				,"CSE254: CSE111 MATH210 INTR100"
				,"CSE244: CSE243 MATH210 INTR100"
				,"MATH210: INTR100"
				,"CSE101: INTR100"
				,"CSE111: INTR100"
				,"ECE201: CSE111 INTR100"
				,"ECE111: INTR100"
				,"CSE243: CSE254"
				,"INTR100:"};
		
		Prerequisites2 p = new Prerequisites2();
		String[] result = p.orderClasses(classes);
		
		if(result.length == 0){
			System.out.println("{}");
		}
		for(int cnt = 0; cnt < result.length; cnt++){
			System.out.print(result[cnt] + " ");
		}

	}

}
