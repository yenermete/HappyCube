package com.cube;

import com.cube.app.CubeApplication;
import com.cube.model.Cube;

public class Application {

	public static void main(String[] args) {
		System.out.println(5 & 1);
		System.out.println(5 & 2);
		System.out.println(5 & 3);
		if (args == null || args.length != 1) {
			System.out.println(
					"Please provide the folder where the cube will be printed to. Sample usage java -jar <jar_file_name_without_underscores> <absolute_or_relative_folder_name>");
			return;
		}
		findAndPrintCube(args[0]);
	}

	private static void findAndPrintCube(String folderName) {
		CubeApplication app = new CubeApplication();
		Cube cube = app.getValidCube();
		app.printCube(cube, folderName);
	}
}
