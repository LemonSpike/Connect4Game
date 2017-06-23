package com.TPP.Connect4;

public class Grid {

    private int xLen;
    private int yLen;
    private int[][] matrix;
    private int cellsRemaining = 0;

    public Grid(int xxLen,int yyLen) {
        xLen = xxLen;
        yLen = yyLen;

        matrix = new int[xLen][yLen];
        for (int i=0;i<xLen;i++) {
            for (int j=0;j<yLen;j++) {
                matrix[i][j] = 0;
                cellsRemaining++;
            }
        }
    }
    
    //methods to gain access to internal private data

    public int getCellsRemaining() {
        return cellsRemaining;
    }

    public int[][] getBoardMatrix() {
        return matrix;
    }
    
    public int getMatrixEntry(int row, int column) {
    	return matrix[row][column];
    }

    public boolean matrixEquals(int a, int b, int c) {
        return matrix[a][b]==c;
    }

    public void setMatrix(int a, int b, int player) {
        matrix[a][b]=player;
    }

    public int getXLen() {//returns the xLen
        return xLen;
    }

    public int getYLen() {//returns the yLen
        return yLen;
    }

    public int findY(int x) {//checks for room in column and returns free spot.
        int y = -1;
        int i=0;
        while (i<xLen && matrix[i][x]==0) {
        	y=i;
        	i++;
        }
        return y;
    }

    public int changePlayer(int player, int max_players) {
        player++;
        if (player > max_players) {
            return 1;
        }
        return player;
    }
}
