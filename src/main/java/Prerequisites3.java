

public class Prerequisites3 {

	public static class Mapper{
		private Integer currInteger = 0;
		private java.util.Map<Integer, String> intToStr = new java.util.HashMap<Integer, String>();
		private java.util.Map<String, Integer> strToInt = new java.util.HashMap<String, Integer>();
		
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
	
	public String[] orderClasses(String[] classes){
		
		Mapper mapper = new Mapper();		
		java.util.List<java.util.List<String>> graph = new java.util.ArrayList<java.util.List<String>>();
		
		for(int cnt = 0; cnt < classes.length; cnt++){
			String [] classesToOrder = classes[cnt].split("\\s+");
			String dependentClass = classesToOrder[0].substring(0,classesToOrder[0].indexOf(":"));
			mapper.get(dependentClass);
			java.util.List<String> list = new java.util.ArrayList<String>();
			graph.add(list);
			list.add(dependentClass);
			for(int cntClasses = 1; cntClasses < classesToOrder.length; cntClasses++){
				String prerequisite = classesToOrder[cntClasses];
				mapper.get(prerequisite);
				list.add(prerequisite);
			}
		}

		printGraph(graph);
		
		int[][] graphMatrix = new int[graph.size()][graph.size()];
		if(graph.size() < mapper.getSize()){
			return new String[]{};
		}
		
		java.util.Iterator<java.util.List<String>> edgesIter = graph.iterator();
		while(edgesIter.hasNext()){
			java.util.Iterator<String> strIter = edgesIter.next().iterator();
			String startEdge = null;
			if(strIter.hasNext()){
				startEdge = strIter.next();
			}
			while(strIter.hasNext()){
				graphMatrix[mapper.get(startEdge)][mapper.get(strIter.next())] = 1;
			}
		}
		printGraph(graphMatrix);
		graphMatrix = transponeGraphMatrix(graphMatrix);
		printGraph(graphMatrix);
		
		java.util.List<String> orderedListHead = new java.util.ArrayList<String>();
		java.util.List<String> startNodesListHead = new java.util.ArrayList<String>();
		
		//initialization of startNodesListHead
		for(int cntJ = 0; cntJ < graphMatrix.length; cntJ++){
			boolean isOneMet = false;
			for(int cntI = 0; cntI < graphMatrix.length; cntI++){
				if(graphMatrix[cntI][cntJ] == 1){
					isOneMet = true;
				}
			}
			if(!isOneMet){
				addSorted(startNodesListHead, mapper.get(cntJ));
			}
		}
		
		while(startNodesListHead.size() != 0){
			int intToProcess = mapper.get(startNodesListHead.get(0));
			orderedListHead.add(startNodesListHead.get(0));
			startNodesListHead.remove(0);
			
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
						addSorted(startNodesListHead, mapper.get(cntJ));
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
			result = orderedListHead.toArray(new String[orderedListHead.size()]);
		}	
		return result;
	}

	private int[][] transponeGraphMatrix(int[][] graphMatrix) {
		int length = graphMatrix.length;
		int [][] result = new int[length][length];
		
		for(int cntI = 0; cntI < length; cntI++){
			for(int cntJ = 0; cntJ < length; cntJ++){
				result[cntJ][cntI] = graphMatrix[cntI][cntJ];
			}
		}		
		return result;
	}

	private void addSorted(java.util.List<String> startNodesListHead, String inputStr) {
		java.util.ListIterator<String> listIter = startNodesListHead.listIterator();
		boolean isAdded = false;
		while(listIter.hasNext()){
			Integer index = listIter.nextIndex();
			String str = listIter.next();
			if(compare(inputStr, str)<=0){
				startNodesListHead.add(index, inputStr);
				isAdded = true;
				break;
			}
		}
		if(!isAdded){
			startNodesListHead.add(inputStr);
		}
	}
	
	
	private void printGraph(int[][] graphMatrix) {
		for(int cntI = 0; cntI < graphMatrix.length; cntI++){
			for(int cntJ = 0; cntJ < graphMatrix.length; cntJ++){
				System.out.print(graphMatrix[cntI][cntJ]);
			}
			System.out.println();
		}
	}


	private void printGraph(java.util.List<java.util.List<String>> graph) {
		java.util.Iterator<java.util.List<String>> iterator = graph.iterator();
		while(iterator.hasNext()){
			java.util.List<String> currElem = iterator.next();
			java.util.Iterator<String>iterStr = currElem.iterator();
			while(iterStr.hasNext()){
				String toPrint = iterStr.next();
				System.out.print(toPrint + " ");
			}
			System.out.println();
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] classes = new String[]{
				"CSE258: CSE244 CSE243 INTR100"
				,"CSE221: CSE254 INTR100"
				,"CSE254: CSE111 MATH210 INTR100"
				,"CSE244: CSE243 MATH210 INTR100"
				,"MATH210: INTR100"
				,"CSE101: INTR100"
				,"CSE111: INTR100"
				,"ECE201: CSE111 INTR100"
				,"ECE111: INTR100"
				,"CSE243: CSE254"
				,"INTR100:"
				};
//		String[] classes = new String[]{"CSE110: CSE121","CSE121:","MATH121:"};
//		String[] classes = new String[]{"ENGL111: ENGL110"};
//		String[] classes = new String[]{
//			"ENGL111: ENGL110",
//			"ENGL110: ENGL111"};
		
		Prerequisites3 p = new Prerequisites3();
		String[] result = p.orderClasses(classes);
		
		if(result.length == 0){
			System.out.println("{}");
		}
		for(int cnt = 0; cnt < result.length; cnt++){
			System.out.print(result[cnt] + " ");
		}
	}
}
