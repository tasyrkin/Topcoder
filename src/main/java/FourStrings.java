public class FourStrings {

    private static int res = Integer.MAX_VALUE;

    public int shortestLength(String a, String b, String c, String d){
        String[]arr = {a,b,c,d};
        solve(a, arr, 1);
        return res;
    }

    private void solve(String combined, String[] arr, int idx) {
        if(idx >= arr.length){
            if(res > combined.length()){
                res = combined.length();
            }
            return;
        }
        if(combined.contains(arr[idx])){
            solve(combined, arr, idx+1);
        } else{
            proceed(combined, arr[idx], arr, idx);
            proceed(arr[idx], combined, arr, idx);
        }
    }

    private void proceed(String combined, String otherStr, String[] arr, int idx) {
        main:
        for(int start = 1; start < otherStr.length(); start++){
            for(int curr = 0; start+curr < otherStr.length(); curr++){
                if(curr >= combined.length()){
                    continue main;
                }
                if(otherStr.charAt(start+curr) != combined.charAt(curr)){
                    continue main;
                }
            }
            String newCombined = otherStr.substring(0, start) + combined;
            solve(newCombined, arr, idx+1);
            return;
        }
        solve(combined+otherStr, arr, idx+1);
    }

    public static void main(String[] args) {
        //int res = new FourStrings().shortestLength("abc", "ab", "bc", "b");
        //int res = new FourStrings().shortestLength("a", "bc", "def", "ghij");
        //int res = new FourStrings().shortestLength("top", "bc", "def", "ghij");
        int res = new FourStrings().shortestLength("ab", "de", "bcd", "y");
        System.out.println(res);
    }
}
