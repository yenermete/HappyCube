package com.cube.service.impl;

import com.cube.model.Cube;
import com.cube.model.Surface;
import com.cube.model.Vertex;
import com.cube.service.CubeService;
import com.cube.util.CubeUtil;

import static com.cube.constants.CubeConstants.MIDDLE_ALL_ONES;
import static com.cube.constants.CubeConstants.NOT_NULL;
import static com.cube.util.CubeUtil.getGreatestDigit;
import static com.cube.util.CubeUtil.getLowestDigit;

public class CubeServiceImpl implements CubeService {

	@Override
	public boolean isValidCube(Cube cube) {
		if (cube == null) {
			throw new IllegalArgumentException(String.format(NOT_NULL, "Cube"));
		}
		// Only one one at each of the eight vertices
		for (Vertex vertext : cube.getVertices()) {
			if (!isVertexMatching(vertext)) {
				return false;
			}
		}
		// All six surfaces must be valid
		if (!isValidSurface(cube.getBottom()) || !isValidSurface(cube.getFront()) || !isValidSurface(cube.getLeft())
				|| !isValidSurface(cube.getRear()) || !isValidSurface(cube.getRear())
				|| !isValidSurface(cube.getTop())) {
			return false;
		}
		if (!edgesMatchInTheMiddle(cube.getRear().getBottom(), cube.getBottom().getTop())
				|| !edgesMatchInTheMiddle(cube.getRear().getLeft(), cube.getLeft().getTop())
				|| !edgesMatchInTheMiddle(cube.getRear().getTop(), cube.getTop().getBottom())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getRear().getRight()), cube.getRight().getTop())
				|| !edgesMatchInTheMiddle(cube.getBottom().getLeft(), cube.getLeft().getRight())
				|| !edgesMatchInTheMiddle(cube.getBottom().getRight(), cube.getRight().getLeft())
				|| !edgesMatchInTheMiddle(cube.getBottom().getBottom(), cube.getFront().getTop())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getFront().getLeft()), cube.getLeft().getBottom())
				|| !edgesMatchInTheMiddle(cube.getFront().getBottom(), cube.getTop().getTop())
				|| !edgesMatchInTheMiddle(cube.getFront().getRight(), cube.getRight().getBottom())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getTop().getLeft()), cube.getLeft().getLeft())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getTop().getRight()), cube.getRight().getRight())) {
			return false;
		}
		return true;
	}

	private boolean edgesMatchInTheMiddle(int edge1, int edge2) {
		// Highest digit ignored, and with 1110 to ignore lowest digit
		return ((edge1 ^ edge2) & MIDDLE_ALL_ONES) == MIDDLE_ALL_ONES;
	}

	@Override
	public boolean isValidSurface(Surface surface) {
		// Highest digit of one edge must be equal to the lowest digit of an
		// adjacent edge
		if (getGreatestDigit(surface.getBottom()) != getLowestDigit(surface.getLeft())
				|| getLowestDigit(surface.getBottom()) != getLowestDigit(surface.getRight())
				|| getLowestDigit(surface.getTop()) != getGreatestDigit(surface.getRight())
				|| getGreatestDigit(surface.getTop()) != getGreatestDigit(surface.getLeft())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isVertexMatching(Vertex vertext) {
		return vertext.getX() + vertext.getY() + vertext.getZ() == 1;
	}

	@Override
	public void printCube(Cube cube) {
		// TODO Auto-generated method stub

	}


	@Override
	public Surface rotateSurfaceRight(Surface surface) {
		if (surface == null) {
			throw new IllegalArgumentException(String.format(NOT_NULL, "Surface"));
		}
		return new Surface(surface.getLeft(), surface.getRight(), surface.getBottom(), surface.getTop());
	}

	@Override
	public Surface rotateSurfaceSymmetric(Surface surface) {
		if (surface == null) {
			throw new IllegalArgumentException(String.format(NOT_NULL, "Surface"));
		}
		return new Surface(CubeUtil.rotateSymmetric(surface.getTop()), CubeUtil.rotateSymmetric(surface.getBottom()),
				surface.getRight(), surface.getLeft());
	}

}
