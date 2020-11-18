import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class GameClient extends UnicastRemoteObject implements GameClientInterface, Runnable {

    private static final long serialVersionUID = 1L;
    private GameServerInterface chatServer;
    private int bgLength;
    private String name = null;

    protected GameClient(String name, GameServerInterface chatServer, int bgLength) throws RemoteException{
        this.name = name;
        this.chatServer = chatServer;
        this.bgLength = bgLength;
        chatServer.registerChatClient(this);
    }

    @Override
    public void getMoves(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public void run() {
//        BattleshipBoard player = new BattleshipBoard(bgLength);
//        player.printBoard();

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
//        multiplayerSetup(player, playerName);
//        boolean gameCont = true;
//        while(gameCont){
//            player.printBoard();
//            lookUp.multiplayerPlay();
//
//        }
//
//
//        // here is where we'd put the file checking for player one
//
//        System.out.print("Player Two, enter your name: ");
//        String playerTwoName = scanner.nextLine();
//
//        // and here is for player two.
//        boolean cont = true;
//        while(cont) {
//            BattleshipBoard playerOne = new BattleshipBoard(bgLength);
//            BattleshipBoard playerTwo = new BattleshipBoard(bgLength);
//
//            while (true) {
//                playerOne.printBoard();
//                playerTwo.printBoard();
//                // fire and pass in your opponents board to confirm if hit worked
//                if (fire(playerOneName, playerTwoName, playerOne, playerTwo)) break;
//                // as above, so below
//                if (fire(playerTwoName, playerOneName, playerTwo, playerOne)) break;
//            }
//            cont = rematch();
//        }

    }
}
