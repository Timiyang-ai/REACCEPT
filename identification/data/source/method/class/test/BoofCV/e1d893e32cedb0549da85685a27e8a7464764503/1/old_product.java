void connectNodes() {
		setupSearch();

		for (int i = 0; i < nodes.size(); i++) {
			SquareNode n = nodes.get(i);

			double[] point = searchPoints.get(i);

			// distance between center when viewed head on will be space + 0.5*2*width.
			// when you factor in foreshortening this search will not be symmetric
			// the smaller will miss its larger neighbor but the larger one will find the smaller one.
			double neighborDistance = n.largestSide*(1.0+spaceToSquareRatio)*1.2;

			// find it's neighbors
			searchResults.reset();
			search.findNearest(point, neighborDistance*neighborDistance, maxNeighbors + 1, searchResults);

			// try to attach it's closest neighbors
			for (int j = 0; j < searchResults.size(); j++) {
				NnData<SquareNode> neighbor = searchResults.get(j);
				if( neighbor.data != n )
					considerConnect(n, neighbor.data);
			}
		}
	}