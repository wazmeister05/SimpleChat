package Client;

import Server.GameServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class GameClientCaptain {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String chatServerURL = "//localhost:11111/Battleship";
        GameServerInterface chatServer = (GameServerInterface) Naming.lookup(chatServerURL);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String name = scanner.nextLine();
        new Thread(new GameClient(name, chatServer)).start();
    }
}
