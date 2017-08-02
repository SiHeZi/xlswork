package com.sj.work.tool.util;

import com.sj.work.tool.common.JobDay;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";


    /**
     * 读取excel中的数据
     */
    public static String readExcel(String path,int cellNum) {
        if (path != null && !path.equals("")) {
            String ext = getExt(path);
            if (ext!=null && !ext.equals("")) {
                if (ext.equals("xls")) {
                    return readXls(path,cellNum);
                } else if (ext.equals("xlsx")) {
                    return readXlsx(path,cellNum);
                }
            }
        }
        return null;
    }


    /**
     * 读取后缀为xls的excel文件的数据
     */
    private static String readXls(String path,int cellNum) {

        HSSFWorkbook hssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer xlsBuffer=new StringBuffer();

        if (hssfWorkbook != null) {

            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {

                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);

                if (hssfSheet == null) {
                    continue;
                }

                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);

                    if (hssfRow != null) {

                        for (int i = 0; i <cellNum ; i++) {
                            HSSFCell value = hssfRow.getCell(i);
                            xlsBuffer.append(i).append(":").append(value);
                        }


                    }
                }
            }
        }
        return xlsBuffer.toString();
    }




    /**
     * 读取后缀为xlsx的excel文件的数据
     */
    private static String readXlsx(String path,int cellNum) {

        XSSFWorkbook xssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer xlsBuffer=new StringBuffer();

        if(xssfWorkbook!=null){
            // Read the Sheet
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }

                // Read the Row
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {

                        for (int i = 0; i <cellNum ; i++) {
                            XSSFCell value = xssfRow.getCell(i);
                            xlsBuffer.append(value).append("^");
                        }
                        xlsBuffer.append("$");
                    }
                }
            }
        }
        return xlsBuffer.toString().substring(0, xlsBuffer.toString().length() - 1);
    }

    /**
     * 获取文件扩展名
     */
    private static String getExt(String path) {
        if (path == null || path.equals("") || !path.contains(".")) {
            return null;
        } else {
            return path.substring(path.lastIndexOf(".") + 1, path.length());
        }
    }

    /**
     * 判断Excel的版本,获取Workbook
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    //输出
    public static void writeExcel(List<JobDay> dataList, int cloumnCount,String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();  // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);

                /**
                 * 此处可优化为通用接口
                 */
                JobDay job = dataList.get(j);

                String no = job.getNo();
                String name = job.getName();
                String day = job.getDay();
                String start=job.getStartTime();
                String end=job.getEndTime();
                String flag=job.getFlag();

                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环
                    Cell first = row.createCell(0);
                    first.setCellValue(no);

                    Cell second = row.createCell(1);
                    second.setCellValue(name);

                    Cell third = row.createCell(2);
                    third.setCellValue(day);

                    Cell four = row.createCell(3);
                    four.setCellValue(start);

                    Cell five = row.createCell(4);
                    five.setCellValue(end);

                    Cell six = row.createCell(5);
                    six.setCellValue(flag);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 判断后缀为xlsx的excel文件的数据类型
     * */
//    private  static String getValue(XSSFCell xssfRow) {
//        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
//            return String.valueOf(xssfRow.getBooleanCellValue());
//        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
//            return String.valueOf(xssfRow.getNumericCellValue());
//        } else {
//            return String.valueOf(xssfRow.getStringCellValue());
//        }
//    }
//
//
//    /**
//     * 判断后缀为xls的excel文件的数据类型
//     */
//    private String getValue(HSSFCell hssfCell) {
//        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
//            return String.valueOf(hssfCell.getBooleanCellValue());
//        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
//            return String.valueOf(hssfCell.getNumericCellValue());
//        } else {
//            return String.valueOf(hssfCell.getStringCellValue());
//        }
//    }
    public static void main(String[] args) throws Exception{
       // System.out.println(ExcelUtil.readExcel("F://file/1.xlsx",7));
    }
}
