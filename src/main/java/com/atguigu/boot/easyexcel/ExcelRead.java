package com.atguigu.boot.easyexcel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2021/8/26 17:35
 **/

public class ExcelRead {

    private static String PATH_URI = "D:\\Qyytest\\boot\\springboot\\src\\main\\resources\\";


    public static void main(String[] args) throws IOException {
        ExcelRead excelRead = new ExcelRead();

//        2003 导入 HSSFWorkbook
//        excelRead.exlxsReadHSSF03();
//        2007  导入 XSSFWorkbook
//        excelRead.exlxsReadXSSF07();
//        2003  批量导入  HSSFWorkbook
        excelRead.exlxsReadType03();
    }



    //     2003 xls 读取 HSSFWorkbook对象
    public void exlxsReadHSSF03() throws IOException {


//        获取文件流
        FileInputStream fileInputStream = new FileInputStream(PATH_URI + "物资处置申请表03.xls");
//        用流获取文件,放到工作簿
        Workbook workbook = new HSSFWorkbook(fileInputStream);
//        工作簿中获取信息   获取Sheet页
        Sheet sheet = workbook.getSheetAt(0);
//        通过Sheet页,获取行
        Row row = sheet.getRow(0);
//        通过行,获取列
        Cell cell = row.getCell(0);
//        获取列值
        String stringCellValue = cell.getStringCellValue();
//        关闭流
        fileInputStream.close();
        System.out.println("获取String类型列值  " + stringCellValue);

    }


    //     2007 读取elxs XSSFWorkbook对象
    public void exlxsReadXSSF07() throws IOException {

//        获取文件流
        FileInputStream fileInputStream = new FileInputStream(PATH_URI + "物资处置申请表07.xlsx");
//        用流获取文件,放到工作簿
        Workbook workbook = new XSSFWorkbook(fileInputStream);
//        工作簿中获取信息   获取Sheet页
        Sheet sheet = workbook.getSheetAt(0);
//        通过Sheet页,获取行
        Row row = sheet.getRow(0);
//        通过行,获取列
        Cell cell = row.getCell(0);
//        获取列值
        String stringCellValue = cell.getStringCellValue();
//        关闭流
        fileInputStream.close();
        System.out.println("获取String类型列值  " + stringCellValue);


    }


    //    2003 最大数据量65536行测试 HSSFWorkbook  数据量小65536  速度快
    public void exlxsReadType03() throws IOException {

//        获取文件流
        FileInputStream fileInputStream = new FileInputStream(PATH_URI + "物资处置申请表03.xls");
//        用流获取文件,放到工作簿
        Workbook workbook = new HSSFWorkbook(fileInputStream);
//        获取SHeet 页面
        Sheet sheet = workbook.getSheetAt(0);
//       通过Sheet页, 获取标题内容  title 第一行标题
        Row rowTitle = sheet.getRow(0);
//        获取Sheet 页,标题
        if (rowTitle != null) {
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                Cell cell = rowTitle.getCell(cellNum);
                if (null != cell) {
                    int cellType = cell.getCellType();
                    String stringCellValue = cell.getStringCellValue();
                    System.out.print(stringCellValue + " | ");
                }
            }
            System.out.println("");
        }
//        获取Sheet页,标题下对应的信息
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++){
            Row row = sheet.getRow(rowNum);
            if (null!=row){
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum<cellCount ; cellNum++){
                    Cell cell = row.getCell(cellNum);
                    double numericCellValue = cell.getNumericCellValue();
                    System.out.print(numericCellValue + " | ");
                    if ((cellCount-1)==cellNum){
                        System.out.println("");
                    }
                }
            }
        }
    }

    //    2007 最大数据量65536行测试 XSSFWorkbook  数据量大   比较耗时
    public void exlxsReadXSSFBigDate07() throws IOException {
        //开始时间
        long begin = System.currentTimeMillis();
        //创建一个簿
        Workbook workbook = new XSSFWorkbook();
        // 创建一个表
        Sheet sheet = workbook.createSheet();
        //写入数据 先创建行
        for (int rowNum = 0; rowNum < 65537; rowNum++) {
            // 创建行
            Row row = sheet.createRow(rowNum);
            // for循环,创建列
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("运行完毕");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH_URI + "物资处置申请表07.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);
    }

    //    优化 poi SXSSFWorkbook  先生成临时文件
    public void exlxsReadSXSSFBigDate07() throws IOException {
        //开始时间
        long begin = System.currentTimeMillis();
        //创建一个簿
        Workbook workbook = new SXSSFWorkbook();
        // 创建一个表
        Sheet sheet = workbook.createSheet();
        //写入数据 先创建行
        for (int rowNum = 0; rowNum < 65537; rowNum++) {
            // 创建行
            Row row = sheet.createRow(rowNum);
            // for循环,创建列
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("运行完毕");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH_URI + "物资处置申请表07.xlsx");
        workbook.write(fileOutputStream);
//        清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);
    }
}
