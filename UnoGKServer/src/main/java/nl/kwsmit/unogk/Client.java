package nl.kwsmit.unogk;

import java.io.PrintWriter;
import java.io.BufferedReader;

public class Client {
    String name;
    int numberOfCards;
    boolean unoCall;
    PrintWriter out;
    BufferedReader in;
    String id;

    public Client() {
        name = "";
        numberOfCards = 0;
        unoCall = false;
        out = null;
        in = null;
    }
}
