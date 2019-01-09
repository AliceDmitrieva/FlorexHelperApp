package florexhelper.fileutils;

import florexhelper.DataFormatter;
import florexhelper.Flower;
import florexhelper.gui.Window;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileOutputCreator {

    public static final String OUTPUT_FILE_PATH = "C:/Users/PC/Desktop/Florex/";
    public static String fileName;

    public static void createOutputFile(List<Flower> flowerList, String plantationName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(plantationName);
        sheet.setZoom(120);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 8);
        font.setFontName("Tahoma");

        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFont(font);
        headerCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFCellStyle casualStyle = workbook.createCellStyle();
        casualStyle.setAlignment(HorizontalAlignment.CENTER);
        casualStyle.setFont(font);

        Row rowHeader = sheet.createRow(0);
        rowHeader.createCell(0).setCellValue("ID OO");
        rowHeader.createCell(1).setCellValue("Производитель");
        rowHeader.createCell(2).setCellValue("Тип");
        rowHeader.createCell(3).setCellValue("Сорт");
        rowHeader.createCell(4).setCellValue("Длина");
        rowHeader.createCell(5).setCellValue("Производство");
        rowHeader.createCell(6).setCellValue("Цена");
        rowHeader.createCell(7).setCellValue("Мин. Лот");
        rowHeader.createCell(8).setCellValue("Количество");
        rowHeader.createCell(9).setCellValue("Свежесть");
        rowHeader.createCell(10).setCellValue("Качество");
        for (int i = 0; i < 11; i++) {
            rowHeader.getCell(i).setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (Flower flower : flowerList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(1)
                    .setCellValue(plantationName);
            row.createCell(2)
                    .setCellValue("ROSE");
            row.createCell(3)
                    .setCellValue(flower.getTitle());
            row.createCell(4)
                    .setCellValue(flower.getLength().trim());
            row.createCell(5)
                    .setCellValue("Эквадор");
            row.createCell(6)
                    .setCellValue(DataFormatter.formatPrice(flower));
            row.createCell(7)
                    .setCellValue(25);
            row.createCell(8)
                    .setCellValue(DataFormatter.formatCount(flower));

            for (int i = 1; i < 9; i++) {
                row.getCell(i).setCellStyle(casualStyle);
            }
        }

        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = null;
        try {
            fileName = DataFormatter.formatFileTitle(plantationName);
            fileOut = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workbook.write(fileOut);
            System.out.println("\nResult file is ready! :)");
            Window.showPopUpWindow();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (fileOut != null) {
                fileOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
