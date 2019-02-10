package florexhelper;

import florexhelper.fileutils.AssortmentFileHandler;
import florexhelper.fileutils.FileOutputCreator;
import florexhelper.fileutils.SupplyFileHandler;
import florexhelper.gui.Window;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.*;

public class Launcher {

    public static void main(String[] args) {
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

            for (Flower f : resultList) {
                f.setCapacity(DataFormatter.formatCount(f));
            }

            findDuplicates(resultList);
            FileOutputCreator.createOutputFile(resultList, plantationName);

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public static void findDuplicates (List<Flower> flowers) {
        for (int i = 0; i < flowers.size(); i++) {
            for (int j = 0; j < flowers.size(); j++) {
                if (i != j) {
                    if (flowers.get(i).equals(flowers.get(j))) {
                        flowers.get(j).setCapacity(flowers.get(i).getCapacity() + flowers.get(j).getCapacity());
                        flowers.remove(flowers.get(i));
                    }
                }
            }
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
}
