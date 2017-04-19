package com.yunxiang.salary;

import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by wangqingxiang on 2017/4/18.
 */
public class ReadExcelUtil {
    static Logger logger = Logger.getLogger(ReadExcelUtil.class.getName());
    public static Map<String, List<String>> readSalary(String filePath) {

        Map<String, List<String>> salaryMap = new HashMap<String, List<String>>();
        List<String> salary;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //同时支持Excel 2003、2007
            File excelFile = new File(filePath); //创建文件对象
            FileInputStream is = new FileInputStream(excelFile); //文件流
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();


            int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
            //遍历每个Sheet
            for (int s = 0; s < sheetCount; s++) {
                logger.info("<<<<<<<<<<<<第"+(s+1)+"页>>>>>>>>>>>>>>>>>>>>");
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数

                //遍历每一行
                for (int r = 0; r < rowCount; r++) {
                    Row row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    String username = "";

                    salary = new ArrayList<String>();
                    int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
                    //遍历每一列

                    for (int c = 0; c <= 27; c++) {

                        Cell cell = row.getCell(c);
                        String cellValue = null;
                        if (cell == null) {
                            cellValue = "0.0";
                        } else {
                            int cellType = cell.getCellType();
                            switch (cellType) {
                                case Cell.CELL_TYPE_STRING: //文本
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC: //数字、日期
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        cellValue = fmt.format(cell.getDateCellValue()); //日期型
                                    } else {
                                        cellValue = String.valueOf(cell.getNumericCellValue()); //数字
                                    }
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN: //布尔型
                                    cellValue = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case Cell.CELL_TYPE_BLANK: //空白
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_ERROR: //错误
                                    cellValue = "错误";
                                    break;
                                case Cell.CELL_TYPE_FORMULA: //公式

                                    try {
                                        CellValue value = evaluator.evaluate(cell);
                                        if (value != null) {
                                            cellValue = String.valueOf(value.getNumberValue());
                                        } else {
                                            cellValue = "错误";
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        cellValue = "错误";
                                    }
                                    break;
                                default:
                                    cellValue = "错误";
                            }
                        }

                        if (c == 0) {
                            username = cellValue;
                        } else {
                            salary.add(cellValue);
                        }
                        logger.info(cellValue);

                    }
                    System.out.println("");
                    salaryMap.put(username, salary);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return salaryMap;


    }

    public static Map<String, String> readEmail(String filePath) {

        Map<String, String> emailMap = new HashMap<String, String>();
        try {
            //同时支持Excel 2003、2007

            File excelFile = new File(filePath); //创建文件对象
            FileInputStream is = new FileInputStream(excelFile); //文件流
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();


            int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
            //遍历每个Sheet
            for (int s = 0; s < sheetCount; s++) {
//                System.out.println("<<<<<<<<<<<<第"+(s+1)+"页>>>>>>>>>>>>>>>>>>>>");
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数

                //遍历每一行
                for (int r = 0; r < rowCount; r++) {
                    Row row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    Cell cell = row.getCell(0);
                    if (cell == null) {
                        continue;
                    }
                    String username = cell.getStringCellValue();
                    if (username == null || username.equals("")) {
                        continue;
                    }
                    Cell cell2 = row.getCell(1);
                    if (cell2 == null) {
                        continue;
                    }
                    String email = cell2.getStringCellValue();
                    emailMap.put(username, email);
                    logger.info("<<<<<<<<<"+username+":"+email+">>>>>>>>>>>");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailMap;


    }

    public static Properties readEmail2() {
        Properties pro = new Properties();
        try {
            InputStreamReader in = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("emails.properties"), "UTF-8");
            pro.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

}
