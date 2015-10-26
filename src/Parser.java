
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	static List<File> godfile = new ArrayList<File>();
	static HashMap<File, HashMap> filejdk = new HashMap<File, HashMap>();

	public Parser() {
		// TODO Auto-generated constructor stub
	}

	public static void search(File tempList2) throws IOException {

		// god god = new god();
		File[] tempList = tempList2.listFiles();
		System.out.println("该目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				// System.out.println("文     件："+tempList[i]);
				if (tempList[i].getName().indexOf("java") > -1) {
					godfile.add(tempList[i]);
					// searchjdk(tempList[i]);
					filejdk.put(tempList[i], searchjdk(tempList[i]));
				}
			}
			if (tempList[i].isDirectory()) {
				System.out.println("文件夹：" + tempList[i]);
				search(tempList[i]);
			}
		}
	}

	public static HashMap<String, Integer> searchjdk(File file)
			throws IOException {
		String regEx = "\\.[a-zA-Z0-9]*\\(";
		HashMap<String, Integer> jdk = new HashMap<String, Integer>();
		// System.out.println(regEx);[.][a-zA-Z0-9][()]{2}
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			Pattern pat = Pattern.compile(regEx);
			Matcher mat = pat.matcher(lineTxt);
			// boolean rs = mat.find();
			// System.out.println(rs);
			while (mat.find()) {
				// System.out.println("~~~~~~~"+mat.group());
				String temp = mat.group();
				if (jdk.containsKey(temp)) {
					Integer a = (Integer) jdk.get(temp) + 1;
					jdk.replace(temp, a);

				} else {
					jdk.put(temp, 1);
				}

			}
			// System.out.println(jdk);

		}
		read.close();
		return jdk;
	}

	public static void main(String[] args) throws IOException {
		// god god = new god();
		String path = "H:/godtest";//TODO
		File file = new File(path);
		File[] tempList = file.listFiles();
		System.out.println("该目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				// System.out.println("文     件："+tempList[i]);
				if (tempList[i].getName().indexOf("java") > -1) {
					godfile.add(tempList[i]);
					filejdk.put(tempList[i], searchjdk(tempList[i]));
				}
			}
			if (tempList[i].isDirectory()) {
				System.out.println("文件夹：" + tempList[i]);
				search(tempList[i]);
			}
		}
		System.out.println(godfile);
		System.out.println(filejdk);

	}

}
