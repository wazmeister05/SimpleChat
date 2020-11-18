import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class GameClientCaptain {

    private static final int bgLength = 5;
    private static Scanner scanner;
    private static GameServerInterface gameServer;
    //private static RMIinterface lookUp;
    //private String name;


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        gameServer = (GameServerInterface) Naming.lookup("//localhost:11111/Battleship");
        scanner = new Scanner(System.in);
        System.out.println("Welcome to Battleship!");
        System.out.print("Enter a name: ");
        String name = scanner.nextLine();

        while (true) {
            System.out.println("Play solo on CLI (1), solo on GUI(2), with someone(3) or quit(0)");
            int choice;

            String choiceStr = scanner.nextLine();
            // confirm a number was entered.
            if (choiceStr.matches("[0-9][0-9]*")) {
                choice = Integer.parseInt(choiceStr);
                if (choice == 1) {
                    soloGameCLI(name);
                } else if (choice == 2) {
                    soloGameGUI(name);
                } else if (choice == 3) {
                    multiplayer(name);
                } else if (choice == 0) {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                } else {
                    System.out.println("Please enter 1, 2, 3 or 0");
                }
            } else {
                System.out.println("Please enter a number");
            }
        }
    }


    public static void soloGameCLI(String name) {
        boolean cont = true;

        while(cont) {
            BattleshipBoard playerOne = new BattleshipBoard(bgLength);
            BattleshipBoard ai = new BattleshipBoard(bgLength);

            while(true){
                playerOne.printBoard();
                // fire and pass in your opponents board to confirm if hit worked
                if (playerOne.playerFire(ai.getBoard())) {
                    ai.shipSunk();
                }
                // check to see if the enemies ships are all gone
                if (ai.getShipsRemaining() == 0) {
                    System.out.println(name + " wins!");
                    updatePlayer(name, "AI");
                    break;
                }
                // as above, so below
                if (ai.aiFire(playerOne.getBoard())) {
                    playerOne.shipSunk();
                }
                if (playerOne.getShipsRemaining() == 0) {
                    System.out.println("Computer wins!");
                    updatePlayer("AI", name);
                    break;
                }
            }
            cont = rematch();
        }
    }

    public static boolean gameOver = false;
    public static void setGameOver(){
        gameOver = true;
    }


    /**
     * Controlling method for the solo game against the AI.
     */
    public static void soloGameGUI(String name) throws RemoteException {
        gameServer.soloGameGUI(bgLength, name);
        while(true){
            if(gameOver){
                break;
            }
        }
    }


    /**
     * Controlling method for the multiplayer game (2 real players)
     */
    public static void multiplayer(String name) throws RemoteException {
        new Thread(new GameClient(name, gameServer, bgLength)).start();
        while(true){

        }


//        lookUp.multiplayerSetup(player, playerName);
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


    /**
     * FIRE
     * @param player player firing
     * @param opponent player being fired at
     * @param playerBoard player firings board
     * @param opponentBoard player being fired ats board
     * @return boolean if it hit or not
     */
    private static boolean fire(String player, String opponent, BattleshipBoard playerBoard, BattleshipBoard opponentBoard) {
        System.out.println(opponent + " fire");

        if(playerBoard.playerFire(opponentBoard.getBoard())){
            opponentBoard.shipSunk();
        }
        if(opponentBoard.getShipsRemaining() == 0){
            System.out.println(player + " wins!");
            updatePlayer(player, opponent);
            return true;
        }
        return false;
    }




    /**
     * @param winner player who won - file updated with win + games attempted
     * @param loser player who lost - file updated with games attempted
     */
    public static void updatePlayer(String winner, String loser){

        // This will use the file stuff Sonja is doing.

    }
//
//    @Override
//    public void getMoves(String message) throws RemoteException {
//
//    }

    public static boolean rematch() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to play again? (Y/N)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                break;
            }
            else if (choice.equalsIgnoreCase("n")) {
                return false;
            }
            else{
                System.out.println("Please enter a Y or N");
            }
        }
        return true;
    }
}
