package com.cube.test;

import org.junit.Test;

import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

import com.cube.model.Surface;

public class TestSurface extends TestCase {

	@Test
	public void testValidStringToIntConversion() {
		Surface surface = new Surface("00111", "11000", "01001", "10110");
		;
		assertEquals(7, surface.getTop());
		assertEquals(24, surface.getBottom());
		assertEquals(9, surface.getLeft());
		assertEquals(22, surface.getRight());
	}

	@Test
	public void testSurfacesEqualAndHashCodeSame() {
		Surface first = new Surface("00111", "11000", "01001", "10110");
		Surface second = new Surface(7, 24, 9, 22);
		assertTrue(first.equals(second));
		assertEquals(first.hashCode(), second.hashCode());
	}

	@Test
	public void testSurfacesNotEqualAndHashCodeDifferent() {
		Surface first = new Surface("00111", "11000", "01001", "10110");
		Surface second = new Surface(7, 24, 9, 18);
		assertFalse(first.equals(second));
		assertFalse(first.hashCode() == second.hashCode());
	}

	@Test
	public void testFlatEdgeThrowsException() {
		try {
			new Surface(31, 2, 2, 2);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Surfaces must have no empty or flat edge!"));
		}
	}

	@Test
	public void testEmptyEdgeThrowsException() {
		try {
			new Surface(10, 0, 2, 2);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Surfaces must have no empty or flat edge!"));
		}
	}

	@Test
	public void testInvalidTopRightThrowsException() {
		try {
			new Surface(8, 15, 2, 25);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Surface edges must be consistent with neighbours!"));
		}
	}

	@Test
	public void testInvalidTopLeftThrowsException() {
		try {
			new Surface(24, 15, 2, 3);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Surface edges must be consistent with neighbours!"));
		}
	}

	@Test
	public void testInvalidBottomRightThrowsException() {
		try {
			new Surface(10, 15, 2, 2);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Surface edges must be consistent with neighbours!"));
		}
	}

	@Test
	public void testInvalidBottomLeftThrowsException() {
		try {
			new Surface(10, 15, 3, 3);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Surface edges must be consistent with neighbours!"));
		}
	}

	@Test
	public void testInvalidSurfaceTopLeftNoNeighbour() {
		try {
			new Surface("10010", "00011", "10000", "00011");
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Each tile on a surface must have at least one neighbour!"));
		}
	}

	@Test
	public void testInvalidSurfaceTopRightNoNeighbour() {
		try {
			new Surface("10001", "00011", "11000", "10011");
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Each tile on a surface must have at least one neighbour!"));
		}
	}

	@Test
	public void testInvalidSurfaceBottomRightNoNeighbour() {
		try {
			new Surface("11010", "10001", "10011", "00001");
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Each tile on a surface must have at least one neighbour!"));
		}
	}

	@Test
	public void testInvalidSurfaceBottomLeftNoNeighbour() {
		try {
			new Surface("11010", "10011", "10001", "00011");
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("Each tile on a surface must have at least one neighbour!"));
		}
	}
}
