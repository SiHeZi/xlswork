package com.sj.work.tool.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class ExcelUtil {

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
        return xlsBuffer.toString().substring(0,xlsBuffer.toString().length()-1);
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
     * 判断后缀为xlsx的excel文件的数据类型
     */
    private  static String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }


    /**
     * 判断后缀为xls的excel文件的数据类型
     */
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }


    public static void main(String[] args) throws Exception{
        System.out.println(ExcelUtil.readExcel("F://file/1.xlsx",7));
    }
}
