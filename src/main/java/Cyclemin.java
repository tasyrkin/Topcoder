import java.util.*;
public class Cyclemin{
    public String bestmod(String s, int k){
        char[] arr = s.toCharArray();
        String[]ss = new String[s.length()];
        ss[0] = new String(arr);
        for(int len = 1; len<s.length();len++){
            char last = arr[0];
            for(int i = 1; i < s.length(); i++){
                arr[i-1] = arr[i];
            }
            arr[arr.length-1] = last;
            ss[len] = new String(arr);
        }
        Arrays.sort(ss);
        String[]res = new String[arr.length];
        for(int j = 0; j < arr.length; j++){
            arr = ss[j].toCharArray();
            int change = k;
            for(int i = 0; i < arr.length && change > 0; i++){
                if(arr[i] != 'a'){
                    arr[i] = 'a';
                    change--;
                }
            }
            res[j] = new String(arr);
        }
        Arrays.sort(res);
        return res[0];
    }
}