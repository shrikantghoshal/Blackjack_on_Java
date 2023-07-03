import java.util.*;

/**
 * Representation of a deck in the game of Blackjack
 *
 * <p>Developed while completing COMP1721 Coursework 2.</p>
 *
 * @author Shrikant Ghoshal
 */
public class Deck extends CardCollection{

    public Deck(){

        cards = new LinkedList<>();
        
        int suitCount, rankCount;

        for (suitCount = 0; suitCount < Card.Suit.values().length; suitCount++) {
            Card.Suit[] addedSuit = Card.Suit.values() ;

            for (rankCount = 0; rankCount < Card.Rank.values().length; rankCount++) {
                Card.Rank[] addedRank = Card.Rank.values();

                Card addedCard = new Card(addedRank[rankCount], addedSuit[suitCount]);

                add(addedCard);
            }
        }
    }
    /**
     * Shuffles a collection's cards in a random order 
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    /**
     * Removes a card from the top of the deck and returns the removed card
     * 
     * <p>Throws an instance of CardException if the Deck is empty</p>
     * 
     * @return Card to be dealt
     */
    public Card deal(){

        if (isEmpty()==true){
            throw new CardException("Deck is Empty!");
        }
        Card dealtCard = cards.get(0);
        cards.remove(0);
        return dealtCard;

    }

}