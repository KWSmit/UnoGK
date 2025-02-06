package nl.kwsmit.unogk;

public class Player {
    private String name;
    private int numberOfCards;
    private boolean unoCall;

    public Player(String name) {
        this.name = name;
        numberOfCards = 0;
        unoCall = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int nr) {
        numberOfCards = nr;
    }

    public void addToNumberOfCards(int nr) {
        numberOfCards += nr;
    }

    public void subtractFromNumberOfCards(int nr) {
        numberOfCards -= nr;
    }

    public boolean getUnoCall() {
        return unoCall;
    }

    public void setUnoCall(boolean uno) {
        unoCall = uno;
    }
}
