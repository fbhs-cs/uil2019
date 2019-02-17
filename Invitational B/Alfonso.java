public class Alfonso {
    public static void main(String[] args) {
        String str = "ACEGIKMOQSUWY";

        for (int j = 0; j < str.length(); j+=2) {
            String tempStr = str.substring(j);
            for (int i = 0; i < tempStr.length(); i++) {
                System.out.println(tempStr.substring(i));
            }
        }
    }
}