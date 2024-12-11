import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {
    ArrayList<Card> playerHand = new ArrayList<Card>();
    ArrayList<Card> bankerHand  = new ArrayList<Card>();
    BlackjackDealer theDealer = new BlackjackDealer();
    BlackjackGameLogic gameLogic = new BlackjackGameLogic();
    double currentBet = 0;
    double totalWinnings = 0;
    double startingMoney = 1000;
    
    public double evaluateWinnings() {
        if ("player".equals(gameLogic.whoWon(playerHand, bankerHand))) {
            startingMoney += currentBet * 2; // Player wins double the bet
        } else if ("dealer".equals(gameLogic.whoWon(playerHand, bankerHand))) {
            // Player loses the bet, which has already been subtracted
        } 
        return startingMoney;
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter starting amount: ");
        game.startingMoney = scanner.nextDouble();

        while (game.startingMoney > 0) {
            game.theDealer.generateDeck();
            game.theDealer.shuffleDeck();

            System.out.println("Current money: " + game.startingMoney);
            System.out.println("Enter the bet: ");
            game.currentBet = scanner.nextDouble();

            if (game.currentBet > game.startingMoney) {
                System.out.println("Your bet cannot exceed your current money. Please try again.");
                continue;  // Skip the rest of the loop and start a new round
            }

            game.startingMoney -= game.currentBet; // Deduct the bet from the player's money

            game.playerHand = game.theDealer.dealHand();
            game.bankerHand = game.theDealer.dealHand();

            // Display player and dealer hands
            System.out.println("Player Hand: " + game.gameLogic.printDeck(game.playerHand));
            System.out.println("Dealer Hand: " + game.gameLogic.printDeck(game.bankerHand));

            // Player decision to hit or stand 
            System.out.println("Hit (h) or Stand (s)?");
            String decision = scanner.next();
            if ("h".equals(decision.toLowerCase())) {
                game.playerHand.add(game.theDealer.drawOne());
                System.out.println("Player Hand: " + game.gameLogic.printDeck(game.playerHand));
            }

            // Dealer plays (dealer hits if total is below 17)
            while (game.gameLogic.handTotal(game.bankerHand) < 17) {
                game.bankerHand.add(game.theDealer.drawOne());
            }
            System.out.println("Dealer Hand: " + game.gameLogic.printDeck(game.bankerHand));

            // Determine the winner and update player money
            game.evaluateWinnings();

            // Show updated player money
            System.out.println("Your current money: " + game.startingMoney);

            // Ask the player if they want to play another round
            System.out.println("Would you like to play another round? (yes/no)");
            String answer = scanner.next();
            if (!"yes".equalsIgnoreCase(answer)) {
                break;  // Exit the loop if the player does not want to continue
            }
        }

        System.out.println("Game over! Your final money: " + game.startingMoney);
        scanner.close();
    }
    

}
