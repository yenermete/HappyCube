package com.cube.test;

import org.junit.Test;

import com.cube.model.Vertex;

import junit.framework.TestCase;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

public class TestVertex extends TestCase {

	@Test
	public void testVertexThrowsExceptionOnNegativeInput() {
		try {
			new Vertex(-1, 1, 0);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("-1 is not a valid value!"));
		}
	}

	@Test
	public void testVertexThrowsExceptionOnNonBinaryInput() {
		try {
			new Vertex(0, 0, 3);
			fail("Expected IllegalArgumentException!");
		} catch (IllegalArgumentException e) {
			assertThat(e, isA(IllegalArgumentException.class));
			assertThat(e.getMessage(), equalTo("3 is not a valid value!"));
		}
	}

	@Test
	public void testVertexThrowsNoExceptionOnBinaryInput() {
		assertEquals(1, new Vertex(0, 0, 1).getZ());
	}

}
