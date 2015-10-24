import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Tdetectived2 {

    private static class Node{
        int person;
        int[] currentDetectiveRanking;

        public Node(int person, int[] currentDetectiveRanking) {
            this.person = person;
            this.currentDetectiveRanking = Arrays.copyOf(currentDetectiveRanking, currentDetectiveRanking.length);
        }
    }

    public int reveal(String[] personJudges){
        int n = personJudges.length;
        final Map<Integer, int[]> personJudgesMap = new HashMap<>();
        Map<Integer, Integer> answer = new HashMap<>();

        final int[] ZERO_ARRAY = new int[n];

        for(int istr = 0; istr < n; istr++) {
            int[] arr = new int[n];
            int i = 0;
            for (int ch : personJudges[istr].toCharArray()) {
                arr[i++] = ch;
            }
            personJudgesMap.put(istr, arr);
        }
        answer.put(0, 0);

        final Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, ZERO_ARRAY));
        while(!q.isEmpty()){
            Node nodeToInterview = q.poll();
            int[] currentPersonJudges = personJudgesMap.get(nodeToInterview.person);
            int currAnswer = answer.get(nodeToInterview.person);
            int[] nextDetectiveSuspicionRanking = new int[n];

            for (int i = 0; i < n; i++) {
                if(nodeToInterview.person != i) {
                    nextDetectiveSuspicionRanking[i] = Math.max(currentPersonJudges[i], nodeToInterview.currentDetectiveRanking[i]);
                }
            }

            int[] copy = Arrays.copyOf(nextDetectiveSuspicionRanking, nextDetectiveSuspicionRanking.length);
            Arrays.sort(copy);

            final List<Integer> nextPersonsToInterview = new LinkedList<>();

            for(int j = copy.length-1; j >= 0; j--) {
                boolean taken = false;
                for (int idx2 = 0; idx2 < n; idx2++) {
                    if (copy[j] == nextDetectiveSuspicionRanking[idx2] && answer.get(idx2) == null) {
                        nextPersonsToInterview.add(idx2);
                        taken = true;
                    }
                }
                if(taken) break;
            }

            for(Integer nextPersonToInterview : nextPersonsToInterview){
                if(!q.contains(nextPersonToInterview)) {
                    answer.put(nextPersonToInterview, currAnswer + 1);
                    q.offer(new Node(nextPersonToInterview, nextDetectiveSuspicionRanking));
                }
            }


        }
        long res = 0;
        for(Integer key : answer.keySet()){
            res += key*answer.get(key);
        }
        return (int)res;
    }

    public static void main(String[] args) {
        int res = new Tdetectived2().reveal(new String[]{"0886","8086","8801","6610"});//should return 12
        System.out.println(res);

    }
}
