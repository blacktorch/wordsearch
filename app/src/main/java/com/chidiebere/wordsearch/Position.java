package com.chidiebere.wordsearch;

public class Position {

    private int orientation, direction, startRow, endRow, startCol, endCol;

    public Position(int orientation, int direction, int startRow, int endRow, int startCol, int endCol){
        this.orientation = orientation;
        this.direction = direction;
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
    }


    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }
}

