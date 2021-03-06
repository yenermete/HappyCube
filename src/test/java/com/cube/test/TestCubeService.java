package com.cube.test;

import org.junit.Before;
import org.junit.Test;

import com.cube.model.Cube;
import com.cube.model.Surface;
import com.cube.model.Vertex;
import com.cube.service.CubeService;
import com.cube.service.impl.CubeServiceImpl;

import junit.framework.TestCase;

public class TestCubeService extends TestCase {

	private CubeService service;

	@Before
	public void setUp() {
		service = new CubeServiceImpl();
	}

	@Test
	public void testCubeIsValid() {
		Surface rear = new Surface(10, 26, 11, 4);
		Surface front = new Surface(20, 10, 26, 10);
		Surface left = new Surface(4, 4, 4, 4);
		Surface right = new Surface(10, 4, 4, 4);
		Surface bottom = new Surface(5, 11, 10, 27);
		Surface top = new Surface(21, 21, 27, 27);
		assertTrue(service.isValidCube(new Cube(top, bottom, left, right, rear, front)));
	}
	
	@Test
	public void testCubeIsInvalidWithoutRotation() {
		Surface rear = new Surface(10, 26, 11, 4);
		Surface front = new Surface(20, 10, 26, 10);
		Surface right = new Surface(4, 4, 4, 4);
		Surface left = new Surface(10, 4, 4, 4);
		Surface bottom = new Surface(5, 11, 10, 27);
		Surface top = new Surface(21, 21, 27, 27);
		assertFalse(service.isValidCube(new Cube(top, bottom, left, right, rear, front)));
	}

	@Test
	public void testCubeIsInvalidOnVerticesInvalidEdgesValid() {
		Surface rear = new Surface(10, 26, 11, 4);
		Surface front = new Surface(20, 10, 26, 10);
		Surface left = new Surface(28, 4, 20, 4); // Forms Invalid Vertex
		Surface right = new Surface(10, 4, 4, 4);
		Surface bottom = new Surface(5, 11, 10, 27);
		Surface top = new Surface(21, 21, 27, 27);
		assertFalse(service.isValidCube(new Cube(top, bottom, left, right, rear, front)));
	}

	@Test
	public void testCubeIsInvalidOnVerticesValidEdgesInvalid() {
		Surface rear = new Surface(10, 26, 11, 4);
		Surface front = new Surface(20, 10, 26, 10);
		Surface left = new Surface(14, 4, 4, 4); // Edge doesn't match
		Surface right = new Surface(10, 4, 4, 4);
		Surface bottom = new Surface(5, 11, 10, 27);
		Surface top = new Surface(21, 21, 27, 27);
		assertFalse(service.isValidCube(new Cube(top, bottom, left, right, rear, front)));
	}

	@Test
	public void testVertexMatches() {
		Vertex vertice = new Vertex(0, 1, 0);
		assertTrue(service.isVertexMatching(vertice));
	}

	@Test
	public void testVertexNoMatchAllZero() {
		Vertex vertice = new Vertex(0, 0, 0);
		assertFalse(service.isVertexMatching(vertice));
	}

	@Test
	public void testVertexNoMatchAllOne() {
		Vertex vertice = new Vertex(1, 1, 1);
		assertFalse(service.isVertexMatching(vertice));
	}

	@Test
	public void testVertexNoMatchOneZero() {
		Vertex vertice = new Vertex(1, 1, 0);
		assertFalse(service.isVertexMatching(vertice));
	}

	public void testRotateSurfaceRight() {
		Surface original = new Surface(2, 3, 8, 7);
		Surface rotated = new Surface(2, 28, 3, 2);
		assertEquals(rotated, service.rotateSurfaceRight(original));
	}

	public void testRotateSurfaceSymmetric() {
		Surface original = new Surface(2, 3, 8, 7);
		Surface rotated = new Surface(8, 24, 7, 8);
		assertEquals(rotated, service.rotateSurfaceSymmetric(original));
	}

}
