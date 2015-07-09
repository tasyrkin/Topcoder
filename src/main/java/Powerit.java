import java.util.*;
public class Powerit{
    public int calc(int n, int k, int m){
        boolean[]primes = new boolean[n+1];
        long[]res = new long[n+1];
        res[1] = 1;
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for(int num = 2; num <= n; num++){
            if(primes[num]){
                if((long)num*num <= n){
                    for(int i = num*num; i <= n; i += num){
                        primes[i] = false;
                        res[i] = num;
                    }
                }
                res[num] = 1;
                long pow = num;
                for(int j = 0; j < k; j++){
                    res[num] = (res[num] * pow) % m;
                    pow = (pow * pow) % m;
                }
            }
        }
        for(int i = 4; i <= n; i++){
            if(!primes[i]){
                res[i] = (res[(int)res[i]] * res[i/(int)res[i]]) % m;
            }
        }
        long result = 0;
        for(int i = 1; i <= n; i++){
            result = (result + res[i]) % m;
        }
        return (int)result;
    }
}