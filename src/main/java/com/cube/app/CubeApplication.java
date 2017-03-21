package com.cube.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.cube.model.Cube;
import com.cube.model.Surface;
import com.cube.service.CubeService;
import com.cube.service.impl.CubeServiceImpl;

public class CubeApplication {

	private CubeService service;
	private Surface rear;
	private Surface top;
	private Surface bottom;
	private Surface front;
	private Surface right;
	private Surface left;
	private final int maxSurfaceTransformations = 8;

	public CubeApplication() {
		rear = new Surface("00100", "00100", "00100", "00100");
		top = new Surface("00100", "00100", "00100", "00100");
		bottom = new Surface("00100", "00100", "00100", "00100");
		front = new Surface("00100", "00100", "00100", "00100");
		left = new Surface("00100", "00100", "00100", "00100");
		right = new Surface("00100", "00100", "00100", "00100");
		service = new CubeServiceImpl();
	}

	public Cube getValidCube() {
		Cube cube = new Cube(top, bottom, left, right, rear, front);
		if (service.isValidCube(cube)) {
			return cube;
		}

		Set<Surface> rearSet = getRotations(rear);
		Set<Surface> bottomSet = getRotations(bottom);
		Set<Surface> leftSet = getRotations(left);
		Set<Surface> rightSet = getRotations(right);
		Set<Surface> topSet = getRotations(top);
		Set<Surface> frontSet = getRotations(front);
		for (Surface rr : rearSet) {
			for (Surface tp : topSet) {
				for (Surface bot : bottomSet) {
					for (Surface frnt : frontSet) {
						for (Surface lft : leftSet) {
							for (Surface rght : rightSet) {
								cube = new Cube(tp, bot, lft, rght, rr, frnt);
								if (service.isValidCube(cube)) {
									return cube;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public void printCube(Cube cube) {
		if (cube == null) {
			System.out.println("There is no valid cube combination.");
		} else {
			System.out.println("Found valid cube. Printing to file.");
			service.printCube(cube);
		}
	}

	private Set<Surface> getRotations(Surface surface) {
		Surface[] surfaces = new Surface[maxSurfaceTransformations];
		surfaces[0] = surface;
		surfaces[1] = service.rotateSurfaceRight(surface);
		surfaces[2] = service.rotateSurfaceRight(surfaces[1]);
		surfaces[3] = service.rotateSurfaceRight(surfaces[2]);
		for (int i = 4; i < maxSurfaceTransformations; i++) {
			surfaces[i] = service.rotateSurfaceSymmetric(surfaces[i - maxSurfaceTransformations / 2]);
		}
		return new HashSet<>(Arrays.asList(surfaces));
	}

	public CubeService getService() {
		return service;
	}

	public void setService(CubeService service) {
		this.service = service;
	}

}
