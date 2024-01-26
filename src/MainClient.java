// MainClient.java
public class MainClient {
    public static void main(String[] args) {
        ClientSock client = new ClientSock("localhost", 6666);
        ClientGUI gui = new ClientGUI(client);
        gui.showGUI();
    }
}
