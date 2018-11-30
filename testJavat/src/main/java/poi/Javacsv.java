
package poi;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Javacsv {
    public String inPath="D:\\111in.csv";
    public String outPath="D:\\111out.csv";
    //public String outPath="D:\\";
    public  CsvWriter wr = new CsvWriter(outPath,',', Charset.forName("gb2312"));
    //public  CsvWriter wr = null;
    public ArrayList<String []> List = new ArrayList<String[]>();

    private void CheckandCreateFile(String tableName){
        //outPath = outPath + tableName + ".csv";
        File file=new File(outPath);
        try{
            if(!file.exists()){
                file.createNewFile();
                //创建CsvWriter
                //wr = new CsvWriter(outPath,',', Charset.forName("gb2312"));
                System.out.println("文件不存在，新建成功！");
            }
            else{
                System.out.println("文件存在！");
            }
        }catch( Exception e){
            e.printStackTrace();
        }
    }

    public void ReadCSV() throws IOException {
        Javacsv IO=new Javacsv();
        IO.CheckandCreateFile("");

        CsvReader reader = new CsvReader(outPath,',', Charset.forName("gb2312"));
        //新建无表头，is：false
        boolean is = reader.readHeaders();// 跳过表头   如果需要表头的话，不要写这句。
        String[] head = reader.getHeaders(); //获取表头
        System.out.println("isheads:" + is+ head);
        while(reader.readRecord()) {
            List.add(reader.getValues());
        }
        reader.close();
        for (int row = 0;row < List.size(); row++) {
            int Length=List.get(row).length;
            if(Length > 0){
                for(int i=0;i<Length;i++){
                    System.out.print(List.get(row)[i]+",");
                }//for

            }//if
            System.out.println("");
        }//for

    }//class


    /**
     * @param list
     * @param pageQueryCount 分页查询的次数，从0开始，0---第一次
     * @param totalPageQueryCount 总的分页查询次数
     * @param tableName 表名
     * @throws IOException
     */
    public void WriteCSV(ArrayList<String []> list, Integer pageQueryCount, Integer totalPageQueryCount, String tableName)	throws IOException{
        Javacsv IO=new Javacsv();
        IO.CheckandCreateFile(tableName);
        //CsvWriter wr = new CsvWriter(outPath,',', Charset.forName("gb2312"));
        if(pageQueryCount == 0){//第一次查询表（绘制csv文件）,所以要绘制表头header
            String[] header = { "Name","Province","City","Address","Tel","Website","Server_content","Jigou_cengji","Type","Parent_level1","Parent_level2","Branch_level" };
            wr.writeRecord(header);
        }
        for(int i=0;i<list.size();i++)
        {
            String[] Data= list.get(i);
            wr.writeRecord(Data);
        }
        if((pageQueryCount+1) == totalPageQueryCount){//当是最后一次查询表（绘制csv文件）时，要关闭流，才能最后生成csv文件
            wr.close();
        }
        //wr.close();
    }
    public static void main( String args[]) throws IOException{
        String tableName = "RES_VEHICLE_TRACK20180803";

        String[] contents1 = { "1","1","1","1","1","1","1","1","1","1","1","1" };
        ArrayList<String []> list1 = new ArrayList<String[]>();
        list1.add(contents1);

        String[] contents2 = { "2","2","2","2","2","2","2","2","2","2","2","2" };
        ArrayList<String []> list2= new ArrayList<String[]>();
        list2.add(contents2);
        list2.add(contents2);

        String[] contents3 = { "3","3","3","3","3","3","3","3","3","3","3","3" };
        ArrayList<String []> list3= new ArrayList<String[]>();
        list3.add(contents3);
        list3.add(contents3);
        list3.add(contents3);
        list3.add(contents3);
        list3.add(contents3);
        list3.add(contents3);
        list3.add(contents3);
        list3.add(contents3);
        list3.add(contents3);



        Javacsv id = new Javacsv();
       // id.ReadCSV();
        //模拟分页
        id.WriteCSV(list1, 0, 3, tableName);//分页时给一个参数min： min <rownum < max 当 min 为0时（第一次查询这个表）
        id.WriteCSV(list2,1,3, tableName);
        id.WriteCSV(list3,2,3, tableName);
    }

}