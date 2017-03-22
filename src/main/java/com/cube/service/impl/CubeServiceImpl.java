package com.cube.service.impl;

import com.cube.model.Cube;
import com.cube.model.Surface;
import com.cube.model.Vertex;
import com.cube.service.CubeService;
import com.cube.util.CubeUtil;

import static com.cube.constants.CubeConstants.MIDDLE_ALL_ONES;
import static com.cube.constants.CubeConstants.NOT_NULL;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import com.cube.constants.CubeConstants;

public class CubeServiceImpl implements CubeService {

	private final String emptyPrefix = String.join("", Collections.nCopies(CubeConstants.TILE_LENGTH, " "));
	private final String midZeros = String.join("", Collections.nCopies(CubeConstants.TILE_LENGTH - 2, "0"));

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
		if (!edgesMatchInTheMiddle(cube.getRear().getBottom(), cube.getBottom().getTop())
				|| !edgesMatchInTheMiddle(cube.getRear().getLeft(), cube.getLeft().getTop())
				|| !edgesMatchInTheMiddle(cube.getRear().getTop(), cube.getTop().getBottom())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getRear().getRight()), cube.getRight().getTop())
				|| !edgesMatchInTheMiddle(cube.getBottom().getLeft(), cube.getLeft().getRight())
				|| !edgesMatchInTheMiddle(cube.getBottom().getRight(), cube.getRight().getLeft())
				|| !edgesMatchInTheMiddle(cube.getBottom().getBottom(), cube.getFront().getTop())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getFront().getLeft()),
						cube.getLeft().getBottom())
				|| !edgesMatchInTheMiddle(cube.getFront().getBottom(), cube.getTop().getTop())
				|| !edgesMatchInTheMiddle(cube.getFront().getRight(), cube.getRight().getBottom())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getTop().getLeft()), cube.getLeft().getLeft())
				|| !edgesMatchInTheMiddle(CubeUtil.rotateSymmetric(cube.getTop().getRight()),
						cube.getRight().getRight())) {
			return false;
		}
		return true;
	}

	private boolean edgesMatchInTheMiddle(int edge1, int edge2) {
		// Highest digit ignored, and with 1110 to ignore lowest digit
		return ((edge1 ^ edge2) & MIDDLE_ALL_ONES) == MIDDLE_ALL_ONES;
	}

	@Override
	public boolean isVertexMatching(Vertex vertex) {
		return vertex.getX() + vertex.getY() + vertex.getZ() == 1;
	}

	@Override
	public void printCube(Cube cube, String fileName) {
		if (cube == null) {
			throw new IllegalArgumentException(String.format(CubeConstants.NOT_NULL, "cube"));
		}
		String[] rowsLeft = getPrintableSurface(cube.getLeft());
		String[] rowsRight = getPrintableSurface(cube.getRight());
		String[] rowsBottom = getPrintableSurface(cube.getBottom());
		String[] rowsRest = new String[3 * CubeConstants.TILE_LENGTH];
		int index = 0;
		for (String row : getPrintableSurface(cube.getFront())) {
			rowsRest[index++] = row;
		}
		for (String row : getPrintableSurface(cube.getTop())) {
			rowsRest[index++] = row;
		}
		for (String row : getPrintableSurface(cube.getRear())) {
			rowsRest[index++] = row;
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			for (int i = 0; i < CubeConstants.TILE_LENGTH; i++) {
				writer.write(rowsLeft[i]);
				writer.write(rowsBottom[i]);
				writer.write(rowsRight[i]);
				writer.newLine();
			}
			for (String row : rowsRest) {
				writer.write(emptyPrefix);
				writer.write(row);
				writer.newLine();
			}

		} catch (IOException e) {
			throw new RuntimeException("Cube could not be written to file!", e);
		}
	}

	private String[] getPrintableSurface(Surface surface) {
		// A lot of conversions exist here because solution is required with
		// zeros and space instead of ones and zeros
		String[] result = new String[CubeConstants.TILE_LENGTH];
		result[0] = CubeUtil.convertEdgeToStringForPrinting(surface.getTop());
		result[CubeConstants.TILE_LENGTH - 1] = CubeUtil.convertEdgeToStringForPrinting(surface.getBottom());
		for (int i = 1; i < CubeConstants.TILE_LENGTH - 1; i++) {
			result[i] = new StringBuilder().append(getInteriorTileValue(surface.getLeft(), i)).append(midZeros)
					.append(getInteriorTileValue(surface.getRight(), i)).toString();
		}
		return result;
	}

	private String getInteriorTileValue(int edge, int shiftLength) {
		return ((edge << shiftLength) & CubeConstants.HIGH) == CubeConstants.HIGH ? CubeConstants.TILE_FULL
				: CubeConstants.TILE_EMPTY;
	}

	@Override
	public Surface rotateSurfaceRight(Surface surface) {
		if (surface == null) {
			throw new IllegalArgumentException(String.format(NOT_NULL, "Surface"));
		}
		return new Surface(CubeUtil.rotateSymmetric(surface.getLeft()), CubeUtil.rotateSymmetric(surface.getRight()),
				surface.getBottom(), surface.getTop());
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
