void sortEdgesCCW(FastQueue<Node> corners) {
		for (int nodeIdx = 0; nodeIdx < corners.size; nodeIdx++) {
			Node na = corners.get(nodeIdx);

			int count = 0;
			for (int i = 0; i < 4; i++) {
				order[i] = i;
				tmpEdges[i] = na.edges[i];
				if( na.edges[i] == null ) {
					directions[i] = Double.MAX_VALUE;
				} else {
					count++;
					Node nb = na.edges[i];
					directions[i] = Math.atan2(nb.y-na.y,nb.x-na.x);
				}
			}

			sorter.sort(directions,0,4,order);
			for (int i = 0; i < 4; i++) {
				na.edges[i] = tmpEdges[ order[i] ];
			}
			// Edges need to point along the 4 possible directions, in the case of 3 edges, there might
			// need to be a gap at a different location than at the end
			if( count == 3 ) {
				double tail = UtilAngle.distanceCCW(directions[order[2]],directions[order[0]]);
				for (int i = 1; i <3; i++) {
					double ccw = UtilAngle.distanceCCW(directions[order[i-1]],directions[order[i]]);
					if( tail < ccw ) {
						for (int j = 3; j >= i+1; j--) {
							na.edges[j] = na.edges[j-1];
						}
						na.edges[i] = null;
						break;
					}
				}
			}
		}
	}