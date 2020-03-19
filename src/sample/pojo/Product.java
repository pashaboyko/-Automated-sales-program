package sample.pojo;

public class Product {

    private int id;
    private String name;
    private String barcode;
    private double price;
    private String photo;

    public Product(int id, String name, String barcode, double price, String photo) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.photo = photo;
    }

    public Product() {
        photo="1.jpg";
    }
    public Product(Product another) {
        this.id=another.id;
        this.barcode=another.barcode;
        this.name=another.name;
        this.price=another.price;
        this.photo=another.photo;

    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
