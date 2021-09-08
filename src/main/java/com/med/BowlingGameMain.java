package com.med;

public class BowlingGameMain {
    public static void main(String[] args) {
        BowlingGame bg = new BowlingGame();
        for(int i=0; i<20; i++) {
            bg.roll(1);
        }

        System.out.println(bg.getScore());
    }
}
