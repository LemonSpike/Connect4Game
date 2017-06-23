package com.TPP.Connect4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;


/**
 * Unit test for Connect Four Model class.
 */
public class Connect4ModelTest {
	private Model model;
	private Grid grid;

	@Before
	public void setUp() {
		grid = new Grid(6,7);
		model = new Model(grid,4);
	}

	public void testWinHorizontal(int xLen, int yLen, int max) {
		grid = new Grid(xLen,yLen);
		model = new Model(grid,max);
		for (int i=0; i<max-1; i++) {
			model.setAndCheck(grid.getXLen()-1, i, 1);
			model.setAndCheck(grid.getXLen()-2, i, 2);
		}
		assertTrue("Horizontal win not registered!", model.setAndCheck(grid.getXLen()-1, max-1, 1));
	}

	@Test
	public void testNormalWinHorizontal() {
		testWinHorizontal(6,7,4);
	}
	
	@Test
	public void testLargeGridWinHorizontal() {
		testWinHorizontal(14,14,6);
	}

	public void testWinVertical(int xLen, int yLen, int max) {
		grid = new Grid(xLen,yLen);
		model = new Model(grid,max);
		for (int i=1; i<max; i++) {
			model.setAndCheck(grid.getXLen()-i, 0, 1);
			model.setAndCheck(grid.getXLen()-i, 1, 2);
		}
		assertTrue("Vertical win not registered!", model.setAndCheck(grid.getXLen()-max, 0, 1));
	}

	@Test
	public void testNormalWinVertical() {
		testWinVertical(6,7,4);
	}
	
	@Test
	public void testLargeGridWinVertical() {
		testWinVertical(14,14,6);
	}

	@Test
	public void testNormalWinNorthEast() {
		grid = new Grid(7,6);
		model = new Model(grid,4);
		model.setAndCheck(grid.getXLen()-1, 0, 1);
		model.setAndCheck(grid.getXLen()-1, 1, 2);
		model.setAndCheck(grid.getXLen()-2, 1, 1);
		model.setAndCheck(grid.getXLen()-1, 2, 2);
		model.setAndCheck(grid.getXLen()-2, 2, 1);
		model.setAndCheck(grid.getXLen()-1, 3, 2);
		model.setAndCheck(grid.getXLen()-3, 2, 1);
		model.setAndCheck(grid.getXLen()-2, 3, 2);
		model.setAndCheck(grid.getXLen()-3, 3, 1);
		model.setAndCheck(grid.getXLen()-1, 4, 2);
		assertTrue("North East Diagonal win not registered!", model.setAndCheck(grid.getXLen()-4, 3, 1));
	}

	@Test
	public void testNormalWinSouthEast() {
		grid = new Grid(7,6);
		model = new Model(grid,4);
		model.setAndCheck(grid.getXLen()-1, 0, 1);
		model.setAndCheck(grid.getXLen()-2, 0, 2);
		model.setAndCheck(grid.getXLen()-3, 0, 1);
		model.setAndCheck(grid.getXLen()-4, 0, 2);
		model.setAndCheck(grid.getXLen()-1, 1, 1);
		model.setAndCheck(grid.getXLen()-2, 1, 2);
		model.setAndCheck(grid.getXLen()-1, 2, 1);
		model.setAndCheck(grid.getXLen()-3, 1, 2);
		model.setAndCheck(grid.getXLen()-1, 4, 1);
		model.setAndCheck(grid.getXLen()-2, 2, 2);
		model.setAndCheck(grid.getXLen()-1, 5, 1);
		assertTrue("South East Diagonal win not registered!", model.setAndCheck(grid.getXLen()-1, 3, 2));
	}
	
	@Test
	public void testFilledColumn() {
		grid = new Grid(4,6);
		model = new Model(grid,5);
		
		for (int i=1; i<5; i++) {
			model.setAndCheck(grid.getXLen()-i, 0, 1);
			model.setAndCheck(grid.getXLen()-i, 1, 2);
		}
		assertEquals((model.getGrid()).findY(0),-1);
	}
}
