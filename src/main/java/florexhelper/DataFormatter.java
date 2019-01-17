package florexhelper;

import florexhelper.fileutils.FileOutputCreator;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
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

    public static double formatCount(Flower flower) {
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        double countOfBox = Double.valueOf(decimalFormat.format(flower.getCountOfBox()));
        double capacity = flower.getCapacity();
        double count = countOfBox * capacity;

        return (int) (count / 25) * 25;
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
