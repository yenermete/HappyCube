package com.cube.service;

import com.cube.model.Cube;
import com.cube.model.Surface;
import com.cube.model.Vertex;

public interface CubeService {

	public boolean isValidCube(Cube cube);

	public boolean isVertexMatching(Vertex vertice);

	public void printCube(Cube cube, String fileName);

	public Surface rotateSurfaceRight(Surface surface);

	public Surface rotateSurfaceSymmetric(Surface surface);
}
