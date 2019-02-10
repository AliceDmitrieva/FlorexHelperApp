package florexhelper;

import com.sun.istack.internal.NotNull;

public class Flower {

    private double boxCount;
    private String title;
    private String length;
    private String price;
    private double capacity;

    public Flower(Flower flower) {
        this.boxCount = flower.boxCount;
        this.title = flower.title;
        this.length = flower.length;
        this.price = flower.price;
        this.capacity = flower.capacity;
    }

    public Flower(String title, String length, double capacity) {
        this.title = title;
        this.length = length;
        this.capacity = capacity;
    }

    public Flower(String title, String length, String price) {
        this.title = title;
        this.length = length;
        this.price = price;
    }

    public Flower(double boxCount, String title, String length) {
        this.boxCount = boxCount;
        this.title = title;
        this.length = length;
    }

    public Flower(@NotNull double boxCount, @NotNull String title, @NotNull String length, String price) {
        this.boxCount = boxCount;
        this.title = title;
        this.length = length;
        this.price = price;
    }

    public Flower copy(Flower flower) {
        return new Flower(flower);
    }

    public double getCountOfBox() {
        return boxCount;
    }

    public void setCountOfBox(double countOfBox) {
        this.boxCount = countOfBox;
    }

    public String getTitle() {
        return this.title = title.replaceAll("QB", "").replaceAll("HB", "").trim();
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length.replaceAll("/", "").replaceAll("-", "").trim();
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price.replaceAll("-", "").replaceAll("/", "").trim();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Flower)) {
            return false;
        }

        Flower flower = (Flower) obj;
        return flower.getTitle().trim().equals(title.trim()) && flower.getLength().trim().equals(length.trim());
    }

    @Override
    public String toString() {
        return this.getTitle() + " " + this.getLength() + " " + this.getCapacity();
    }
}
