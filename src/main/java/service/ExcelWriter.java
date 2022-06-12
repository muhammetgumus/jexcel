package service;

import model.Column;
import model.Row;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelWriter<T> {

    private List<Row> rows = new ArrayList<>();

    public <T> void write(Collection<T> collection) throws NullPointerException {
        if (collection == null || collection.size() <= 0) {
            throw new NullPointerException("Collection can not be null");
        }
        collection.forEach(this::createRows);
        exportExcel(rows, getCurrentModelClass(collection));
        rows.clear();
    }

    public <T> void createRows(T element) {
        List<Field> fields = Arrays.asList(element.getClass().getFields());
        Row row = new Row();
        for (Field field : fields) {
            Object value;
            try {
                if (field.isAnnotationPresent(Column.class)) {
                    value = element.getClass().getDeclaredField(field.getName()).get(element);
                    int columnNum = field.getAnnotation(Column.class).columnNumber();
                    row.getDataPair().put(columnNum, String.valueOf(value));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        rows.add(row);
    }

    public <T> void exportExcel(List<Row> collection, T classObj) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("first-sheet");
            setExcelHeaders(workbook, sheet, classObj);
            setExcelBody(sheet, collection);
            setExcelMetaData(workbook);
            writeDataToExcel(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HSSFCellStyle styleHeaderCells(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private void setExcelMetaData(HSSFWorkbook workbook) {
        workbook.setActiveSheet(0);
        workbook.setSheetName(0, "Sheet1");
    }

    private <T> void setExcelHeaders(HSSFWorkbook workbook, HSSFSheet sheet, T object) {
        HSSFRow headerRow = sheet.createRow(0);
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                try {
                    Object cellValue = field.getAnnotation(Column.class).name();
                    int columnNum = field.getAnnotation(Column.class).columnNumber();
                    HSSFCell cell = headerRow.createCell(columnNum - 1);
                    cell.setCellValue(String.valueOf(cellValue));
                    cell.setCellStyle(styleHeaderCells(workbook));
                    //HSSFCell cell =  headerRow.createCell(columnNum - 1).setCellValue(String.valueOf(cellValue));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void setExcelBody(HSSFSheet sheet, List<Row> collection) {
        for (int i = 0; i < collection.size(); i++) {
            Map<Integer, String> dataPair = collection.get(i).getDataPair();
            HSSFRow row = sheet.createRow(i + 1);
            for (Map.Entry<Integer, String> entry : dataPair.entrySet()) {
                row.createCell(entry.getKey() - 1).setCellValue(entry.getValue());
            }
        }
    }

    private void writeDataToExcel(HSSFWorkbook workbook) throws IOException {
        UUID uuid = UUID.randomUUID();
        File file = new File("" + uuid + ".xlsx");
        workbook.write(file);
        workbook.close();
    }

    private <T> T getCurrentModelClass(Collection<T> collection) {
        return collection.stream().findFirst().get();
    }
}
