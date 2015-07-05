import java.util.ArrayList;
import java.util.List;

/**
 * @author  tasyrkin
 * @since   2014/05/10
 */
public class CandidatesSelectionEasy {
    public int[] sort(final String[] score, final int x) {
        List<Integer> l = new ArrayList<Integer>();

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            for (int i = 0; i < score.length; i++) {
                if (score[i].charAt(x) == ch) {
                    l.add(i);
                }
            }
        }

        int[] res = new int[score.length];
        int cnt = 0;
        for (Integer i : l) {
            res[cnt++] = i;
        }

        return res;
    }
}
