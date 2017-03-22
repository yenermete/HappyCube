package com.cube.test;

import org.junit.Test;

import com.cube.model.Surface;

import junit.framework.TestCase;

public class TestSurface extends TestCase {

	@Test
	public void testValidStringToIntConversion() {
		Surface surface = new Surface("00111", "11000", "01000", "00110");
		assertEquals(7, surface.getTop());
		assertEquals(24, surface.getBottom());
		assertEquals(8, surface.getLeft());
		assertEquals(6, surface.getRight());
	}

	@Test
	public void testSurfacesEqualAndHashCodeSame() {
		Surface first = new Surface("00111", "11000", "01000", "00110");
		Surface second = new Surface(7, 24, 8, 6);
		assertTrue(first.equals(second));
		assertEquals(first.hashCode(), second.hashCode());
	}

	@Test
	public void testSurfacesNotEqualAndHashCodeDifferent() {
		Surface first = new Surface("00111", "11000", "01000", "00110");
		Surface second = new Surface(7, 24, 8, 5);
		assertFalse(first.equals(second));
		assertFalse(first.hashCode() == second.hashCode());
	}

	@Test
	public void testFlatEdgeThrowsException() {

	}

	@Test
	public void testEmptyEdgeThrowsException() {

	}

	@Test
	public void testInvalidTopRightThrowsException() {

	}

	@Test
	public void testInvalidTopLeftThrowsException() {

	}

	@Test
	public void testInvalidBottomRightThrowsException() {

	}

	@Test
	public void testInvalidBottomLeftThrowsException() {

	}

}
