/**
 * @author  tasyrkin
 * @since   2014/04/19
 */
public class SpamChecker {

    public String spamCheck(final String judgeLog, final int good, final int bad) {
        char[] ch = judgeLog.toCharArray();
        int sc = 0;
        for (int i = 0; i < ch.length; i++) {
            if (sc < 0) {
                return "SPAM";
            }

            if (ch[i] == 'o') {
                sc += good;
            } else {
                sc -= bad;
            }
        }

        return "NOT SPAM";
    }

}
