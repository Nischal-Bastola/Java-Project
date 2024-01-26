import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSock {
    private ServerSocket serverSocket;
    private ServerData serverData;

    public ServerSock(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.serverData = new ServerData();
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                new Thread(new ClientHandler(clientSocket, serverData)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
