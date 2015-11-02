import java.util.ArrayList;

/**
 * Created by Rex on 15/11/2.
 */
public class JavaClan {

    public static void main(String[] args){

        try {
            JavaFileParser parser = new JavaFileParser();
            parser.getJdkMethodInFile(Consts.ROOT_DIR, Consts.JDK_DIR);
            VectorComparator vectorComparator = new VectorComparator();
            ArrayList<String> answerList = vectorComparator.getNearestVectorNumber(Consts.VEC_DIR, Consts.AIM_NUM, Consts.ANS_AMT);
            System.out.println("\n===== ANSWER =====\n");
            for (String item : answerList){
                System.out.println(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
