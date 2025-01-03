package assignment2;

import java.util.Arrays;
import java.util.Random;

public class Deck {
    public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
    public static Random gen = new Random();

    public int numOfCards; // contains the total number of cards in the deck
    public Card head; // contains a pointer to the card on the top of the deck



    public Deck(int numOfCardsPerSuit, int numOfSuits) {

        if(numOfCardsPerSuit<=0 || numOfCardsPerSuit>=14 || numOfSuits>suitsInOrder.length || numOfSuits<=0){ //numOfSuits>suitsInOrder.length +1 ?????
            throw new IllegalArgumentException("Invalid inputs");
        }

        else{
            int suitFinder = 0;
            for (int i = 1; i<=numOfCardsPerSuit*numOfSuits;i++){
                Card card = new PlayingCard(suitsInOrder[suitFinder], i-numOfCardsPerSuit*suitFinder);
                addCard(card);
                if (i % numOfCardsPerSuit  == 0) {
                    suitFinder += 1;
                }

            }
            Card redJoker = new Joker("red");
            Card blackJoker = new Joker("black");
            addCard(redJoker);
            addCard(blackJoker);


        }


    }

    /*
     * TODO: Implements a copy constructor for Deck using Card.getCopy().
     * This method runs in O(n), where n is the number of cards in d.
     */

    public Deck(Deck d) {

        Card index = d.head;

        addCard(index.getCopy());
        index=index.next;

        while (index!=d.head){
            addCard(index.getCopy());
            index=index.next;
        }


    }






    /*
     * For testing purposes we need a default constructor.
     */
    public Deck() { }


    /*
     * TODO: Adds the specified card at the bottom of the deck. This method runs in $O(1)$.
     */
    public void addCard(Card c) {

        numOfCards++;

        if(head == null){
            head=c;
            head.next=c;
            head.prev=c;
        }
        else{
            Card bottom = head.prev;
            c.next = head;
            head.prev = c;
            c.prev = bottom;
            bottom.next = c;
        }
    }


    /*
     * TODO: Shuffles the deck using the algorithm described in the pdf.
     * This method runs in O(n) and uses O(n) space, where n is the total
     * number of cards in the deck.
     */
    public void shuffle() {
        Card[] array = new Card[numOfCards];
        Card temp = head;

        for(int i = 0;i<array.length;i++){
            array[i] = temp;
            temp = temp.next;
        }

        for(int i =numOfCards-1;i>=1;i--){
            int j = gen.nextInt(i+1);
            Card temp2 = array[j];
            array[j] = array[i];
            array[i] = temp2;
        }


        head = null;

        numOfCards =0;

        for(int i = 0;i< array.length; i++){
           addCard(array[i]);

        }

    }

    /*
     * TODO: Returns a reference to the joker with the specified color in
     * the deck. This method runs in O(n), where n is the total number of
     * cards in the deck.
     */

    public Joker locateJoker(String color) {



        Card index = head;

        if(head instanceof Joker) {

            if (((Joker) head).getColor().equals(color)) {


                return (Joker) head;

            }
        }

        while (index.next!=head){

            index = index.next;


            if(index instanceof Joker){
                if(( (Joker) index).getColor().equals(color)){

                    return (Joker) index;
                }

            }

        }


        return null;

    }

    /*
     * TODO: Moved the specified Card, p positions down the deck. You can
     * assume that the input Card does belong to the deck (hence the deck is
     * not empty). This method runs in O(p).
     */
    public void moveCard(Card c, int p) {
       Card temp;

        for(int i=0;i<p;i++) {
            temp = c.next;

            c.next = temp.next;
            temp.prev = c.prev;
            temp.next = c;
            c.prev = temp;

            c.prev.prev.next = c.prev;
            c.next.prev = c;

        }

  }

    /*
     * TODO: Performs a triple cut on the deck using the two input cards. You
     * can assume that the input cards belong to the deck and the first one is
     * nearest to the top of the deck. This method runs in O(1)
     */
    public void tripleCut(Card firstCard, Card secondCard) {

            if(secondCard == head.prev){
                head = firstCard;

                return;
            }

            if(firstCard == head){
                head = secondCard.next;

                return;
            }


                Card oldBottom = head.prev;
                Card oldHead = head;

                Card newBottom = firstCard.prev;
                Card newHead = secondCard.next;

                oldHead.prev = secondCard;
                oldBottom.next = firstCard;

                head = newHead;
                head.prev = newBottom;
                newBottom.next = head;
                firstCard.prev = oldBottom;
                secondCard.next = oldHead;



            }



    /*
     * TODO: Performs a count cut on the deck. Note that if the value of the
     * bottom card is equal to a multiple of the number of cards in the deck,
     * then the method should not do anything. This method runs in O(n).
     */
    public void countCut() {

        Card bottom = head.prev;
        int value = bottom.getValue();


        if (value % numOfCards == 0 || value == numOfCards - 1 ) {

            return;
        }


        else {

            Card start = head;
            Card end = head;

            Card OldBottomPointerPrev = bottom.prev;
            Card oldHead = head;

            for(int i=1; i<value % numOfCards;i++){
                end = end.next;
            }
            Card newHead = end.next;


            head = newHead;
            start.prev = OldBottomPointerPrev;
            OldBottomPointerPrev.next = start;
            bottom.prev = end;
            end.next = bottom;
            bottom.next = head;
            head.prev = bottom;



            return;

            }


    }

    /*
     * TODO: Returns the card that can be found by looking at the value of the
     * card on the top of the deck, and counting down that many cards. If the
     * card found is a Joker, then the method returns null, otherwise it returns
     * the Card found. This method runs in O(n).
     */
    public Card lookUpCard() {

        int value = head.getValue();
        Card index = head;

        for(int i = 0;i<value;i++){
            index = index.next;
        }

        if(index instanceof PlayingCard){
            return index;
        }
        else{
            return null;
        }

    }

    /*
     * TODO: Uses the Solitaire algorithm to generate one value for the keystream
     * using this deck. This method runs in O(n).
     */
    public int generateNextKeystreamValue() {
        int key;



        Card redJoker = locateJoker("red");
        moveCard(redJoker,1);


        Card blackJoker = locateJoker("black");
        moveCard(blackJoker,2);


        Card index = head;


        while(index.next != head){

            if(index == redJoker){
                tripleCut(redJoker, blackJoker);
              break;
            }
            if(index == blackJoker){
                tripleCut(blackJoker, redJoker);
                break;
            }

            index = index.next;
        }

        countCut();

        Card lookValue = lookUpCard();

        if(lookValue == null){
            key = generateNextKeystreamValue();



        }
        else{

            key = lookValue.getValue();

        }

        return key;


    }





    public abstract class Card {
        public Card next;
        public Card prev;

        public abstract Card getCopy();
        public abstract int getValue();

    }

    public class PlayingCard extends Card {
        public String suit;
        public int rank;

        public PlayingCard(String s, int r) {
            this.suit = s.toLowerCase();
            this.rank = r;
        }

        public String toString() {
            String info = "";
            if (this.rank == 1) {
                //info += "Ace";
                info += "A";
            } else if (this.rank > 10) {
                String[] cards = {"Jack", "Queen", "King"};
                //info += cards[this.rank - 11];
                info += cards[this.rank - 11].charAt(0);
            } else {
                info += this.rank;
            }
            //info += " of " + this.suit;
            info = (info + this.suit.charAt(0)).toUpperCase();
            return info;
        }

        public PlayingCard getCopy() {

            return new PlayingCard(this.suit, this.rank);
        }

        public int getValue() {
            int i;
            for (i = 0; i < suitsInOrder.length; i++) {
                if (this.suit.equals(suitsInOrder[i]))
                    break;
            }

            return this.rank + 13*i;
        }

    }






    public class Joker extends Card{
        public String redOrBlack;

        public Joker(String c) {
            if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black"))
                throw new IllegalArgumentException("Jokers can only be red or black");

            this.redOrBlack = c.toLowerCase();
        }

        public String toString() {
            //return this.redOrBlack + " Joker";
            return (this.redOrBlack.charAt(0) + "J").toUpperCase();
        }

        public Joker getCopy() {

            return new Joker(this.redOrBlack);
        }

        public int getValue() {

            return numOfCards - 1;
        }

        public String getColor() {
            return this.redOrBlack;
        }
    }




}
