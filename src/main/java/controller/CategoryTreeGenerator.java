package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import model.CategoryTree;

public class CategoryTreeGenerator {
	
	private final List<CategoryTree> trees = new ArrayList<>();
	
	public CategoryTreeGenerator(final List<String> seeds,final int treeDepth) {
		for(String seed:seeds) {
			trees.add(generateTree(seed,treeDepth));
		}
	}

	private CategoryTree generateTree(String seed, int treeDepth) {
		final CategoryTree catTree = new CategoryTree(seed, treeDepth);
		final Queue<String> queue = new LinkedBlockingQueue<>();
		queue.add(seed);
		
		int currentDepth = 0,
			      elementsToDepthIncrease = 1, 
			      nextElementsToDepthIncrease = 0;


		while(!queue.isEmpty()) {
			final String pop = queue.poll();
			catTree.addSubClass(pop,currentDepth);
			final Set<String> set = FastLookUpSubjectObject.getFastlookUpSubjectObjects().get(pop);
			if(set==null) {
				continue;
			}
			nextElementsToDepthIncrease += set.size();
		    if (--elementsToDepthIncrease == 0) {
		      if (++currentDepth > treeDepth) {
		    	  break;
		      }
		      elementsToDepthIncrease = nextElementsToDepthIncrease;
		      nextElementsToDepthIncrease = 0;
		    }
			queue.addAll(set);
		}
		return catTree;
	}
	
	public void printTreesToFile() {
		for(CategoryTree ct:trees) {
			ct.writeToFile();
		}
	}
}
