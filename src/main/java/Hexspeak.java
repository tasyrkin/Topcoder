public class Hexspeak {

    public String decode(long ciphertext){

        String val = Long.toHexString(ciphertext);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < val.length(); i++){
            if(val.charAt(i) >= '2' && val.charAt(i) >= '2'){
                return "Error!";
            }

            switch (val.charAt(i)){
                case '0':
                    sb.append('O');
                    break;
                case '1':
                    sb.append('I');
                    break;
                default:
                    sb.append(val.charAt(i));
            }
        }

        return sb.toString();

    }
}
