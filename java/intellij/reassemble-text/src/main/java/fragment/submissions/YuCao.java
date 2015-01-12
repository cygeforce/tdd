package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;

public class YuCao {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));

            String fragmentProblem;

            while ((fragmentProblem = in.readLine()) != null) {
                System.out.println(reassemble(fragmentProblem));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String reassemble(String textFragments) {
        return new TextUtils().reassemble(textFragments);
    }
}
