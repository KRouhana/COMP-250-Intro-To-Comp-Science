package assignment2;


public class SolitaireCipher {
    public Deck key;

    public SolitaireCipher (Deck key) {

        this.key = new Deck(key); // deep copy of the deck
    }

    /*
     * TODO: Generates a keystream of the given size
     */
    public int[] getKeystream(int size) {

        int[] Keystream = new int[size];

                for(int i=0;i<size;i++){

                    Keystream[i] = key.generateNextKeystreamValue();

                }

        return Keystream;
    }

    /*
     * TODO: Encodes the input message using the algorithm described in the pdf.
     */
    public String encode(String msg) {
        String message = "";
        msg = msg.replaceAll("[^a-zA-Z]", "");

        int lenght = msg.length();

        int stream[] = getKeystream(lenght);

        for(int i = 0 ; i <lenght; i++){
            char ch = Character.toUpperCase(msg.charAt(i));
            int alphabet = ch -'A'+1;


            int codedLetter =alphabet + stream[i];

            while(codedLetter>26) {
                codedLetter = codedLetter - 26 ;
            }


            message+= (char) (codedLetter - 1+'A');

        }

        return message;
    }

    /*
     * TODO: Decodes the input message using the algorithm described in the pdf.
     */
    public String decode(String msg) {

        String message="";
        message = message.replaceAll("[^a-zA-Z]", "");

        int lenght = msg.length();
        int stream[] = getKeystream(lenght);

        for(int i = 0 ; i <lenght; i++){
            char ch = Character.toUpperCase(msg.charAt(i));
            int alphabet = ch -'A'+1;


            int codedLetter =alphabet - stream[i];

            while(codedLetter<=0) {
                codedLetter = codedLetter + 26 ;
            }


            message+= (char) (codedLetter - 1+'A');

        }

        return message;
    }

}
