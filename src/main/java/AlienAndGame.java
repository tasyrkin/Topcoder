/**
 * @author  tasyrkin
 * @since   2014/01/21
 */
public class AlienAndGame {
    public int getNumber(final String[] board) {
        int n = board.length;
        int m = board[0].length();
        int res = 1;
        char[][] b = new char[n][m];
        for (int i = 0; i < n; i++) {
            b[i] = board[i].toCharArray();
        }

        for (int len = 2; len <= Math.min(n, m); len++) {
            for (int i = 0; i < n - len + 1; i++) {

                labelj:
                for (int j = 0; j < m - len + 1; j++) {
                    for (int i1 = i; i1 < i + len; i1++) {
                        for (int j1 = j + 1; j1 < j + len; j1++) {
                            if (b[i1][j1] != b[i1][j1 - 1]) {
                                continue labelj;
                            }
                        }
                    }

                    if (len > res) {
                        res = len;
                    }
                }
            }

        }

        return res * res;
    }

    public static void main(final String[] args) {
        AlienAndGame a = new AlienAndGame();
        System.out.println(a.getNumber(new String[] {"BBBBB", "BWWWW", "BBBBB", "BBBBB", "WWWWW"}));
    }
}
