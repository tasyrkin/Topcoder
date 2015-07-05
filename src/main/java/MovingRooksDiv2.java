import java.util.HashSet;
import java.util.Set;

public class MovingRooksDiv2 {
    static int[] Y1 = null;
    static int[] Y2 = null;
    static boolean res = false;

    public String move(final int[] y1, final int[] y2) {
        Y2 = y2;
        Y1 = y1;

        boolean isOk = true;
        for (int j = 0; j < Y1.length; j++) {
            if (Y1[j] != Y2[j]) {
                isOk = false;
            }
        }

        if (isOk) {
            return "Possible";
        }

        int n = Y1.length;
        Set<Integer> vs = new HashSet<Integer>();

        int[] C = new int[n];
        check(0, vs, C, false);
        if (res) {
            return "Possible";
        }

        return "Impossible";
    }

    private void check(final int i, final Set<Integer> vs, final int[] C, final boolean change) {
        if (vs.size() == C.length) {
            for (int j = 0; j < C.length; j++) {
                if (Y1[j] != C[j]) {
                    return;
                }
            }

            res = true;
            return;
        }

        for (int k = change ? i + 1 : 0; k < C.length; k++) {
            if (!vs.contains(k)) {
                if (change) {
                    vs.add(k);
                    if (i < k && Y1[i] > Y1[k]) {
                        C[i] = Y1[k];
                        C[k] = Y1[i];
                    } else {
                        C[i] = Y1[i];
                        C[k] = Y1[k];
                    }

                    check(0, vs, C, !change);
                    vs.remove(k);
                } else {
                    if (vs.size() == C.length - 1) {
                        vs.add(k);
                        C[k] = Y1[k];
                        check(k, vs, C, !change);
                        vs.remove(k);
                    } else {
                        vs.add(k);
                        check(k, vs, C, !change);
                        vs.remove(k);
                    }
                }
            }
        }
    }

    public static void main(final String[] args) {
        MovingRooksDiv2 m = new MovingRooksDiv2();
        System.out.println(m.move(new int[] {3, 1, 2, 0}, new int[] {0, 2, 1, 3}));
    }
}
