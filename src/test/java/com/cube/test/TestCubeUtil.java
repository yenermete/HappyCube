package com.cube.test;

import org.junit.Test;

import com.cube.constants.CubeConstants;
import com.cube.util.CubeUtil;

import junit.framework.TestCase;

public class TestCubeUtil extends TestCase {

	@Test
	public void testGreatestDigitWithFiveDigits() {
		assertEquals(1, CubeUtil.getGreatestDigit(16));
	}

	@Test
	public void testGreatestDigitWithFourDigits() {
		assertEquals(0, CubeUtil.getGreatestDigit(15));
	}

	@Test
	public void testGreatestDigitWithThreeDigits() {
		assertEquals(0, CubeUtil.getGreatestDigit(4));
	}

	@Test
	public void testGreatestDigitWithTwoDigits() {
		assertEquals(0, CubeUtil.getGreatestDigit(3));
	}

	@Test
	public void testGreatestDigitWithOneDigit() {
		assertEquals(0, CubeUtil.getGreatestDigit(1));
	}

	@Test
	public void testLowestWithEndIntZero() {
		assertEquals(0, CubeUtil.getLowestDigit(10));
	}

	@Test
	public void testLowestWithEndIntOne() {
		assertEquals(1, CubeUtil.getLowestDigit(3));
	}

	@Test
	public void testRotateSymmetricResultSame() {
		assertEquals(10, CubeUtil.rotateSymmetric(10));
	}

	@Test
	public void testRotateEight() {
		assertEquals(2, CubeUtil.rotateSymmetric(8));
	}

	@Test
	public void testRotateTwo() {
		assertEquals(8, CubeUtil.rotateSymmetric(2));
	}

	@Test
	public void testRotateSymmetricResultDifferentGreaterNumber() {
		assertEquals(7, CubeUtil.rotateSymmetric(28));
	}

	@Test
	public void testRotateSymmetricResultDifferentSmallerNumber() {
		assertEquals(28, CubeUtil.rotateSymmetric(7));
	}

	@Test
	public void testRotateSymmetricTwiceEqualsOriginal() {
		int x = (int) (Math.random() * CubeConstants.FLAT_EDGE);
		assertEquals(x, CubeUtil.rotateSymmetric(CubeUtil.rotateSymmetric(x)));
	}

	@Test
	public void testConvertEdgeToStringPrintingAllOnes() {
		assertEquals("00000", CubeUtil.convertEdgeToStringForPrinting(31));
	}

	@Test
	public void testConvertEdgeToStringPrintingAllZeros() {
		assertEquals("     ", CubeUtil.convertEdgeToStringForPrinting(0));
	}

	@Test
	public void testConvertEdgeToStringPrintingZerosAndOnesMixed() {
		assertEquals(" 0000", CubeUtil.convertEdgeToStringForPrinting(15));
	}

	@Test
	public void testGetStringValuePrefixesZero() {
		assertEquals("01001", CubeUtil.getStringValue(9));
	}

	@Test
	public void testGetStringValueRemainsTheSame() {
		assertEquals("11001", CubeUtil.getStringValue(25));
	}
}
