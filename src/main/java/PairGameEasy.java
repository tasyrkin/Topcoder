/**
 * @author  tasyrkin
 * @since   2014/05/10
 */
public class PairGameEasy {
    public String able(final int a, final int b, final int c, final int d) {
        boolean possible = false;

        if (a > c || a > d) {
            return "Not able to generate";
        }

        if (b > c || b > d) {
            return "Not able to generate";
        }

        if (a == c) {
            possible = true;
        }

        if (b == c) {
            possible = true;
        }

        main:
        for (int X = 1; X <= 1000; X++) {
            for (int Y = 1; Y <= 1000; Y++) {
                if (a * X + b * Y == c) {
                    possible = true;
                    break main;
                }
            }
        }

        if (possible) {
            boolean p1 = false;
            if (a == d) {
                p1 = true;
            }

            if (b == d) {
                p1 = true;
            }

            main:
            for (int X = 1; X <= 1000; X++) {
                for (int Y = 1; Y <= 1000; Y++) {
                    if (a * X + b * Y == d) {
                        p1 = true;
                        break main;
                    }
                }
            }

            if (p1) {
                return "Able to generate";
            }
        }

        return "Not able to generate";
    }
}
