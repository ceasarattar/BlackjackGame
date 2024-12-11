import java.util.ArrayList;
import java.util.Collections;

public class BlackjackDealer {

    ArrayList<Card> deck = new ArrayList<Card>();

    public void generateDeck(){
        String[] face = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};

        for (String element : face) { //Ace
            for (String suit : suits) { //Spades
                Card card = new Card(element, suit, 0);
                if (card.face == "ace"){
                    card.value = 11;
                }
                if (card.face == "king" || card.face == "queen" ||card.face == "jack" ||card.face == "10"){
                    card.value = 10;
                }
                if (card.face == "9"){
                    card.value = 9;
                }
                if (card.face == "8"){
                    card.value = 8;
                }
                if (card.face == "7"){
                    card.value = 7;
                }
                if (card.face == "6"){
                    card.value = 6;
                }
                if (card.face == "5"){
                    card.value = 5;
                }
                if (card.face == "4"){
                    card.value = 4;
                }
                if (card.face == "3"){
                    card.value = 3;
                }
                if (card.face == "2"){
                    card.value = 2;
                }
                deck.add(card);
            }
        }
      
    }

    public Card drawOne(){
        Card temp = deck.get(0);
        deck.remove(0);
        return temp;
    }

    public ArrayList<Card> dealHand(){
        ArrayList<Card> temp = new ArrayList<Card>();
        temp.add(deck.get(0));
        temp.add(deck.get(1));
        deck.remove(0);
        deck.remove(1);
        return temp;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public int deckSize(){
        return deck.size();
    }

}