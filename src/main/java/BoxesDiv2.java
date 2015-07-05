import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tasyrkin
 * @since   2014/05/29
 */
public class BoxesDiv2 {
    public int findSize(final int[] cc) {

        Integer[] ncc = new Integer[cc.length];
        for (int i = 0; i < cc.length; i++) {
            ncc[i] = findPow2(cc[i]);
        }

        while (ncc.length != 1) {
            Arrays.sort(ncc);

            // System.out.println(Arrays.toString(ncc));

            List<Integer> l = new ArrayList<Integer>();
            if (ncc.length % 2 == 0) {
                for (int i = 0; i < ncc.length / 2; i++) {
                    int a = ncc[i] + ncc[ncc.length - i - 1];
                    int j = findPow2(a);

                    l.add(j);
                }
            } else {
                for (int i = 0; i < ncc.length / 2; i++) {
                    int a = ncc[i] + ncc[ncc.length - i - 2];
                    int j = findPow2(a);

                    l.add(j);
                }

                l.add(ncc[ncc.length - 1]);

            }

            ncc = l.toArray(new Integer[0]);
        }

        return ncc[0];
    }

    private int findPow2(final int a) {
        int j = 1;
        while (j < a) {
            j = j * 2;
        }

        return j;
    }

    public static void main(final String[] args) {
        BoxesDiv2 b = new BoxesDiv2();
        int s = b.findSize(new int[] {1, 1, 13, 1, 1});
        System.out.println(s);
    }
}
