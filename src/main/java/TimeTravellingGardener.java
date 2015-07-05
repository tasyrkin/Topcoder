

public class TimeTravellingGardener {

	public int determineUsage(int[] distance, int[] height){
		
		int minRes = Integer.MAX_VALUE;
		float precision = 0.0001f;
		
		int[] xpositions = new int[distance.length+1];
		xpositions[0] = 0;
		for(int cnt = 0; cnt < distance.length; cnt++){
			xpositions[cnt+1] = xpositions[cnt]+distance[cnt];
		}
		
		for(int cnt = 0; cnt < xpositions.length-1; cnt++){
			for(int cntNext = cnt+1; cntNext < xpositions.length; cntNext++){				
				
				for(int heightCurrent = 0; heightCurrent <= height[cnt]; heightCurrent += height[cnt]){
					for(int heightNext = 0; heightNext <= height[cntNext]; heightNext += height[cntNext]){
						boolean isTreeValid = true;						
						int numberToCut = 0;
						float k = ((float)(heightNext - heightCurrent)) / (xpositions[cntNext] - xpositions[cnt]);						
						float x0 = heightCurrent - k*xpositions[cnt];
						
						for(int treeCnt = 0; isTreeValid && treeCnt < xpositions.length; treeCnt++){
							float heightProposed = x0 + k*xpositions[treeCnt];
							if((heightProposed > height[treeCnt] && Math.abs(heightProposed - height[treeCnt]) > precision) 
									|| (heightProposed < 0 && Math.abs(heightProposed - 0) > precision)){
								isTreeValid = false;
								break;
							}
							else if(heightProposed < height[treeCnt] && Math.abs(heightProposed - height[treeCnt]) > precision){
								numberToCut++;
							}
						}
						if(isTreeValid){
							if(numberToCut < minRes){
								minRes = numberToCut;
							}
						}
					}
				}
				
			}
		}		
		return minRes;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TimeTravellingGardener ttg = new TimeTravellingGardener();
//		int[] distance = {2,2};
//		int[] height = {1,3,10};
//		int[] distance = {3,3};
//		int[] height = {3,1,3};
//		int[] distance = {1,3};
//		int[] height = {4,4,4};
//		int[] distance = {4,2};
//		int[] height = {9,8,5};
//		int[] distance = {476,465,260,484};
//		int[] height = {39,13,8,72,80};		
//		int[] distance = {173,36,668,79,26,544};
//		int[] height = {488,743,203,446,444,91,453};
//		int[] distance = {2,4,2,2,4,2,4,2,2,4};
//		int[] height = {2,2,10,10,10,16,16,22,22,28,28};
		int[] distance = {182, 78, 104, 156, 156, 104, 234, 260, 234, 156, 156, 78, 104, 104, 156, 234, 182};
		int[] height = {876, 827, 806, 778, 736, 694, 666, 603, 533, 470, 428, 386, 365, 337, 309, 267, 204, 155};		

		int res = ttg.determineUsage(distance, height);
		
		System.out.println("Trees to cut:" + res);
	}

}
