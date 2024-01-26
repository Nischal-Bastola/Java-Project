import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSock {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientSock(String serverAddress, int serverPort) {
        try {
            this.socket = new Socket(serverAddress, serverPort);
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getAllBooks() {
        try {
            ClientRequest request = new ClientRequest(ClientRequest.RequestType.GET_ALL_BOOKS);
            output.writeObject(request);

            Object response = input.readObject();
            if (response instanceof ArrayList<?>) {
                return (ArrayList<Product>) response;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addBook(Product book) {
        try {
            ClientRequest request = new ClientRequest(ClientRequest.RequestType.ADD_BOOK, book);
            output.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New method to request borrowing a book
    public void borrowBook(int studentId, int bookId) {
        try {
            ClientRequest request = new ClientRequest(ClientRequest.RequestType.BORROW_BOOK, new Product(bookId, "", "", 0, studentId));
            output.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New method to request returning a book
    public void returnBook(int bookId) {
        try {
            ClientRequest request = new ClientRequest(ClientRequest.RequestType.RETURN_BOOK, new Product(bookId, "", "", 0, -1));
            output.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}