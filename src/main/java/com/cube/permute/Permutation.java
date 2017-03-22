package com.cube.permute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cube.model.Cube;
import com.cube.model.Surface;

public class Permutation {

	public List<Cube> cubeList = new ArrayList<>();

	public void permute(List<Surface> surfaces, int k) {
		for (int i = k; i < surfaces.size(); i++) {
			Collections.swap(surfaces, i, k);
			permute(surfaces, k + 1);
			Collections.swap(surfaces, k, i);
		}
		if (k == surfaces.size() - 1) {
			cubeList.add(new Cube(surfaces.get(0), surfaces.get(1), surfaces.get(2), surfaces.get(03), surfaces.get(4),
					surfaces.get(5)));
		}
	}

	public List<Cube> getCubeList() {
		return cubeList;
	}

}