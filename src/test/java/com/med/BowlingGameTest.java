package com.med;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    private int playGame(int pins) {
        BowlingGame bg = new BowlingGame();
        for (int i = 0; i < 20; i++) {
            bg.roll(pins);
        }
        return bg.getScore();
    }

    @Test
    public void getScore_should_return_0() { // [0,0] * 10 => score = 0
        int score = playGame(0);
        assertEquals(0, score);
    }

    @Test
    public void getScore_should_return_20() { // [1,1] * 10 => score = 20
        int score = playGame(1);
        assertEquals(20, score);
    }

    @Test
    public void getScore_should_return_29_spare_first() { // [6,4] ([1,1] * 9) => score = 20 / 11 + 18 = 29
        BowlingGame bg = new BowlingGame();
        bg.roll(6);
        bg.roll(4);
        for (int i = 0; i < 18; i++) {
            bg.roll(1);
        }
        int score = bg.getScore();
        assertEquals(29, score);
    }

    @Test
    public void getScore_should_return_33_spare_last() { // ([1,1] * 9) [6,4] + [5] => score = 20 / 18 + 15 = 20 + 13 = 33
        BowlingGame bg = new BowlingGame();
        for (int i = 0; i < 18; i++) {
            bg.roll(1);
        }
        bg.roll(6);
        bg.roll(4);
        bg.roll(5);
        int score = bg.getScore();
        assertEquals(33, score);
    }


    // [10] [1,1] * 9 => 10 + 2 + 18 = 30
    // 10 0, 1 1, 1 1, 1 1, 1 1, 1 1, 1 1, 1 1, 1 1, 1 1
    @Test
    public void getScore_should_return_30_strike_first() {
        BowlingGame bg = new BowlingGame();
        bg.roll(10);
        for (int i = 0; i < 18; i++) {
            bg.roll(1);
        }
        int score = bg.getScore();
        assertEquals(30, score);
    }

    @Test
    public void getScore_should_return_300_all_strikes() {
        BowlingGame bg = new BowlingGame();
        for (int i = 0; i < 10; i++) {
            bg.roll(10);
        }
        bg.roll(10);
        bg.roll(10);
        System.out.println(bg.toString());
        int score = bg.getScore();
        assertEquals(300, score);
    }
}