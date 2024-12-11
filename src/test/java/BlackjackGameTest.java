import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class BlackjackGameTest {

    private BlackjackGame game;

    @BeforeEach
    void setUp() {
        game = new BlackjackGame();
        game.startingMoney = 1000; // Reset the starting money before each test
    }

    @Test
    void testEvaluateWinningsPlayerWins() {
        game.currentBet = 100;
        game.playerHand = new ArrayList<>(); // Add cards to ensure player wins
        game.bankerHand = new ArrayList<>(); // Add cards to ensure dealer loses
        game.evaluateWinnings();
        assertEquals(1100, game.startingMoney, "Player's money should increase by the bet amount when they win.");
    }
}
