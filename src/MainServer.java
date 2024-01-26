// MainServer.java
public class MainServer {
    public static void main(String[] args) {
        ServerSock server = new ServerSock(6666);
        server.startServer();
    }
}
