/**
 * @author  tasyrkin
 * @since   2014/04/26
 */
public class FizzBuzzTurbo {
    public long[] counts(final long A, final long B) {
        long fizzBuzzA = (A - 1) / 15;
        long fizzA = (A - 1) / 3 - fizzBuzzA;
        long buzzA = (A - 1) / 5 - fizzBuzzA;
        long fizzBuzzB = (B) / 15;
        long fizzB = (B) / 3 - fizzBuzzB;
        long buzzB = (B) / 5 - fizzBuzzB;

        return new long[] {fizzB - fizzA, buzzB - buzzA, fizzBuzzB - fizzBuzzA};

    }
}
