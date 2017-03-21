package com.cube;

import com.cube.app.CubeApplication;
import com.cube.model.Cube;

public class Application {

	public static void main(String[] args) {
		findAndPrintCube();
	}

	private static void findAndPrintCube() {
		CubeApplication app = new CubeApplication();
		Cube cube = app.getValidCube();
		app.printCube(cube);
	}
}
