
/**
 * @author  tasyrkin
 * @since   2014/01/30
 */
public class EllysCandyGame {
    public String getWinner(final int[] s) {

        String name1 = "Elly";
        String name2 = "Kris";
        String draw = "Draw";

        if (s.length == 1) {
            if (s[0] > 0) {
                return name1;
            }

            return draw;
        }

        if (s.length == 2) {
            //
        }

        boolean isFirst = true;
        int a1 = 0;
        int a2 = 0;
        int[] diff1 = new int[s.length];
        int[] diff2 = new int[s.length];
        while (true) {
            for (int i = 1; i < s.length - 1; i++) {
                int d1 = s[i - 1] * 2 - s[i];
                int d2 = s[i + 1] * 2 - s[i];
                diff1[i] = Math.max(d1, d2);
                diff2[i] = Math.min(d1, d2);
            }

            diff1[0] = s[1] * 2 - s[0];
            diff1[s.length - 1] = s[s.length - 2] * 2 - s[s.length - 1];

            int min1 = Integer.MAX_VALUE;
            int min1idx = -1;

            for (int i = 0; i < s.length; i++) {
                if (diff1[i] < min1 && s[i] != 0) {
                    min1 = diff1[i];
                    min1idx = i;
                }
            }

            int max2 = Integer.MIN_VALUE;
            int max2idx = -1;
            for (int i = 0; i < s.length; i++) {
                if (diff1[i] == min1 && max2 < diff2[i] && s[i] != 0) {
                    max2 = diff2[i];
                    max2idx = i;
                }
            }

            if (isFirst) {
                a1 += s[max2idx];
            } else {
                a2 += s[max2idx];
            }

            isFirst = !isFirst;

            if (max2idx - 1 >= 0) {
                s[max2idx - 1] *= 2;
            }

            if (max2idx + 1 < s.length) {
                s[max2idx + 1] *= 2;
            }

            s[max2idx] = 0;

            boolean isAllNull = true;
            for (int i = 0; i < s.length; i++) {
                if (s[i] > 0) {
                    isAllNull = false;
                }
            }

            if (isAllNull) {
                break;
            }
        }

        if (a1 > a2) {
            return name1;
        }

        if (a1 < a2) {
            return name2;
        }

        return draw;
    }

    public static void main(final String[] args) {
        EllysCandyGame e = new EllysCandyGame();
        e.getWinner(new int[] {20, 50, 70, 0, 30});
    }
}
