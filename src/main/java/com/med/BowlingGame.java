package com.med;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlingGame {

    private int frameIndex = 0;
    private final Map<Integer, List<Integer>> frames = new HashMap<>();

    public int getScore() {
        return frames
                .keySet()
                .stream()
                .filter(key -> key < 10)
                .map(key -> {
                    List<Integer> pins = frames.get(key);
                    int sum = pins.stream().mapToInt(Integer::intValue).sum();
                    if (sum == 10) {
                        if (pins.size() > 1) {
                            sum += getSpareScore(key);
                        } else {
                            sum += getStrikeScore(key);
                        }
                    }
                    return sum;
                })
                .mapToInt(Integer::intValue).sum();
    }

    public int getSpareScore(int frame) {
        if (frame < this.frames.size()) {
            List<Integer> nextFramePins = this.frames.get(frame+1);
            return nextFramePins.get(0);
        }
        return 0;
    }

    public int getStrikeScore(int frame) {
        if (frame < this.frames.size()) {
            List<Integer> nextFramePins = this.frames.get(frame+1);
            int sum = nextFramePins.get(0);
            if (nextFramePins.size() > 1) {
                sum += nextFramePins.get(1);
            } else {
                sum += this.frames.get(frame+2).get(0);
            }
            return sum;
        }
        return 0;
    }

    public void roll(int pins) {
        boolean endOfFrame = false;
        List<Integer> actualPins = new ArrayList<>();
        if (this.frames.get(this.frameIndex) != null) {
            actualPins = this.frames.get(this.frameIndex);
            endOfFrame = true;
        }
        actualPins.add(pins);
        this.frames.put(this.frameIndex, actualPins);
        if (pins == 10 || endOfFrame) {
            this.frameIndex++;
        }
    }
}
