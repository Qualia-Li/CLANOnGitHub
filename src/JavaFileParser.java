import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFileParser {

	private HashMap<String, HashMap<String, Integer>> javaFileJDKList = new HashMap<String, HashMap<String, Integer>>();
	private HashMap<String, String> javaDirList = new HashMap<String, String>();
	private HashSet<String> jdkSet = new HashSet<String>();

	private void getJavaFileList(File fileList, String dirNumber, HashMap<String, Integer> jdkMethodCount) throws Exception {

		File[] tempList = fileList.listFiles();
		if (null != tempList){
			System.out.println("Number of items under the given directory: " + tempList.length);
		} else throw new Exception("ERROR: Nothing under the given directory.");

		for (File file : tempList) {
			if (file.isFile()) {
				if (file.getName().contains("java")) {
					HashMap<String, Integer> tempJdkList = getJDKList(file);
					for (String item : tempJdkList.keySet()){
						if (jdkMethodCount.containsKey(item)){
							jdkMethodCount.replace(item, jdkMethodCount.get(item) + tempJdkList.get(item));
						} else {
							jdkMethodCount.put(item, tempJdkList.get(item));
						}
					}
					javaFileJDKList.put(dirNumber, getJDKList(file));
				}
			} else if (file.isDirectory()) {
				getJavaFileList(file, dirNumber, jdkMethodCount);
			}
		}
		javaFileJDKList.put(dirNumber, jdkMethodCount);
	}

	private HashMap<String, Integer> getJDKList(File file) throws Exception {

		String regEx = "\\.[a-zA-Z0-9]*\\(";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		HashMap<String, Integer> jdkMethodCount = new HashMap<String, Integer>();

		String lineTxt;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			Pattern pat = Pattern.compile(regEx);
			Matcher mat = pat.matcher(lineTxt);

			while (mat.find()) {
				String temp = mat.group().substring(1, mat.group().length() - 1);
				if (jdkSet.contains(temp)){
					if (jdkMethodCount.containsKey(temp)) {
						jdkMethodCount.replace(temp, jdkMethodCount.get(temp) + 1);
					} else {
						jdkMethodCount.put(temp, 1);
					}
				}
			}
		}
		bufferedReader.close();
		return jdkMethodCount;
	}

	private void getJdkSetFromFile(String jdkFileDir) throws Exception{

		File file = new File(jdkFileDir);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		String item;
		while (null != (item = bufferedReader.readLine())){
			jdkSet.add(item);
		}
		bufferedReader.close();
	}

	private void printJdkVectorToFile() throws Exception{
		File file = new File(Consts.VEC_DIR);
		if (!file.exists()){
			if (!file.createNewFile()) throw new Exception("Vector file creating failed.");
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (String dirNumber: javaFileJDKList.keySet()){
			int totalTermAmount = 0;
			for (String jdkName: javaFileJDKList.get(dirNumber).keySet()){
				totalTermAmount += javaFileJDKList.get(dirNumber).get(jdkName);
			}
			StringBuilder infoToWrite = new StringBuilder(dirNumber);
			if (0 == totalTermAmount){
				for (String item : jdkSet) {
					infoToWrite.append(",0");
				}
			} else {
				for (String item : jdkSet) {
					if (null == javaFileJDKList.get(dirNumber).get(item)){
						infoToWrite.append(",0");
					} else {
						System.out.println(item + ": " + javaFileJDKList.get(dirNumber).get(item));
						infoToWrite.append(",");
						infoToWrite.append((double)javaFileJDKList.get(dirNumber).get(item) * 1.0 / (double)totalTermAmount);
					}
				}
			}

			writer.write(infoToWrite.toString());
			writer.newLine();
		}
		writer.close();
		System.out.println("Vector file wrote successfully.");
	}

	public void getJdkMethodInFile(String javaFileDir, String jdkFileDir) throws Exception{

		getJdkSetFromFile(jdkFileDir);

		File file = new File(javaFileDir);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		String item;
		while (null != (item = bufferedReader.readLine())){
			int commaPosition = item.indexOf(',');
			String dirNumber = item.substring(0, commaPosition);
			String dirName = item.substring(commaPosition + 1, item.length());
			javaDirList.put(dirName, dirNumber);
			HashMap<String, Integer> jdkMethodCount = new HashMap<String, Integer>();
			getJavaFileList(new File(dirName), dirNumber, jdkMethodCount);
		}

		printJdkVectorToFile();
	}
}
