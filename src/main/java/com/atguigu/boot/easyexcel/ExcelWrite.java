package com.atguigu.boot.easyexcel;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2021/8/26 17:35
 **/

public class ExcelWrite {

    private static String PATH_URI = "D:\\Qyytest\\boot\\springboot\\src\\main\\resources\\";


    public static void main(String[] args) throws IOException {
        ExcelWrite excelWrite = new ExcelWrite();
//   -------     生成exlxs
//      2007  XSSFWorkbook对象  大于65536 ,无上限
//        createExcel.exlxsWriteXSSF07();
//      xls 2003 HSSFWorkbook对象
//        createExcel.exlxsWriteHSSF03();

//        2003 最大数据量65536测试
        excelWrite.exlxsWriteXSSFBigDate03();
//        createExcel.exlxsWriteXSSFBigDate07();
//        excelWrite.exlxsWriteSXSSFBigDate07();
    }


    //     2003 xls 生成 HSSFWorkbook对象
    public void exlxsWriteHSSF03() throws IOException {
        String[] titlie = {"id", "name", "sex"};
        // 2,创建Excel 工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //  3,创建一个 Sheet 页面
        HSSFSheet sheet = workbook.createSheet();
        // 4,创建一行
        HSSFRow row = sheet.createRow(0);
        // 5 , 给第一行,加title
        for (int i = 0; i < titlie.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titlie[i]);
        }
        // 6 创建第二行
        HSSFRow row2 = sheet.createRow(1);
        // 添加第二行数据
        HSSFCell cell1 = row2.createCell(0);
        HSSFCell cell2 = row2.createCell(1);
        HSSFCell cell3 = row2.createCell(2);
        cell1.setCellValue("秦洋洋");
        cell2.setCellValue("男");
        cell3.setCellValue("18");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH_URI + "qyy.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }


    //     2007 生成elxs XSSFWorkbook对象
    public void exlxsWriteXSSF07() throws IOException {
        String[] titlie = {"id", "name", "sex"};
        // 2,创建Excel 工作簿
        Workbook xssfSheets = new XSSFWorkbook();
        //  3,创建一个 Sheet 页面
        Sheet sheet = xssfSheets.createSheet();
        // 4,创建一行
        Row row = sheet.createRow(0);
        // 5 , 给第一行,加title
        for (int i = 0; i < titlie.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titlie[i]);
        }
        // 6 创建第二行
        Row row2 = sheet.createRow(1);
        // 添加第二行数据
        Cell cell1 = row2.createCell(0);
        Cell cell2 = row2.createCell(1);
        Cell cell3 = row2.createCell(2);
        cell1.setCellValue("秦洋洋");
        cell2.setCellValue("男");
        cell3.setCellValue("18");

        FileOutputStream fileOutputStream = new FileOutputStream(PATH_URI + "2007qyy.xlsx");
        xssfSheets.write(fileOutputStream);
        fileOutputStream.close();

        System.out.println("XSSF 2007 执行完成");

    }



    //    2003 最大数据量65536行测试 HSSFWorkbook  数据量小65536  速度快
    public void exlxsWriteXSSFBigDate03() throws IOException {
        //开始时间
        long begin = System.currentTimeMillis();
        //创建一个簿
        Workbook workbook = new HSSFWorkbook();
        // 创建一个表
        Sheet sheet = workbook.createSheet();
        //写入数据 先创建行
        for(int rowNum = 0 ; rowNum < 6 ;rowNum++){
            // 创建行
            Row row = sheet.createRow(rowNum);
            // for循环,创建列
            for (int cellNum = 0 ;cellNum < 10 ; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("运行完毕");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH_URI + "exlxsWriteXSSFBigDate.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end-begin)/1000);
    }

    //    2007 最大数据量65536行测试 XSSFWorkbook  数据量大   比较耗时
    public void exlxsWriteXSSFBigDate07() throws IOException {
        //开始时间
        long begin = System.currentTimeMillis();
        //创建一个簿
        Workbook workbook = new XSSFWorkbook();
        // 创建一个表
        Sheet sheet = workbook.createSheet();
        //写入数据 先创建行
        for(int rowNum = 0 ; rowNum < 65537 ;rowNum++){
            // 创建行
            Row row = sheet.createRow(rowNum);
            // for循环,创建列
            for (int cellNum = 0 ;cellNum < 10 ; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("运行完毕");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH_URI + "exlxsWriteXSSFBigDate07.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end-begin)/1000);
    }

//    优化 poi SXSSFWorkbook  先生成临时文件
    public void exlxsWriteSXSSFBigDate07() throws IOException {
        //开始时间
        long begin = System.currentTimeMillis();
        //创建一个簿
        Workbook workbook = new SXSSFWorkbook();
        // 创建一个表
        Sheet sheet = workbook.createSheet();
        //写入数据 先创建行
        for(int rowNum = 0 ; rowNum < 65537 ;rowNum++){
            // 创建行
            Row row = sheet.createRow(rowNum);
            // for循环,创建列
            for (int cellNum = 0 ;cellNum < 10 ; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("运行完毕");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH_URI + "exlxsWriteXSSFBigDate07.xlsx");
        workbook.write(fileOutputStream);
//        清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end-begin)/1000);
    }
}
