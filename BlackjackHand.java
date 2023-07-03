import java.util.*;



/**
 * Representation of a player's hand of cards in the game of Blackjack
 * 
 * <p>Developed while completing COMP1721 Coursework 2.</p>
 *
 * @author Shrikant Ghoshal
 */
class BlackjackHand extends CardCollection{
    
    /**
     * Creates an empty hand
     */
    public BlackjackHand(){
        cards = new LinkedList<>();
    }

    /**
     * Accepts a string of two-character representations of each card separated by a space
     * 
     * <p> Creates Card objects and adds them to a player's hand </p>
     * 
     * @param cards
     */
    public BlackjackHand(String cards){
        
        String[] nameOfCard = cards.split(" ");

        for(int i = 0; i < nameOfCard.length; i++){
            Card singleCard = new Card(nameOfCard[i]);

            add(singleCard);
        }
    }
    /**
     *Empties a hand of all its cards, and add each one to the given deck

     <p>Throws an instance CardException if called in an empty hand</p>
     * @param deck
     */
    public void discard (Deck deck){

        if (isEmpty()==true){
            throw new CardException("Empty Hand!");
        }
        
                

        for(int cardNumber = 0; cardNumber < size(); cardNumber++){
            Card handyCard = cards.get(cardNumber);
            deck.add(handyCard);
        }
        super.discard();
    }

    /**
     * Tests whether a player's hand qualifies as a Natural
     * 
     * <p>Conditions for Natural: first two cards in a hand add up to 21 - a combination of an Ace and a card with a value of 10 (a 10 or face cards)</p> 
     * @return True if the player's hand is Natural, false otherwise
     */
    public boolean isNatural(){
        if (cards.size() == 2){
            if(this.value() == 21)
                return true;
            else   
                return false;
        }
        else
            return false;
    }

    /**
     * Tests whether a player's hand is bust
     * 
     * <p>Conditions for a bust: sum of cards in a hand exceed 21</p> 
     * @return True if the player's hand is a bust, false otherwise
     */
    public boolean isBust(){
        if (this.value() > 21)
            return true;
        else
            return false;
    }
    
    /**
   * Adds the given card to a player's hand if it is not a bust.
   *
   * <p>If this method is used on a hand that is already bust, then an instance of CardException is thrown</p>
   * 
   * @param card Card to be added
   */
    public void add(Card card){
        if(isBust() == true)
            throw new CardException("Player is already Busted!");
        else
            super.add(card);
    }

    /**
     * In Blackjack, an Ace card can either amount to 1 or 11, based on whether or not a hand is bust.
     * 
     * <p>In this method, an algorithm has been implemented to automatically decide the value of an ace, and subsequently calculate the face value of the cards.</p>
     * 
     * @return Value of cards in a Hand taking into account the variable nature of an Ace card.
     */
    public int value(){
        int aceNumber = 0;
        int handValue;

        for(int cardNumber = 0; cardNumber < size(); cardNumber++){
            
            if(cards.get(cardNumber).getRank() == Card.Rank.ACE){
                aceNumber++;
            }
        }

        if(aceNumber == 0)
            handValue = super.value();
        else{
            if(super.value() > 11){
                handValue = super.value();
            }
            else
                handValue = 10 + super.value();
        }

        return handValue;
    }

    @Override
    public String toString() {

        StringBuilder handCards = new StringBuilder();

        for (int i = 0; i < size(); i++){
            cards.get(i);
            handCards.append(cards.get(i).toString());

            if (i < (size()-1)){
                handCards.append(" ");
            }
            
        }

        return String.format(handCards.toString());
    }
}