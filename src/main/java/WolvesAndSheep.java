/**
 * @author  tasyrkin
 * @since   2014/04/19
 */
public class WolvesAndSheep {

    public int getmin(final String[] f) {

        int n = f.length;
        int m = f[0].length();
        char[][] ch = new char[n][m];
        for (int i = 0; i < n; i++) {
            ch[i] = f[i].toCharArray();
        }

        int res = 0;

        {

            int i1 = 0;
            int i2 = 1;
            while (i1 < n && i2 < n) {
                boolean fenceNecessary = false;
                for (int j = 0; j < m; j++) {
                    if (ch[i1][j] != '.' && ch[i2][j] != '.') {
                        if (ch[i1][j] != ch[i2][j]) {
                            fenceNecessary = true;
                            break;
                        }
                    }
                }

                if (fenceNecessary) {
                    res++;
                    i1 = i2;
                    i2 = i1 + 1;
                } else {
                    i2++;
                }
            }
        }

        int j1 = 0;
        int j2 = 1;

        while (j1 < m && j2 < m) {
            boolean fenceNecessary = false;
            for (int i = 0; i < n; i++) {
                if (ch[i][j1] != '.' && ch[i][j2] != '.') {
                    if (ch[i][j1] != ch[i][j2]) {
                        fenceNecessary = true;
                        break;
                    }
                }
            }

            if (fenceNecessary) {
                res++;
                j1 = j2;
                j2 = j1 + 1;
            } else {
                j2++;
            }
        }

        return res;

    }
}
