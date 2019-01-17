package florexhelper.fileutils;

import florexhelper.Flower;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyFileHandler {

    public static final String SUPPLY_FILE_PATH = "C:/Users/PC/Desktop/Florex/supplyfile.xlsx";

    public static List<Flower> getSupplyList(boolean isPriceInside) {
        Workbook workbookSupply = null;
        try {
            workbookSupply = WorkbookFactory.create(new File(SUPPLY_FILE_PATH));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

        Sheet sheet = workbookSupply != null ? workbookSupply.getSheetAt(0) : null;
        List<Flower> flowerList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Flower flower;

                if (isPriceInside) {
                    flower = receiveFlowerWithPriceInside(cell.getStringCellValue());
                } else {
                    flower = receiveFlower(cell.getStringCellValue());
                }

                if (flower != null) {
                    String flowerLength = flower.getLength();
                    String flowerPrice = flower.getPrice();

                    if (flowerLength.contains("-") || flowerLength.contains("/")) {
                        int dashCount = checkDoubleCounts(flowerLength);

                        if (dashCount == 1) {
                            flower.setLength(setDoubleLengthFirstFlower(flowerLength));
                            if (flowerPrice != null) {
                                flower.setPrice(setDoublePriceFirstFlower(flowerPrice));
                            }
                            flower.setCountOfBox(flower.getCountOfBox() / 2);
                            flowerList.add(flower);

                            Flower copiedFlower = flower.copy(flower);
                            copiedFlower.setLength(setDoubleLengthSecondFlower(flowerLength));
                            if (flowerPrice != null) {
                                copiedFlower.setPrice(setDoublePriceSecondFlower(flowerPrice));
                            }
                            flowerList.add(copiedFlower);
                        }
                        if (dashCount == 2) {
                            flower.setLength(setDoubleLengthFirstFlower(flowerLength));
                            if (flowerPrice != null) {
                                flower.setPrice(setDoublePriceFirstFlower(flowerPrice));
                            }
                            flower.setCountOfBox(flower.getCountOfBox() / 3);
                            flowerList.add(flower);

                            Flower copiedSecondFlower = flower.copy(flower);
                            copiedSecondFlower.setLength(setDoubleLengthSecondFlower(flowerLength));
                            if (flowerPrice != null) {
                                copiedSecondFlower.setPrice(setDoublePriceSecondFlower(flowerPrice));
                            }
                            flowerList.add(copiedSecondFlower);

                            int thirdFlowerLength = Integer.parseInt(copiedSecondFlower.getLength()) + (Integer.parseInt(copiedSecondFlower.getLength()) - Integer.parseInt(flower.getLength()));
                            Flower copiedThirdFlower = copiedSecondFlower.copy(copiedSecondFlower);
                            copiedThirdFlower.setLength(String.valueOf(thirdFlowerLength));
                            if (flowerPrice != null) {
                                double thirdFlowerPrice = Double.parseDouble(copiedSecondFlower.getPrice()) + (Double.parseDouble(copiedSecondFlower.getPrice()) - Double.parseDouble(flower.getPrice()));
                                copiedThirdFlower.setPrice(String.valueOf(thirdFlowerPrice));
                            }
                            flowerList.add(copiedThirdFlower);
                        }
                    } else {
                        flowerList.add(flower);
                    }
                }
            }
        }
        return flowerList;
    }

    private static int checkDoubleCounts(String length) {
        int flowerCount = 0;
        char[] lengthCharArray = length.toCharArray();
        for (int i = 0; i < lengthCharArray.length; i++) {
            if ((lengthCharArray[i] == '-') || (lengthCharArray[i] == '/')) {
                flowerCount++;
            }
        }
        return flowerCount;
    }

    public static String setDoubleLengthFirstFlower(String flowerLength) {
        String firstLength = null;
        Pattern lengthPattern = Pattern.compile("([1-9][0-9][0]?[\\-|/])");
        Matcher lengthPatternMatcher = lengthPattern.matcher(flowerLength);

        if (lengthPatternMatcher.find()) {
            firstLength = lengthPatternMatcher.group();
        }
        return firstLength;
    }

    public static String setDoubleLengthSecondFlower(String flowerLength) {
        String secondLength = null;
        Pattern lengthPattern = Pattern.compile("([\\-|/][1-9][0-9][0]?)");
        Matcher lengthPatternMatcher = lengthPattern.matcher(flowerLength);

        if (lengthPatternMatcher.find()) {
            secondLength = lengthPatternMatcher.group();
        }
        return secondLength;
    }

    public static String setDoublePriceFirstFlower(String flowerPrice) {
        String firstPrice = null;
        Pattern pricePattern = Pattern.compile("[0-1][,|.][1-9][0-9]");
        Matcher pricePatternMatcher = pricePattern.matcher(flowerPrice);

        if (pricePatternMatcher.find()) {
            firstPrice = pricePatternMatcher.group();
        }
        return firstPrice;
    }

    public static String setDoublePriceSecondFlower(String flowerPrice) {
        String secondPrice = null;
        Pattern pricePattern = Pattern.compile("[\\-|/][0-1][,|.][1-9][0-9]");
        Matcher pricePatternMatcher = pricePattern.matcher(flowerPrice);

        if (pricePatternMatcher.find()) {
            secondPrice = pricePatternMatcher.group();
        }
        return secondPrice;
    }

    public static Flower receiveFlower(String source) {
        Pattern boxPattern = Pattern.compile("[1-9][0-9]?[\\s|\\t]?[\\s]?[Q|H][B]");
        Pattern titlePattern = Pattern.compile("[a-zA-Z]+[\\s?[a-zA-Z]?]+");
        Pattern lengthPattern = Pattern.compile("([\\s][1-9][0-9][0]?[\\-|/][1-9][0-9][0]?[\\-|/][1-9][0-9][0]?)|([\\s][1-9][0-9][0]?[\\-|/][1-9][0-9][0]?)|([\\s][1-9][0-9][0]?)");

        Matcher boxPatternMatcher = boxPattern.matcher(source);
        Matcher titlePatternMatcher = titlePattern.matcher(source);
        Matcher lengthPatternMatcher = lengthPattern.matcher(source);

        Flower flower = null;

        if (boxPatternMatcher.find() && titlePatternMatcher.find() && lengthPatternMatcher.find()) {

            String boxCountString = boxPatternMatcher.group().trim();
            double boxCount = 0.0;

            if (boxCountString.contains("H")) {
                String hb = boxCountString.replaceAll("HB", "").trim();
                boxCount = Integer.parseInt(hb);
            }
            if (boxCountString.contains("Q")) {
                String qb = boxCountString.replaceAll("QB", "").trim();
                boxCount = Integer.parseInt(qb);
                boxCount /= 2;
            }

            flower = new Flower(boxCount, titlePatternMatcher.group(), lengthPatternMatcher.group());
        }
        return flower;
    }

    public static Flower receiveFlowerWithPriceInside(String source) {
        Pattern boxPattern = Pattern.compile("[1-9][0-9]?[\\s|\\t]?[\\s]?[Q|H][B]");
        Pattern titlePattern = Pattern.compile("[a-zA-Z]+[\\s?[a-zA-Z]?]+");
        Pattern lengthPattern = Pattern.compile("([\\s][1-9][0-9][0]?[\\-|/][1-9][0-9][0]?[\\-|/][1-9][0-9][0]?)|([\\s][1-9][0-9][0]?[\\-|/][1-9][0-9][0]?)|([\\s][1-9][0-9][0]?)");
        Pattern pricePattern = Pattern.compile("([0-1][,|.][1-9][0-9][\\-|/][0-1][,|.][1-9][0-9][\\-|/][0-1][,|.][1-9][0-9])|([0-1][,|.][1-9][0-9][\\-|/][0-1][,|.][1-9][0-9])|([0-1][,|.][1-9][0-9])");

        Matcher boxPatternMatcher = boxPattern.matcher(source);
        Matcher titlePatternMatcher = titlePattern.matcher(source);
        Matcher lengthPatternMatcher = lengthPattern.matcher(source);
        Matcher pricePatternMatcher = pricePattern.matcher(source);

        Flower flower = null;

        if (boxPatternMatcher.find() && titlePatternMatcher.find() && lengthPatternMatcher.find() && pricePatternMatcher.find()) {

            String boxCountString = boxPatternMatcher.group().trim();
            double boxCount = 0.0;

            if (boxCountString.contains("H")) {
                String hb = boxCountString.replaceAll("HB", "").trim();
                boxCount = Integer.parseInt(hb);
            }
            if (boxCountString.contains("Q")) {
                String qb = boxCountString.replaceAll("QB", "").trim();
                boxCount = Integer.parseInt(qb);
                boxCount /= 2;
            }

            flower = new Flower(boxCount, titlePatternMatcher.group(), lengthPatternMatcher.group(), pricePatternMatcher.group());
        }
        return flower;
    }

}
