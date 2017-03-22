package com.cube.model;

import java.util.Arrays;

import com.cube.util.CubeUtil;

public class Cube {

	private final Surface top;
	private final Surface bottom;
	private final Surface left;
	private final Surface right;
	private final Surface rear;
	private final Surface front;
	private String stringValue;

	private final Vertex[] vertices;

	/**
	 * A cube will e represented like below
	 * 		REAR
	 * LEFT BOTTOM RIGHT
	 * 		FRONT
	 * 		TOP
	 */
	public Cube(Surface top, Surface bottom, Surface left, Surface right, Surface rear, Surface front) {
		this.top = top;
		this.bottom = bottom;
		this.rear = rear;
		this.front = front;
		this.left = left;
		this.right = right;
		vertices = calculateVertices();
	}

	private Vertex[] calculateVertices() {
		Vertex[] result = new Vertex[8];
		result[0] = new Vertex(CubeUtil.getGreatestDigit(bottom.getTop()), CubeUtil.getLowestDigit(left.getTop()),
				CubeUtil.getGreatestDigit(rear.getBottom()));
		result[1] = new Vertex(CubeUtil.getLowestDigit(bottom.getTop()), CubeUtil.getLowestDigit(rear.getBottom()),
				CubeUtil.getGreatestDigit(right.getTop()));
		result[2] = new Vertex(CubeUtil.getGreatestDigit(bottom.getBottom()), CubeUtil.getGreatestDigit(front.getTop()),
				CubeUtil.getLowestDigit(left.getBottom()));
		result[3] = new Vertex(CubeUtil.getLowestDigit(bottom.getBottom()),
				CubeUtil.getGreatestDigit(right.getBottom()), CubeUtil.getLowestDigit(front.getTop()));

		result[4] = new Vertex(CubeUtil.getGreatestDigit(front.getBottom()),
				CubeUtil.getGreatestDigit(left.getBottom()), CubeUtil.getGreatestDigit(top.getTop()));
		result[5] = new Vertex(CubeUtil.getLowestDigit(front.getBottom()), CubeUtil.getLowestDigit(top.getTop()),
				CubeUtil.getLowestDigit(right.getBottom()));
		result[6] = new Vertex(CubeUtil.getGreatestDigit(top.getBottom()), CubeUtil.getGreatestDigit(left.getTop()),
				CubeUtil.getGreatestDigit(rear.getTop()));
		result[7] = new Vertex(CubeUtil.getLowestDigit(top.getBottom()), CubeUtil.getLowestDigit(rear.getTop()),
				CubeUtil.getLowestDigit(right.getTop()));
		return result;
	}

	@Override
	public String toString() {
		if (stringValue == null) {
			StringBuilder builder = new StringBuilder();
			builder.append("TOP_SURFACE: ").append(top).append(System.lineSeparator()).append("BOTTOM_SURFACE: ")
					.append(bottom).append(System.lineSeparator()).append("LEFT_SURFACE: ").append(left)
					.append(System.lineSeparator()).append("RIGHT_SURFACE: ").append(right)
					.append(System.lineSeparator()).append("REAR_SURFACE: ").append(rear).append(System.lineSeparator())
					.append("FRONT_SURFACE: ").append(front).append(System.lineSeparator()).append("VERTICES: ")
					.append(System.lineSeparator());
			Arrays.stream(vertices).forEach(v -> builder.append("vertice: ").append(v).append("."));
			stringValue = builder.toString();
		}
		return stringValue;
	}

	public Surface getTop() {
		return top;
	}

	public Surface getBottom() {
		return bottom;
	}

	public Surface getLeft() {
		return left;
	}

	public Surface getRight() {
		return right;
	}

	public Surface getRear() {
		return rear;
	}

	public Surface getFront() {
		return front;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

}
