import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class BlackjackDealerTest {

    @Test
    void testWhoWonPlayerWins() {
        BlackjackGameLogic logic = new BlackjackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>(); // Add cards to ensure player wins
        ArrayList<Card> dealerHand = new ArrayList<>(); // Add cards to ensure dealer loses
        String winner = logic.whoWon(playerHand, dealerHand);
        assertEquals("player", winner, "The player should be determined as the winner.");
    }
}
