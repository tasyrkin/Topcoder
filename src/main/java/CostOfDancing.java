import java.util.Arrays;

/**
 * @author: tasyrkin
 * @since: 2014/06/12
 */
public class CostOfDancing {
    
    public int minimum(int K, int[] danceCost){
        Arrays.sort(danceCost);
        int cnt = 0;
        for(int i = 0; i < K && i < danceCost.length; i++){
            cnt += danceCost[i] ;
        }
        return cnt;
    }
}
