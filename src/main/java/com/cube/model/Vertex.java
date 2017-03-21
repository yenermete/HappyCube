package com.cube.model;

public class Vertex {

	private final int x;
	private final int y;
	private final int z;
	private String stringValue;

	public Vertex(int x, int y, int z) {
		checkValue(x);
		checkValue(y);
		checkValue(z);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	private void checkValue(int a) {
		if (a == 0 || a == 1) {
			return;
		}
		throw new IllegalArgumentException(String.format("%d is not a valid value!", a));
	}

	@Override
	public String toString() {
		if (stringValue == null) {
			stringValue = new StringBuilder().append("x: ").append(x).append(", y: ").append(y).append(", z: ")
					.append(z).toString();
		}
		return stringValue;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

}
