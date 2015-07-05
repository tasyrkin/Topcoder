package srm507_div2;

import java.util.*;
public class CubeRoll{
	public int getMinimumSteps(int initPos, int goalPos, int[] holePos){
		Arrays.sort(holePos);
		int tmp = -1;
		if(initPos>goalPos){
			tmp = goalPos;
			goalPos = initPos;
			initPos = tmp;
		}
		if(holesBetween(initPos,goalPos,holePos))return -1;
		int diff = goalPos-initPos;
		int initIdx = Arrays.binarySearch(holePos,initPos);
		initIdx = -1*(initIdx+1);
		if(initIdx==0)initIdx=Integer.MIN_VALUE;
		else {
			initIdx = holePos[initIdx-1];
			initIdx++;
		}
		int goalIdx = Arrays.binarySearch(holePos,goalPos);
		goalIdx = -1*(goalIdx+1);
		if(goalIdx>=holePos.length)goalIdx=Integer.MAX_VALUE;
		else {
			goalIdx = holePos[goalIdx];
			goalIdx--;
		}
		int MAX = 9500;
		ArrayList<Integer> res1 = new ArrayList<Integer>();
		for(int i = 1; i <= MAX; i++){
			int negRes = initPos-i*i;
			if(negRes>=initIdx)res1.add(negRes);
			int posRes = initPos+i*i;
			if(posRes<=goalIdx)res1.add(posRes);
			if(negRes==goalPos||posRes==goalPos)return 1;
		}
		ArrayList<Integer> res2 = new ArrayList<Integer>();
		for(Integer num : res1){
			for(int i = 1; i <= MAX; i++){
				int negRes = num-i*i;
				if(negRes>=initIdx)res2.add(negRes);
				int posRes = num+i*i;
				if(posRes<=goalIdx)res2.add(posRes);
				if(negRes==goalPos||posRes==goalPos)return 2;
			}
		}
		res1.removeAll(res1);
		ArrayList<Integer> res3 = new ArrayList<Integer>();
		for(Integer num : res2){
			for(int i = 1; i <= MAX; i++){
				int negRes = num-i*i;
				if(negRes>=initIdx)res3.add(negRes);
				int posRes = num+i*i;
				if(posRes<=goalIdx)res3.add(posRes);
				if(negRes==goalPos||posRes==goalPos)return 3;
			}			
		}
		res2.removeAll(res2);
		for(Integer num : res3){
			for(int i = 1; i <= MAX; i++){
				int negRes = num-i*i;
				//if(negRes>=initIdx)res3.add(negRes);
				int posRes = num+i*i;
				//if(posRes<=goalIdx)res3.add(posRes);
				if(negRes==goalPos||posRes==goalPos)return 4;
			}			
		}
		return -1;
	}
	boolean isSquare(int num){
		for(int i = 1; i <=100; i++){
			if(i*i==num)return true;
		}
		return false;
	}
	boolean holesBetween(int start, int end, int[] holePos){
		int posS = 0, posE = 0;
		for(int i = 0; i < holePos.length; i++){
			if(holePos[i]>start)posS++;
			if(holePos[i]>end)posE++;			
			if(holePos[i]==start||holePos[i]==end)return true;
		}
		if(posS!=posE)return true;
		return false;
	}
	public static void main(String[]args){
		CubeRoll cr = new CubeRoll();
		int initPos = 55429;
		int goalPos = 26214;		
		int[] holePos = {10647, 5101, 17836, 13560, 7641, 10355, 7938, 26205, 10786, 9795, 22543, 5445, 735, 21561, 2673, 6071, 5327, 4293, 21359, 22334, 23379, 18990, 9359, 3282, 12103, 25026, 15271, 8463, 21298, 22409, 17472, 3017, 467, 22252, 16389, 4340, 19565, 9699, 5416, 23332, 15719, 12544, 6008, 9358, 19678, 12036, 18126, 16609, 24033, 18299};
		int cnt = cr.getMinimumSteps(initPos , goalPos, holePos);
		System.out.println(cnt);
	}
}