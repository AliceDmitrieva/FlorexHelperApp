package florexhelper.fileutils;

import florexhelper.Flower;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssortmentFileHandler {

    private static final String CAPACITY_FILE_PATH = "C:/Users/PC/Desktop/Florex/assortment_";
    private static final String PRICE_FILE_PATH = "C:/Users/PC/Desktop/Florex/price_";

    public static String setCapacityFile(String plantation) {
        return CAPACITY_FILE_PATH + plantation + ".xlsx";
    }

    public static String setPriceFile(String plantation) {
        return PRICE_FILE_PATH + plantation + ".xlsx";
    }

    public static List<Flower> getFlowerCapacityList(String plantation) throws IOException, InvalidFormatException {
        Workbook workbookDraft = WorkbookFactory.create(new File(setCapacityFile(plantation)));
        System.out.println("\nGetting assortment list...");
        Sheet sheet = workbookDraft.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        List<Flower> flowerCapacityList = new ArrayList<>();
        System.out.println("Iterating over Rows and Columns using Iterator on AssortmentFile List...\n");
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            String title = null;
            String length = null;
            double capacity = 0.0;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                    case 4:
                        title = dataFormatter.formatCellValue(cell);
                        break;
                    case 5:
                        length = dataFormatter.formatCellValue(cell);
                        break;
                    case 9:
                        capacity = Double.parseDouble(dataFormatter.formatCellValue(cell));
                        break;
                    default:
                        break;
                }
            }
            flowerCapacityList.add(new Flower(title, length, capacity));
        }
        return flowerCapacityList;
    }

    public static List<Flower> getFlowerPriceList(String plantation) throws IOException, InvalidFormatException {
        try (Workbook workbookDraft = WorkbookFactory.create(new File(setPriceFile(plantation)))) {
            System.out.println("\nGetting price list...");
            Sheet sheet = workbookDraft.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            List<Flower> flowerPriceList = new ArrayList<>();
            System.out.println("Iterating over Rows and Columns using Iterator on PriceList...\n");
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String title = null;
                String length = null;
                String price = null;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0:
                            title = dataFormatter.formatCellValue(cell);
                            break;
                        case 1:
                            length = dataFormatter.formatCellValue(cell);
                            break;
                        case 2:
                            price = dataFormatter.formatCellValue(cell);
                            break;
                        default:
                            break;
                    }
                }
                flowerPriceList.add(new Flower(title, length, price));
            }
            return flowerPriceList;

        } catch (FileNotFoundException e) {
            System.out.println("No " + plantation + " PriceList is Found");
            return null;
        }
    }

    public static double getDefaultCapacity(String length, String plantationName) {
        double capacity = 0.0;
        if ((plantationName.equals("ALBRA")) || (plantationName.equals("EVERBLOOM")) ||
                (plantationName.equals("EDEN ROSES"))) {
            switch (length) {
                case "40":
                case "50":
                    capacity = 400.0;
                    break;
                case "60":
                    capacity = 350.0;
                    break;
                case "70":
                    capacity = 300.0;
                    break;
                case "80":
                    capacity = 250.0;
                    break;
                case "90":
                case "100":
                    capacity = 200.0;
                    break;
                default:
                    capacity = 0.0;
                    break;
            }
        }

        if (plantationName.equals("ANNIROSES")) {
            switch (length) {
                case "50":
                    capacity = 400.0;
                    break;
                case "60":
                case "70":
                    capacity = 350.0;
                    break;
                case "80":
                    capacity = 300.0;
                    break;
                case "90":
                    capacity = 250.0;
                    break;
                case "100":
                    capacity = 200.0;
                    break;
                case "110":
                    capacity = 150.0;
                    break;
                default:
                    capacity = 0.0;
                    break;
            }
        }

        if (plantationName.equals("ALLEGRO 1") || (plantationName.equals("ALLEGRO 2"))) {
            switch (length) {
                case "50":
                    capacity = 450.0;
                    break;
                case "60":
                    capacity = 400.0;
                    break;
                case "70":
                    capacity = 350.0;
                    break;
                case "80":
                    capacity = 300.0;
                    break;
                case "90":
                    capacity = 250.0;
                    break;
                default:
                    capacity = 0.0;
                    break;
            }
        }
        return capacity;
    }
}
