import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerData {
    private Connection connection;

    public ServerData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/library";
            String username = "root";
            String password = "";
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getAllBooks() {
        ArrayList<Product> books = new ArrayList<>();
        try {
            String query = "SELECT * FROM books";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    double price = resultSet.getDouble("price");
                    int studentId = resultSet.getInt("student_id");
                    books.add(new Product(id, title, author, price, studentId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    public void addBook(Product book) {
        try {
            String query = "INSERT INTO books (title, author, price, student_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setDouble(3, book.getPrice());
                statement.setInt(4, book.getStudentId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrowBook(int studentId, int bookId) {
        try {
            String query = "UPDATE books SET student_id = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, studentId);
                statement.setInt(2, bookId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookId) {
        try {
            String query = "UPDATE books SET student_id = NULL WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bookId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getStudentIdForBook(int bookId) {
        try {
            String query = "SELECT student_id FROM books WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bookId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("student_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}