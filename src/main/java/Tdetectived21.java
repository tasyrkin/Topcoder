import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Tdetectived21 {

    private static class Node {
        
        private final int person;
        private final int depthOfTraversal;
        private final int[] detectiveSuspicionLevels;
        private final boolean[] peopleInterviewedSoFar;

        public Node(int person, int depthOfTraversal, int[] detectiveSuspicionLevels, boolean[] peopleInterviewedSoFar) {
            this.person = person;
            this.depthOfTraversal = depthOfTraversal;
            this.detectiveSuspicionLevels = Arrays.copyOf(detectiveSuspicionLevels, detectiveSuspicionLevels.length);
            this.peopleInterviewedSoFar = Arrays.copyOf(peopleInterviewedSoFar, peopleInterviewedSoFar.length);
        }
    }

    public int reveal(String[] personJudges){
        final int N = personJudges.length;
        
        final Map<Integer, int[]> personJudgesMap = new HashMap<>();
        final Map<Integer, Integer> finalAnswer = new HashMap<>();

        final int[] ZERO_SUSPICION_LEVELS = new int[N];

        for (int istr = 0; istr < N; istr++) {
            int[] arr = new int[N];
            int i = 0;
            for (int ch : personJudges[istr].toCharArray()) {
                arr[i++] = ch - '0';
            }
            personJudgesMap.put(istr, arr);
        }
        
        finalAnswer.put(0, 0);

        for (int killer = 1; killer < N; killer++) {
            
            final Queue<Node> q = new LinkedList<>();
            
            q.offer(new Node(0, 0, ZERO_SUSPICION_LEVELS, getArrayOfFalseValues(N)));            
            
            while (!q.isEmpty()) {
                final Node currentTraversalNode = q.poll();
                
                final int personInterviewed = currentTraversalNode.person;
                final int currTraversalDepth = currentTraversalNode.depthOfTraversal;
                boolean[] peopleInterviewedSoFar = Arrays.copyOf(currentTraversalNode.peopleInterviewedSoFar, currentTraversalNode.peopleInterviewedSoFar.length);
                
                if (personInterviewed == killer) {
                    finalAnswer.put(killer, currTraversalDepth);
                   // System.out.println(String.format("Final answer for [%s] is [%s].", killer, currTraversalDepth));
                    break;
                }
                
                int[] currentPersonJudges = personJudgesMap.get(personInterviewed);
                int[] nextDetectiveSuspicionLevels = new int[N];

                for (int i = 1; i < N; i++) {
                    nextDetectiveSuspicionLevels[i] = Math.max(currentPersonJudges[i], currentTraversalNode.detectiveSuspicionLevels[i]);
                }
                
                peopleInterviewedSoFar[personInterviewed] = true;

                int[] copy = Arrays.copyOf(nextDetectiveSuspicionLevels, nextDetectiveSuspicionLevels.length);
                Arrays.sort(copy);

                final List<Integer> nextPersonsToInterview = new LinkedList<>();

                for (int j = copy.length - 1; j >= 0; j--) {    // iterates over positions in the suspicion array; in sorted DESC order
                    final int value = copy[j];
                    boolean someoneWithThatValueIsChosen = false;
                    for (int person = 1; person < N; person++) {  // iterates over people
                        if (nextDetectiveSuspicionLevels[person] == value && !peopleInterviewedSoFar[person]) {
                            nextPersonsToInterview.add(person);
                            someoneWithThatValueIsChosen = true;
                        }
                    }
                    if (someoneWithThatValueIsChosen) {
                        break;
                    }
                }

                for (Integer nextPersonToInterview : nextPersonsToInterview) {
                    q.offer(new Node(nextPersonToInterview, currTraversalDepth + 1, nextDetectiveSuspicionLevels, peopleInterviewedSoFar));
                }
            }
        }
        
        int res = 0;
        for(Integer key : finalAnswer.keySet()){
            res += key*finalAnswer.get(key);
        }
        return res;
    }

    private boolean[] getArrayOfFalseValues(final int n) {
        return new boolean[n];
    }

    public static void main(String[] args) {
        //int res = new Tdetectived2().reveal(new String[]{"0886","8086","8801","6610"});//should return 12
        //System.out.println(res);

    }
}
