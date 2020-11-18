
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameServerInterface extends Remote {
    void registerChatClient(GameClientInterface chatClient) throws RemoteException;
    void sendMoves(String message) throws RemoteException;
    void soloGameGUI(int bgLength, String playerName) throws RemoteException;
    }
