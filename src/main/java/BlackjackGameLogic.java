import java.util.ArrayList;

public class BlackjackGameLogic {

    public String whoWon(ArrayList <Card> playerHand1, ArrayList<Card> dealerHand){

        if (handTotal(playerHand1) > (handTotal(dealerHand))){
            return "player";
        }

        if (handTotal(playerHand1) < (handTotal(dealerHand))){
            return "dealer";
        }

        else{
            return "push";
        }
        
    }

    public int handTotal(ArrayList<Card> hand) {

        int runningTotal = 0;
        int aceCount = 0;

        for (Card card : hand) {
            if (card.face.equals("Ace")) {
                aceCount++;
                runningTotal += 11; // Initially, treat Ace as 11
            } else {
                runningTotal += card.value;
            }
        }
        // If the total exceeds 21 and there are Aces in the hand, treat them as 1
        while (runningTotal > 21 && aceCount > 0) {
            runningTotal -= 10; // Convert an Ace from 11 to 1
            aceCount--;
        }
        
        return runningTotal;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand){
        if (handTotal(hand) <= 16){
            return true;
        }
        else{
            return false;
        }
    }

    public String printDeck(ArrayList<Card> deck){
        String decktemp = "";

        for (Card element : deck){
            decktemp+= element.face + element.suit;
        }

        return decktemp;
    }
}
