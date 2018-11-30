
package poi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVTest {

    public File createFileAndColName(String filePath, String fileName, String[] colNames) {
        File csvFile = new File(filePath, fileName);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(csvFile, "GBK");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < colNames.length; i++) {
                if (i < colNames.length - 1)
                    sb.append(colNames[i] + ",");
                else
                    sb.append(colNames[i] + "\r\n");

            }
            pw.print(sb.toString());
            pw.flush();
            pw.close();
            return csvFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean appendDate(File csvFile, List<List<String>> data) {
        try {

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile, true), "GBK"), 1024);
            for (int i = 0; i < data.size(); i++) {
                List tempData = data.get(i);
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < tempData.size(); j++) {
                    if (j < tempData.size() - 1)
                        sb.append(tempData.get(j) + ",");
                    else
                        sb.append(tempData.get(j) + "\r\n");
                }
                bw.write(sb.toString());
                if (i % 1000 == 0)
                    bw.flush();
            }
            bw.flush();
            bw.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        CSVTest ct = new CSVTest();
        String[] colNames = {"第一列", "第二列", "第三列", "第四列"};

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File("d:\\test.csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();


            //File csvFile = ct.createFileAndColName("d:\\", "test.csv", colNames);
            List<String> list = new ArrayList<>();
            list.add("1啊");
            list.add("2啊");
            list.add("3啊");
            list.add("4阿");
            List<String> list2 = new ArrayList<>();
            list2.add("12");
            list2.add("22");
            list2.add("32");
            list2.add("42");
            List<List<String>> data = new ArrayList<>();
            data.add(list);
            data.add(list2);
            ct.appendDate(csvFile, data);
            ct.appendDate(csvFile, data);

            System.out.println(System.getProperty("file.encoding"));
        }catch (Exception e){

        }
    }
}
