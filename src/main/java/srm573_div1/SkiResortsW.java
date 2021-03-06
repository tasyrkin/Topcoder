package srm573_div1;

import java.util.*;
public class SkiResortsW {
    public long minCost(String[] r, int[] a){
        long[][] arr = new long[r.length][r.length];
        Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
        for(int i = 0; i < arr.length; i++){
            Arrays.fill(arr[i], Long.MAX_VALUE);
            visited.put(i, 0);
        }
        arr[0][0] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        while(!queue.isEmpty()){
            Integer v = queue.poll();
            visited.put(v, visited.get(v)+1);
            long minToV = Long.MAX_VALUE;
            for(int i = 0; i < r.length; i++){
                if(minToV > arr[i][v])minToV = arr[i][v];
            }
            for(int i = 0; i < r.length; i++){
                if(r[v].charAt(i) == 'Y'){
                    long newValue = minToV + (a[v] >= a[i] ? 0 : (long)a[i] - (long)a[v]);
                    arr[v][i] = Math.min(arr[v][i], newValue);

                    if(visited.get(i) <= 10*r.length){
                        queue.offer(i);
                    }
                }
            }
        }
        long min = Long.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            if(min > arr[i][arr.length-1])min = arr[i][arr.length-1];
        }
        if(min == Long.MAX_VALUE)return -1;
        return min;
    }

    public static void main(String[]args){
        SkiResortsW sr = new SkiResortsW();
        String[] road = {
                "NYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "YNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNYN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNY",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYN"};

        int[]altitude = {0, 0, 1000000000, 0, 0, 1000000000, 0, 1000000000, 0, 1000000000, 1000000000, 0, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1000000000, 0, 0, 1000000000, 1000000000, 1000000000, 0, 1000000000, 0, 1000000000, 0, 0, 1000000000, 0, 1000000000, 0, 0, 0, 0, 0, 0, 0, 1000000000, 0};
        long res = sr.minCost(road, altitude);
        System.out.println(res);
    }
}