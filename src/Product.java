import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String title;
    private String author;
    private double price;
    private int studentId;

    public Product(int id, String title, String author, double price, int studentId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getBookDetails() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Price: " + price + ", Student ID: " + studentId;
    }
}
