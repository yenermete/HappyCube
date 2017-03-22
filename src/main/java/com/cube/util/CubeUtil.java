package com.cube.util;

import com.cube.constants.CubeConstants;

public class CubeUtil {

	public static final int getGreatestDigit(int x) {
		return x >> CubeConstants.TILE_LENGTH - 1;
	}

	public static final int getLowestDigit(int x) {
		return x & CubeConstants.LOW;
	}

	public static String convertEdgeToString(int x) {
		return getStringValue(x).replaceAll("0", " ").replaceAll("1", "0");
	}

	private static StringBuilder getCompletedStringBuilder(int x) {
		StringBuilder builder = new StringBuilder(Integer.toBinaryString(x));
		while (builder.length() < CubeConstants.TILE_LENGTH) {
			builder.insert(0, "0");
		}
		return builder;
	}

	public static String getStringValue(int x) {
		return getCompletedStringBuilder(x).toString();
	}

	public static final int rotateSymmetric(int x) {
		return Integer.valueOf(getCompletedStringBuilder(x).reverse().toString(), 2);
	}
}
