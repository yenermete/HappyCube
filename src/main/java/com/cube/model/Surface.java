package com.cube.model;

import static com.cube.util.CubeUtil.getGreatestDigit;
import static com.cube.util.CubeUtil.getLowestDigit;

import com.cube.constants.CubeConstants;

public class Surface {

	private final int top;
	private final int bottom;
	private final int left;
	private final int right;
	private String stringValue;

	public Surface(int top, int bottom, int left, int right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		validateEdges();
	}

	public Surface(String top, String bottom, String left, String right) {
		this.top = getStringAsInt(top);
		this.bottom = getStringAsInt(bottom);
		this.left = getStringAsInt(left);
		this.right = getStringAsInt(right);
		validateEdges();
	}

	private void validateEdges() {
		if (edgeNotFlatOrEmpty(top) && edgeNotFlatOrEmpty(bottom) && edgeNotFlatOrEmpty(left) && edgeNotFlatOrEmpty(right)) {
			if ((cornerMatchesLoneTile(top, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.HIGH)
					&& cornerMatchesLoneTile(left, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.HIGH))
					|| (cornerMatchesLoneTile(top, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.ONE)
							&& cornerMatchesLoneTile(right, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.HIGH))
					|| (cornerMatchesLoneTile(bottom, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.HIGH)
							&& cornerMatchesLoneTile(left, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.ONE))
					|| (cornerMatchesLoneTile(bottom, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.ONE)
							&& cornerMatchesLoneTile(right, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.ONE))) {
				throw new IllegalArgumentException("Each tile on a surface must have at least one neighbour!");
			} else {
				if (!edgesMatch()) {
					throw new IllegalArgumentException("Surface edges must be consistent with neighbours!");
				}
			}
		} else {
			throw new IllegalArgumentException("Surfaces must have no empty or flat edge!");
		}
	}

	private boolean edgesMatch() {
		// Highest digit of one edge must be equal to the lowest digit of an
		// adjacent edge
		if (getGreatestDigit(bottom) != getLowestDigit(left) || getLowestDigit(bottom) != getLowestDigit(right)
				|| getLowestDigit(top) != getGreatestDigit(right) || getGreatestDigit(top) != getGreatestDigit(left)) {
			return false;
		}
		return true;
	}

	private boolean cornerMatchesLoneTile(int edge, int value, int result) {
		return (edge & value) == result;
	}

	private boolean edgeNotFlatOrEmpty(int edge) {
		return edge != CubeConstants.FLAT_EDGE && edge != CubeConstants.EMPTY_EDGE;
	}

	private int getStringAsInt(String edge) {
		if (edge == null || edge.trim().length() != CubeConstants.TILE_LENGTH) {
			throw new IllegalArgumentException(String.format("%s must be not null and be five characters long!", edge));
		}
		int result = 0;
		char c;
		for (int i = 0; i < edge.toCharArray().length; i++) {
			c = edge.toCharArray()[i];
			if (c == '0' || c == '1') {
				result += Character.getNumericValue(c) << (edge.toCharArray().length - 1 - i);
			} else {
				throw new IllegalArgumentException(String.format("%s contains a non binary character %s", edge, c));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		if (stringValue == null) {
			stringValue = new StringBuilder().append("top: ").append(top).append(", bottom: ").append(bottom)
					.append(", left: ").append(left).append(", right: ").append(right).toString();
		}
		return stringValue;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		Surface otherSurface = (Surface) other;
		if (otherSurface.getTop() == top && otherSurface.getBottom() == bottom && otherSurface.getLeft() == left
				&& otherSurface.getRight() == right) {
			return true;
		}
		return false;

	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + top;
		result = 31 * result + bottom;
		result = 31 * result + left;
		result = 31 * result + right;
		return result;
	}

	public int getTop() {
		return top;
	}

	public int getBottom() {
		return bottom;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

}
