package com.ibnshayed.www;

import java.util.Arrays;

public class Maze {
    private int row,col;
    private int startRow,startCol;
    private int endRow,endCol;
    private boolean[][] board;

    public Maze(int row, int col, int startRow, int startCol, int endRow, int endCol) {
        this.row = row;
        this.col = col;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.board = new boolean[row][col];
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Maze{" +
                "row=" + row +
                ", col=" + col +
                ", startRow=" + startRow +
                ", startCol=" + startCol +
                ", endRow=" + endRow +
                ", endCol=" + endCol +
                ", maze=" + Arrays.toString(board) +
                '}';
    }
}
