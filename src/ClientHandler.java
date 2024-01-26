import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ServerData serverData;

    public ClientHandler(Socket clientSocket, ServerData serverData) {
        this.clientSocket = clientSocket;
        this.serverData = serverData;
        try {
            this.input = new ObjectInputStream(clientSocket.getInputStream());
            this.output = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object receivedObject = input.readObject();

                if (receivedObject instanceof ClientRequest) {
                    handleClientRequest((ClientRequest) receivedObject);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClientRequest(ClientRequest request) {
        switch (request.getRequestType()) {
            case GET_ALL_BOOKS:
                sendObjectToClient(serverData.getAllBooks());
                break;
            case ADD_BOOK:
                serverData.addBook(request.getProduct());
                broadcastToAllClients(serverData.getAllBooks());
                break;
            case BORROW_BOOK:
                int studentIdToBorrow = request.getStudentId();
                int bookIdToBorrow = request.getBookId();
                serverData.borrowBook(studentIdToBorrow, bookIdToBorrow);
                broadcastToAllClients(serverData.getAllBooks());
                break;
            case RETURN_BOOK:
                int bookIdToReturn = request.getBookId();
                serverData.returnBook(bookIdToReturn);
                broadcastToAllClients(serverData.getAllBooks());
                break;
        }
    }

    private void sendObjectToClient(Object obj) {
        try {
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcastToAllClients(Object obj) {

    }
}