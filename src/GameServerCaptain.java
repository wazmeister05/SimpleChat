import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class GameServerCaptain {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(11111);
        Naming.rebind("//localhost:11111/Battleship",  new GameServer());
        System.out.println("Server running");
    }
}
