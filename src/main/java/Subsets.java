import java.util.HashMap;
import java.util.Map;

/**
 * @author  tasyrkin
 * @since   2014/05/29
 */
public class Subsets {

    private int COUNTER = 0;

    public class Bag {
        int S;
        int P;

        public Bag(final int elem) {
            S = elem;
            P = elem;
        }

        public Bag(final int iS, final int iP) {
            S = iS;
            P = iP;
        }

        public boolean isNice() {
            return S > P;
        }

        public Bag add(final int elem) {
            return new Bag(S + elem, P * elem);
        }
    }

    public class Groups {
        Map<Integer, Integer> m;

        public Groups(final int[] a) {
            m = new HashMap<Integer, Integer>();
            for (int i : a) {
                Integer cnt = m.get(i);
                if (cnt == null) {
                    cnt = 0;
                }

                m.put(i, cnt + 1);
            }
        }

        public Groups(final Map<Integer, Integer> im) {
            this.m = im;
        }

        public Integer[] sortedKeys() {
            return m.keySet().toArray(new Integer[0]);
        }

        public Groups remove(final int i) {
            int cnt = m.get(i);
            Map<Integer, Integer> newM = new HashMap<Integer, Integer>();
            for (int k : m.keySet()) {
                if (k == i) {
                    if (cnt > 1) {
                        newM.put(k, cnt - 1);
                    }
                } else {
                    newM.put(k, m.get(k));
                }
            }

            return new Groups(newM);
        }

        public Groups removeGroup(final int i) {
            Map<Integer, Integer> newM = new HashMap<Integer, Integer>();
            for (int k : m.keySet()) {
                if (k != i) {
                    newM.put(k, m.get(k));
                }
            }

            return new Groups(newM);

        }

        public int findSubset(final int[] ns) {
            Groups g = new Groups(ns);
            for (int k : g.sortedKeys()) {
                findSubset(new Bag(k), g.remove(k));
            }

            return COUNTER;
        }

        private void findSubset(final Bag bag, final Groups groups) {
            if (bag.isNice()) {
                COUNTER++;
            }

            for (int k : groups.sortedKeys()) {
                Bag newB = bag.add(k);
                if (newB.isNice()) {
                    findSubset(newB, groups.remove(k));
                }
            }

        }
    }

}
