package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameClientInterface extends Remote {
    void getMoves(String message) throws RemoteException;
}
