package com.TPP.Connect4;

public class Model {

    private int cellsRemaining = 0;
    private int max;
    private int xLen;
    private int yLen;
    private Grid myGrid;

    public Model(Grid tempGrid, int maxx) {
        max = maxx;//connect 4 or n
        myGrid = tempGrid;
        cellsRemaining = myGrid.getCellsRemaining();
        xLen = myGrid.getXLen();
        yLen = myGrid.getYLen();
    }
    
    public Grid getGrid() {
    	return myGrid;
    }
    
    public boolean setAndCheck(int row, int column, int player) {//sets the found coordinate to current player
        myGrid.setMatrix(row, column, player);
        cellsRemaining--;
        return winCheck(player);
    }

    public boolean drawCheck() {//checks for draw game
        return cellsRemaining == 0;
    }

    private boolean winCheck(int player) {
    	
    	int count=0;
    	
    	//Horizontal check
    	for (int j=0;j<yLen;j++) {
    		count=0;
    		for (int i=0;i<xLen;i++) {
    			if (myGrid.matrixEquals(i,j,player)) {
    				count++;
    			}
    			if (count==max) {
    				return true;
    			}
    			if (!myGrid.matrixEquals(i, j, player)) {
    				count=0;
    			}
    		}
    	}
    	count=0;
    	
    	//Vertical check
    	for (int i=0;i<xLen;i++) { 
    		count=0;
    		for (int j=0;j<yLen;j++) {
    			if (myGrid.matrixEquals(i,j,player)) {
    				count++;
    			}
    			if (count==max) {
    				return true;
    			}
    			if (!myGrid.matrixEquals(i, j, player)) {
    				count=0;
    			}
    		}
    	}
    	count=0;
    	
    	
    	//Top-left to bottom right diagonal check 1
    	int i,j,k;
    	for(k = 0;k<xLen-max+1;k++){
    		count=0;
    	    for(i=k,j=0;i<xLen && j<yLen;i++,j++){
    	        if(myGrid.matrixEquals(i,j,player)){
    	            count++;
    	        }
    	        if(count == max) {
    	        	return true;
    	        }
    	        if (!myGrid.matrixEquals(i, j, player)) {
    	        	count = 0;
    	        }
    	    }
    	}
    	count=0;
    	
    	//Top-left to bottom right diagonal check 2
    	for (k=1;k<yLen-max+1;k++) {
    		count=0;
    		for (i=0, j=k;i<xLen && j<yLen;i++,j++) {
    			if (myGrid.matrixEquals(i,j,player)) {
    				count++;
    			}
    			if (count==max) {
    				return true;
    			}
    			if (!myGrid.matrixEquals(i, j, player)) {
    				count=0;
    			}
    		}
    	}
    	count=0;
    	
    	//Top-right to bottom left diagonal check 1
    	for (k=max-1;k<xLen;k++) {
    		count=0;
    		for (i=k, j=0;i>=0 && j<yLen;i--,j++) {
    			if (myGrid.matrixEquals(i,j,player)) {
    				count++;
    			}
    			if (count==max) {
    				return true;
    			}
    			if (!myGrid.matrixEquals(i, j, player)) {
    				count=0;
    			}
    		}
    	}
    	count=0;
    	
    	//Top-right to bottom left diagonal check 2
    	for (k=1;k<yLen-max+1;k++) {
    		count=0;
    		for (i=xLen-1, j=k;i>=0 && j<yLen;i--,j++) {
    			if (myGrid.matrixEquals(i,j,player)) {
    				count++;
    			}
    			if (count==max) {
    				return true;
    			}
    			if (!myGrid.matrixEquals(i, j, player)) {
    				count=0;
    			}
    		}
    	}
    	count=0;
    	
    	return false;
    }
}
