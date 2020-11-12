package Server;

import Client.GameClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameServer extends UnicastRemoteObject implements GameServerInterface {

    // literally every server I've ever seen has this line
    private static final long serialVersionUID = 1L;
    private ArrayList<GameClientInterface> chatClients;

    protected GameServer() throws RemoteException{
        chatClients = new ArrayList<>();
    }

    @Override
    public synchronized void registerChatClient(GameClientInterface chatClient) throws RemoteException {
        this.chatClients.add(chatClient);
    }


    @Override
    public synchronized void sendMoves(String message) throws RemoteException {
        int i = 0;
        while(i < chatClients.size()){
            chatClients.get(i++).getMoves(message);
        }
    }
}
