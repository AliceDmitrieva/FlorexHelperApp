package florexhelper;

import florexhelper.fileutils.AssortmentFileHandler;
import florexhelper.fileutils.FileOutputCreator;
import florexhelper.fileutils.SupplyFileHandler;
import florexhelper.gui.Window;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Launcher {

    public static Window window;

    public static void main(String[] args) {
    /*    String plantation = getUserInput();*/;

        Window.showWindow();
    }

    public static void start(String plantationName, boolean isPriceInside) {
        try {
            List<Flower> supplyList = SupplyFileHandler.getSupplyList(isPriceInside);
            List<Flower> capacityList = AssortmentFileHandler.getFlowerCapacityList(plantationName);
            List<Flower> priceList = AssortmentFileHandler.getFlowerPriceList(plantationName);

            List<Flower> resultList;
            if (!isPriceInside) {
                resultList = getFlowerSupplyPriceList(getFlowerSupplyCapacityList(supplyList, capacityList), priceList);
            } else {
                resultList = getFlowerSupplyCapacityList(supplyList, capacityList);
            }
            resultList = setDefaultCapacity(resultList, plantationName);
            FileOutputCreator.createOutputFile(resultList, plantationName);

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public static List<Flower> getFlowerSupplyCapacityList(List<Flower> flowerSupplyList, List<Flower> flowerCapacityList) {
        for (Flower supplyFlower : flowerSupplyList) {
            for (Flower assortmentFlower : flowerCapacityList) {
                if (supplyFlower.getTitle().toUpperCase().equals(assortmentFlower.getTitle())
                        && (supplyFlower.getLength().trim().equals(assortmentFlower.getLength()))) {
                    supplyFlower.setCapacity(assortmentFlower.getCapacity());
                }
            }
        }
        return flowerSupplyList;
    }

    public static List<Flower> getFlowerSupplyPriceList(List<Flower> flowerSupplyList, List<Flower> flowerPriceList) {
        for (Flower supplyFlower : flowerSupplyList) {
            for (Flower priceFlower : flowerPriceList) {
                if (supplyFlower.getTitle().toUpperCase().equals(priceFlower.getTitle())
                        && (supplyFlower.getLength().trim().equals(priceFlower.getLength()))) {
                    supplyFlower.setPrice(priceFlower.getPrice());
                }
            }
        }
        return flowerSupplyList;
    }

    public static List<Flower> setDefaultCapacity(List<Flower> flowerList, String plantationName) {
        for (Flower flower : flowerList) {
            if (flower.getCapacity() == 0.0) {
                flower.setCapacity(AssortmentFileHandler.getDefaultCapacity(flower.getLength().trim(), plantationName));
            }
        }
        return flowerList;
    }

       /* public static String getUserInput() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter plantation name: ");
        Flower.plantationName = reader.nextLine().toUpperCase();
        reader.close();
        return Flower.plantationName;
    }*/
}
