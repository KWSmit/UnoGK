package nl.kwsmit.unogk;

enum Direction {
    CLOCKWISE,
    COUNTERCLOCKWISE,
}

/**
 * Class containing the state of the game.
 *
 * nrOfPlayers : Number of players in the game.
 * indexActivePLayer : Index in list of Players for active player.
 * nameActivePlayer : Name of the active player.
 * direction : Direction of the game (determines next player).
 * nrOfPenaltyCards : Number of cards a player has to take.
 * unoCall : True when a player has called UNO (has just one card left over.
 */
public class GameState {

    private int nrOfPlayers;
    private int indexActivePlayer;
    private String nameActivePlayer;
    private Direction direction;
    private int nrOfPenaltyCards;
    private boolean unoCall;

    GameState() {
        nrOfPlayers = 0;
        indexActivePlayer = 0;
        nameActivePlayer = "";
        direction = Direction.COUNTERCLOCKWISE;
        nrOfPenaltyCards = 0;
        unoCall = false;
    }

    public int getNrOfPlayers() {
        return nrOfPlayers;
    }

    public void setNrOfPlayers(int nrOfPlayers) {
        this.nrOfPlayers = nrOfPlayers;
    }

    public int getIndexActivePlayer() {
        return indexActivePlayer;
    }

    public void setIndexActivePlayer(int indexActivePlayer) {
        this.indexActivePlayer = indexActivePlayer;
    }

    public String getNameActivePlayer() {
        return nameActivePlayer;
    }

    public void setNameActivePlayer(String nameActivePlayer) {
        this.nameActivePlayer = nameActivePlayer;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getNrOfPenaltyCards() {
        return nrOfPenaltyCards;
    }

    public void setNrOfPenaltyCards(int nrOfPenaltyCards) {
        this.nrOfPenaltyCards = nrOfPenaltyCards;
    }

    public boolean getUnoCall() {
        return unoCall;
    }

    public void setUnoCall(boolean unoCall) {
        this.unoCall = unoCall;
    }

}
