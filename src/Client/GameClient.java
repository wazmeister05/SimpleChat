package Client;

import Server.GameServerInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class GameClient extends UnicastRemoteObject implements GameClientInterface, Runnable {

    private static final long serialVersionUID = 1L;
    private GameServerInterface chatServer;
    private String name = null;
    protected GameClient(String name, GameServerInterface chatServer) throws RemoteException{
        this.name = name;
        this.chatServer = chatServer;
        chatServer.registerChatClient(this);
    }

    @Override
    public void getMoves(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message;
        while(true){
            message = scanner.nextLine();
            try{
                chatServer.sendMoves(name + " : " + message);
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }
}
