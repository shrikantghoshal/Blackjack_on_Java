import java.util.*;

/**
 * A complete simulation of the classic card game Blackjack
 * 
 * <p>Developed while completing COMP1721 Coursework 2</p>
 * 
 * @author Shrikant Ghoshal
 */
public class Game{
    
    /**
     * Main method of a game simulation of Blackjack
     * 
     * <p>Interactive: uses user inputs to play the game.</p>
     * 
     * <p>Employs the use of Deck and BlackjackHand classes developed as part of COMP1721 Coursework 2</p>
     * 
     * @param args - String argument input by users when prompted
     */
    public static void main(String[] args){
        Deck gameDeck = new Deck();
        int playerWins = 0;
        int dealerWins = 0;
        int gameTies = 0;
        int roundNumber = 0;
        gameDeck.shuffle();
        
        Scanner userInput = new Scanner(System.in);
        System.out.println("\nHello! Try your hand at Blackjack! \n");
        
        
        String inputResponse;
        System.out.println("Would you like to play? ([Y]es/[N]o) \n");
        inputResponse = userInput.next();
            do{
                while(!inputResponse.toLowerCase().equals("y") && !inputResponse.toLowerCase().equals("n")){
                    System.out.println("Invalid entry, please enter either 'y' or 'n'. This program is not case sensitive.\n");
                    inputResponse = userInput.next();
                }
                
                if(inputResponse.toLowerCase().equals("y")){
                    roundNumber++;
                    BlackjackHand playerHand = new BlackjackHand();
                    BlackjackHand dealerHand = new BlackjackHand();
                    do{                        
                        playerHand.add(gameDeck.deal());

                        System.out.println("\nYour hand: " + playerHand);

                        if(playerHand.isBust()){
                            dealerWins++;
                            System.out.println("\nPlayer's hand is a bust. Dealer Wins!\n");
                            break;
                        }
                        
                        System.out.println("Would you like to [H]it or [S]tand?");

                        inputResponse = userInput.next();

                        while(inputResponse.toLowerCase().equals("h") == false && inputResponse.toLowerCase().equals("s") == false){
                            System.out.println("Invalid entry, please enter either 'h' or 's'. This program is not case sensitive.\n");
                            inputResponse = userInput.next();
                        }

                        

                        if(inputResponse.toLowerCase().equals("s") || playerHand.isNatural()){
                            do{
                                dealerHand.add(gameDeck.deal());
                                System.out.println("Dealer hand: " + dealerHand + "\n");

                                
                                if(dealerHand.isNatural() || playerHand.isNatural()){
                                    if (dealerHand.isNatural() && playerHand.isNatural()){
                                        gameTies++;
                                        System.out.println("\nGame tied! Both player and dealer have natural hands.\n");
                                        break;
                                    }
                                    else if(dealerHand.isNatural() && !playerHand.isNatural()){
                                        dealerWins++;
                                        System.out.println("\nDealer's hand is Natural. Dealer Wins!\n");
                                        break;
                                    }
                                    else{
                                        playerWins++;
                                        System.out.println("\nPlayer's hand is Natural. Player Wins!\n");
                                        break;
                                    }
                                }

                                else if (dealerHand.value() >= 17 && dealerHand.value()<21){
                                    int handDifference = playerHand.value() - dealerHand.value();
                                    if(handDifference > 0){
                                        playerWins++;
                                        System.out.println("\nPlayer Wins!\n");
                                        break;
                                    }
                                    else if (handDifference == 0){
                                        gameTies++;
                                        System.out.println("\nGame tied!\n");
                                        break;
                                    }

                                    else{
                                        dealerWins++;
                                        System.out.println("\nDealer Wins!\n");
                                        break;
                                    }
                                }

                            }while(!dealerHand.isBust());
                        }

                        if(dealerHand.isBust()){
                            playerWins++;
                            System.out.println("\nDealer's hand is a bust. Player Wins!\n");
                            break;
                        }

                    }while(inputResponse.toLowerCase().equals("h"));

                    System.out.println("Would you like to play again? ([Y]es/[N]o) \n");
                    inputResponse = userInput.next();
                    while(!inputResponse.toLowerCase().equals("y") && !inputResponse.toLowerCase().equals("n")){
                        System.out.println("Invalid entry, please enter either 'y' or 'n'. This program is not case sensitive.\n");
                        inputResponse = userInput.next();
                    }
                }
                    
            }while(inputResponse.toLowerCase().equals("y"));

            if (inputResponse.toLowerCase().equals("n")){
                System.out.println("\nYou have chosen not to play another game. You have played " + roundNumber + " round(s), and the results are " + playerWins + " player win(s), " + dealerWins + " dealer win(s), and " + gameTies + " tied game(s).\n");
                System.out.println("\nThank you for using this program. Hope you enjoyed playing the game.\n");
            }


        userInput.close();
    }




    
    
    



}