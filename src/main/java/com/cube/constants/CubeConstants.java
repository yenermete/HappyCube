package com.cube.constants;

public class CubeConstants {

	public static final int ONE = 1;
	public static final int TWO_TILES_IN_THE_END = 3;
	public static final int TILE_LENGTH = 5;
	public static final int HIGH = ONE << TILE_LENGTH - 1;
	public static final int TWO_TILES_IN_THE_BEGINNING = HIGH + (ONE << TILE_LENGTH - 2);
	public static final int MIDDLE_ALL_ONES = (int) (Math.pow(2, TILE_LENGTH - 1) - 2);
	public static final int LOW = ONE;
	public static final int FLAT_EDGE = (int) (Math.pow(2, TILE_LENGTH) - 1);
	public static final int EMPTY_EDGE = 0;
	public static final String TILE_FULL = "0";
	public static final String TILE_EMPTY = " ";
	public static final String NOT_NULL = "%s can not be null!";
}
