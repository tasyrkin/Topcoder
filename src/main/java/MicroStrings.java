/**
 * @author  tasyrkin
 * @since   2014/03/29
 */
public class MicroStrings {
    public String makeMicroString(final int A, final int D) {
        String s = "";
        for (int i = A; i >= 0; i -= D) {
            s = i + s;
        }

        return s;
    }
}
