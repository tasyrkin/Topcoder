import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * WRONG
 */
public class BearPlaysDiv2 {
    public String equalPiles(int A, int B, int C){

        Set<String> visited = new HashSet<>();

        int[]arr = {A, B, C};

        Arrays.sort(arr);
        visited.add(Arrays.toString(arr));
        int diff = findDiff(arr);
        while(diff > 0){

            int []brr = Arrays.copyOf(arr, 3);
            if(brr[2] != brr[0]){
                brr[2]-=brr[0];
                brr[0]*=2;
                Arrays.sort(brr);
                String sbrr = Arrays.toString(brr);
                if(!visited.contains(sbrr)) {
                    visited.add(sbrr);
                    int bdiff = findDiff(brr);
                    diff = bdiff;
                    arr = brr;
                    continue;
                }
            }

            brr = Arrays.copyOf(arr, 3);
            if(brr[0] != brr[1]){
                brr[1]-=brr[0];
                brr[0]*=2;
                Arrays.sort(brr);
                String sbrr = Arrays.toString(brr);
                if(!visited.contains(sbrr)) {
                    visited.add(sbrr);
                    int bdiff = findDiff(brr);
                    diff = bdiff;
                    arr = brr;
                    continue;
                }
            }
            brr = Arrays.copyOf(arr, 3);
            if(brr[2] != brr[1]){
                brr[2]-=brr[1];
                brr[1]*=2;
                Arrays.sort(brr);
                String sbrr = Arrays.toString(brr);
                if(!visited.contains(sbrr)) {
                    visited.add(sbrr);
                    int bdiff = findDiff(brr);
                    diff = bdiff;
                    arr = brr;
                    continue;
                }
            }
            return "impossible";
        }
        return "possible";
    }

    private int findDiff(int[] arr) {
        return arr[2] - arr[0];
    }

    public static void main(String[] args) {
        BearPlaysDiv2 b = new BearPlaysDiv2();
        System.out.println(b.equalPiles(1, 250, 500));


    }

}
