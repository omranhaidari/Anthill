package fr.cypno.anthill;

import fr.cypno.anthill.graphics.Frame;

public class Program {
    public static void main(String[] args) {
        Resources.init(5);
        Frame.launchFrame(args, 50);
    }
}
