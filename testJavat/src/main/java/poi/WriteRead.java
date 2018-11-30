
package poi;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class WriteRead{

    public static void main(String[] args) throws Exception {
        String fileName = "test" + ".xlsx";//文件名称
        String filePath = "d:\\"; //文件路径

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            new FileInputStream(csvFile);
            //FileInputStream fileInputStream=new FileInputStream("d://test.xls");  //获取d://test.xls,建立数据的输入通道
            FileInputStream fileInputStream = new FileInputStream("d://test.xlsx");
            System.out.println("fileInputStream:"+fileInputStream);
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);  //使用POI提供的方法得到excel的信息
            System.out.println("excel-1的信息:" + poifsFileSystem);
            HSSFWorkbook Workbook = new HSSFWorkbook(poifsFileSystem);//得到文档对象
            System.out.println("excel-2的信息:" + Workbook);
            HSSFSheet sheet = Workbook.getSheet("sheet1");  //根据name获取sheet表
            System.out.println("excel-3的信息：" + sheet);

            sheet.getLastRowNum();

            HSSFRow row = sheet.getRow(0);  //获取第一行
            System.out.println(sheet.getLastRowNum() + " " + row.getLastCellNum());  //分别得到最后一行的行号，和一条记录的最后一个单元格

            FileOutputStream out = new FileOutputStream(csvFile);  //向d://test.xls中写数据
            row = sheet.createRow((short) (sheet.getLastRowNum() - 4)); //对总行数减4，就是倒数行数加数据
            row.createCell(0).setCellValue("11111"); //设置第一个（从0开始）单元格的数据
            row.createCell(5).setCellValue(11111);

            out.flush();
            Workbook.write(out);
            out.close();
            System.out.println(row.getPhysicalNumberOfCells() + " " + row.getLastCellNum());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
