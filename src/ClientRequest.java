import java.io.Serializable;

public class ClientRequest implements Serializable {
    public enum RequestType {
        GET_ALL_BOOKS,
        ADD_BOOK,
        BORROW_BOOK,
        RETURN_BOOK
    }

    private RequestType requestType;
    private Product product;
    private int studentId;
    private int bookId;

    public ClientRequest(RequestType requestType) {
        this.requestType = requestType;
    }

    public ClientRequest(RequestType requestType, Product product) {
        this.requestType = requestType;
        this.product = product;
    }

    // Add this constructor for borrowing and returning books
    public ClientRequest(RequestType requestType, int studentId, int bookId) {
        this.requestType = requestType;
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Product getProduct() {
        return product;
    }

    // Add getters for new fields
    public int getStudentId() {
        return studentId;
    }

    public int getBookId() {
        return bookId;
    }
}