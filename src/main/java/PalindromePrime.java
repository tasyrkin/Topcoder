public class PalindromePrime {
    public int count(int L, int R){
        int res = 0;
        main:
        for(int i = L; i <= R; i++){
            if(i == 1){
                continue main;
            }
            char[] ch = String.valueOf(i).toCharArray();
            for(int j = 0; j < ch.length / 2; j++){
                if(ch[j] != ch[ch.length-1-j]){
                    continue main;
                }
            }
            for(int j = 2; j <= i/2; j++){
                if(i % j == 0){
                    continue main;
                }
            }
            res++;
            System.out.println(i);
        }
        return res;
    }

    public static void main(String[] args) {
        new PalindromePrime().count(100, 150);
    }
}
