package com.example.queens;

public class CustomQueenCounter implements QueenCounter {
    private int returnValue;

    public CustomQueenCounter(int returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public int count(boolean[][] board) {
        return returnValue;
    }

    public void setReturnValue(int value) {
        this.returnValue = value;
    }
} 