public void process( List<List<SquareNode>> clusters ) {
		for (int i = 0; i < clusters.size(); i++) {
			processCluster(clusters.get(i));
		}
	}