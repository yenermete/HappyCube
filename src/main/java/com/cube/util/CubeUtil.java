package com.cube.util;

import com.cube.constants.CubeConstants;

public class CubeUtil {

	public static final int getGreatestDigit(int x) {
		return x >> CubeConstants.TILE_LENGTH - 1;
	}

	public static final int getLowestDigit(int x) {
		return x & CubeConstants.LOW;
	}

	public static final int rotateSymmetric(int x) {
		StringBuilder builder = new StringBuilder(Integer.toBinaryString(x));
		while(builder.length() < CubeConstants.TILE_LENGTH) {
			builder.insert(0, "0");
		}
		return Integer.valueOf(builder.reverse().toString(), 2);
	}
}
