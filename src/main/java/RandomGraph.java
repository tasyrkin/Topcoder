import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author  tasyrkin
 * @since   2014/05/10
 */
public class RandomGraph {

    private static class Holder {
        boolean connected;
        long prob;

        public Holder(final boolean c, final long p) {
            connected = c;
            prob = p;
        }
    }

    public double probability(final int n, final int p) {
        if (n < 4 || p == 0) {
            return 0;
        }

        double prob = ((double) p) / 1000;

        long res = 0;
        int cn = 0;
        for (int cnt = 0; cnt < 64; cnt++) {

            Holder h = connected(cnt, p);
            if (h.connected) {
                res += h.prob;
                cn++;
            }
        }

        System.out.println(cn);

        return ((double) res) / 1000000000000000000L;
    }

    private Holder connected(final int bits, final int connProb) {
        Map<Integer, List<Integer>> m = new HashMap<Integer, List<Integer>>();
        long prob = 0;
        if ((bits & 1) == 1) {
            prob = connProb;
            addPair(m, 0, 1);
            addPair(m, 1, 0);
        } else {
            prob = (1000 - connProb);
        }

        if (((bits >> 1) & 1) == 1) {
            addPair(m, 0, 2);
            addPair(m, 2, 0);
            prob *= connProb;
        } else {
            prob *= (1000 - connProb);
        }

        if (((bits >> 2) & 1) == 1) {
            addPair(m, 0, 3);
            addPair(m, 3, 0);
            prob *= connProb;
        } else {
            prob *= (1000 - connProb);
        }

        if (((bits >> 3) & 1) == 1) {
            addPair(m, 1, 2);
            addPair(m, 2, 1);
            prob *= connProb;
        } else {
            prob *= (1000 - connProb);
        }

        if (((bits >> 4) & 1) == 1) {
            addPair(m, 1, 3);
            addPair(m, 3, 1);
            prob *= connProb;
        } else {
            prob *= (1000 - connProb);
        }

        if (((bits >> 5) & 1) == 1) {
            addPair(m, 2, 3);
            addPair(m, 3, 2);
            prob *= connProb;
        } else {
            prob *= (1000 - connProb);
        }

        boolean connected = false;

        /*for (Map.Entry<Integer, List<Integer>> e : m.entrySet()) {
         *  if (e.getValue() != null) {
         *      if (e.getValue().size() == 3) {
         *          connected = true;
         *          break;
         *      }
         *  }
         *}*/
        Set<Integer> s = new HashSet<Integer>();
        List<Integer> l0 = m.get(0);
        if (l0 != null) {
            s.add(0);
            for (Integer v : l0) {
                s.add(v);

                List<Integer> lv = m.get(v);
                if (lv != null) {
                    s.addAll(lv);
                    for (Integer v1 : lv) {
                        s.add(v1);

                        List<Integer> lv1 = m.get(v1);
                        if (lv1 != null) {
                            s.addAll(lv1);
                        }
                    }
                }
            }
        }

        return new Holder(s.size() == 4, prob);
    }

    private void addPair(final Map<Integer, List<Integer>> m, final int i, final int j) {
        List<Integer> l = m.get(i);
        if (l == null) {
            l = new ArrayList<Integer>();
        }

        l.add(j);
        m.put(i, l);
    }

    public static void main(final String[] args) {
        RandomGraph r = new RandomGraph();
        System.out.println(r.probability(4, 500));
    }
}
