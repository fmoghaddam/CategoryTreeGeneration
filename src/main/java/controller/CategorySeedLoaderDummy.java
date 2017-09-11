package controller;

import java.util.Arrays;
import java.util.List;

public class CategorySeedLoaderDummy extends CategorySeedloader {

	public CategorySeedLoaderDummy() {

	}

	@Override
	public void loadSeeds() {
		final List<String> dummySeeds = Arrays.asList("Chief_executive_officers", "Heads_of_state",
				"Chancellors_(government)", "Popes", "Monarchy");
		for (String s : dummySeeds) {
			getSeeds().add(s);
		}
	}
}
