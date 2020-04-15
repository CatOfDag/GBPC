package com.huse.utils;

import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelFileGenerator {

    private Class c;
    private String filename = "本次生成的Pins.xls";
    private String[] sheetnames;
    private List<String> columnnames = new ArrayList<>();
    private List<String> values = new ArrayList<>();

    public void create() throws IOException {
        Gson gson = new Gson();
        String Savepath = "H:\\scoreSave\\";
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFCell hssfCell = null;

        int row;
        for (String sheetname : sheetnames){
            row = 0;
            HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetname);/*生成对应的Sheet*/
            HSSFRow hssfRow = hssfSheet.createRow(row++);
            for (int i = 0; i < columnnames.size(); i++) {
                hssfCell = hssfRow.createCell(i);
                hssfCell.setCellValue(columnnames.get(i));
            }
            for (int i = 0; i < values.size(); i++) {
                String value = values.get(i);
                Map map = gson.fromJson(value,Map.class);
                if (map.get("role").equals(sheetname)){
                    hssfRow = hssfSheet.createRow(row++);
                    for (int j = 0; j < columnnames.size(); j++) {
                        hssfCell = hssfRow.createCell(j);
                        if (map.get(columnnames.get(j)) != null)
                            hssfCell.setCellValue(map.get(columnnames.get(j)).toString());
                    }
                }
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(Savepath+filename);
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public ExcelFileGenerator(Class c, String[] sheetnames,List<String> values) {
        this.c = c;
        if (sheetnames != null)
            this.sheetnames = sheetnames;
        else this.sheetnames[0] = "sheet1";
        this.values = values;
        setColumnnames();
    }

    public ExcelFileGenerator() {
    }
    /*获取存储对象的所有变量，做表头*/
    private void setColumnnames(){
        Field[] fields = c.getDeclaredFields();
        for (Field field: fields){
            columnnames.add(field.getName());
        }
    }
}
