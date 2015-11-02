import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.io.*;

/**
 * Created by Rex on 15/11/2.
 */
public class VectorComparator {

    private HashMap<String, ArrayList<Double>> vectorList = new HashMap<String, ArrayList<Double>>();
    private ArrayList<VectorValueItem> compareResultList = new ArrayList<VectorValueItem>();

    Comparator vectorComp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            VectorValueItem vec0 = (VectorValueItem)o1;
            VectorValueItem vec1 = (VectorValueItem)o2;
            if ((vec1.vectorValue - vec0.vectorValue) > 0.0000000001) return 1;
            else if ((vec0.vectorValue - vec1.vectorValue) > 0.0000000001) return -1;
            else return 0;
        }
    };

    private void getVectorFromFile(String vectorDir) throws Exception{

        File file = new File(vectorDir);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String item;
        while (null != (item = bufferedReader.readLine())){
            if (item.length() == 0) break;
            String[] items = item.split(",");
            String vectorNumber = items[0];
            ArrayList<Double> vectorItem = new ArrayList<Double>();
            for (int i = 1; i < items.length; i++){
                vectorItem.add(Double.parseDouble(items[i]));
            }
            vectorList.put(vectorNumber, vectorItem);
        }
    }

    private void calculateComparedVectorValue(String aimVectorNumber){
        ArrayList<Double> aimVector = vectorList.get(aimVectorNumber);
        for (String item : vectorList.keySet()){
            if (item.equals(aimVectorNumber)) continue;
            double comparedValue = 0;
            for (int i = 0; i < aimVector.size(); i++) {
                comparedValue += aimVector.get(i) * vectorList.get(item).get(i);
            }
            compareResultList.add(new VectorValueItem(item, comparedValue));
        }
    }


    public ArrayList<String> getNearestVectorNumber(String vectorDir, String aimVectorNumber, int answerAmount) throws Exception{
        ArrayList<String> topRatedVectorNumbers = new ArrayList<String>();
        getVectorFromFile(vectorDir);
        calculateComparedVectorValue(aimVectorNumber);
        Collections.sort(compareResultList, vectorComp);
        for (int i = 0; i < answerAmount; i++){
            topRatedVectorNumbers.add(compareResultList.get(i).vectorNumber);
        }
        return topRatedVectorNumbers;
    }
}
