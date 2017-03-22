package com.cube.model;

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

	public Surface(String top, String bottom, String left, String right) throws IllegalArgumentException {
		this.top = getStringAsInt(top);
		this.bottom = getStringAsInt(bottom);
		this.left = getStringAsInt(left);
		this.right = getStringAsInt(right);
		validateEdges();
	}

	private void validateEdges() {
		if (allowedEdge(top) && allowedEdge(bottom) && allowedEdge(left) && allowedEdge(right)) {
			if ((matchesLoneTile(top, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.HIGH)
					&& matchesLoneTile(left, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.HIGH))
					|| (matchesLoneTile(top, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.ONE)
							&& matchesLoneTile(right, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.HIGH))
					|| (matchesLoneTile(bottom, CubeConstants.TWO_TILES_IN_THE_END, CubeConstants.HIGH)
							&& matchesLoneTile(left, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.ONE))
					|| (matchesLoneTile(bottom, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.ONE)
							&& matchesLoneTile(right, CubeConstants.TWO_TILES_IN_THE_BEGINNING, CubeConstants.ONE))) {
				throw new IllegalArgumentException("Each tile on a surface must have at least one neighbour!");
			}
		} else {
			throw new IllegalArgumentException("Surfaces must have no empty or flat edge!");
		}
	}

	private boolean matchesLoneTile(int edge, int value, int result) {
		return (edge & value) == result;
	}

	private boolean allowedEdge(int edge) {
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
