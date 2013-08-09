package blackjack_test.base;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private final static int NUM_DECKS = 8;
    private int numCardsRemaining;
    private final static int FACES_IN_DECK = 13;
    private final static int SUITS_IN_DECK = 4;
    private final static int CARDS_IN_DECK = 52;
    private final static int CARDS_IN_FIRST_HAND = 2;
    private int count;
    

    public Deck(){
        for(int i = 0; i < NUM_DECKS; i++){
            for(int j = 0; j < SUITS_IN_DECK; j++){
                for(int k = 0; k < FACES_IN_DECK; k++){
                    deck.add(new Card(j, k));
                }
            }
        }
        shuffle();
    }

    public void shuffle(){
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Random random = new Random();
        while(deck.size() > 0){
            int cardToRemove = random.nextInt(deck.size());
            Card tempCard = deck.get(cardToRemove);
            deck.remove(cardToRemove);
            tempDeck.add(tempCard);
        }
        while(tempDeck.size() > 0){
            Card tempCard = tempDeck.get(0);
            tempDeck.remove(0);
            deck.add(tempCard);            
        }
        count = 0;
        numCardsRemaining = NUM_DECKS * CARDS_IN_DECK;
    }
  
    public Card draw(){
        Card toDraw = deck.get(0);
        deck.remove(0);
        numCardsRemaining--;
        int face = toDraw.getFace();
        if (face >= Card.TWO && face <= Card.SIX)
                count++;
        if (face >= Card.TEN | face == Card.ACE)
                count--;
        return toDraw;
    }
    
    public int getCount() {
        double decksLeft = (double) numCardsRemaining / CARDS_IN_DECK;          
        return (int)Math.round(count / decksLeft);
    }
    
    public void addToBottom(Card c){
        deck.add(c);
        if (numCardsRemaining < 20) {
                shuffle(); 
        }
    }
}
