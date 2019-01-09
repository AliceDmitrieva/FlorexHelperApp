package florexhelper;

import florexhelper.fileutils.FileOutputCreator;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFormatter {

    public static double formatPrice(Flower flower) {
        if (flower.getPrice() != null) {
            return Double.valueOf(flower.getPrice().replaceAll(",", ".")) * 100;
        } else {
            return 0.0;
        }
    }

    public static int formatCount(Flower flower) {
        double count = flower.getCountOfBox() * flower.getCapacity();
        int result = (int) (count / 25);
        return result * 25;
    }

    public static String formatDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDateTime currentDate = LocalDateTime.now();
        return dateTimeFormatter.format(currentDate);
    }

    public static String formatFileTitle(String plantationName) {
        return FileOutputCreator.OUTPUT_FILE_PATH + plantationName + " " + formatDate() + ".xls";
    }
}
