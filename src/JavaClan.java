import java.io.File;

/**
 * Created by Rex on 15/11/2.
 */
public class JavaClan {

    public static void main(String[] args){

        try {
            JavaFileParser parser = new JavaFileParser();
            parser.getJdkMethodInFile(Consts.ROOT_DIR, Consts.JDK_DIR);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
