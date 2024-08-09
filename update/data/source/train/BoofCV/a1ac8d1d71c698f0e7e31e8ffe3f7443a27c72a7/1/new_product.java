public void prunePoints(int count ) {
		// Remove all observations of the Points which are going to be removed
		for (int viewIndex = observations.views.length-1; viewIndex >= 0; viewIndex--) {
			SceneObservations.View v = observations.views[viewIndex];

			for(int pointIndex = v.point.size-1; pointIndex >= 0; pointIndex-- ) {
				SceneStructureMetric.Point p = structure.points.data[v.getPointId(pointIndex)];

				if( p.views.size < count ) {
					v.remove(pointIndex);
				}
			}
		}

		// Create a look up table containing from old to new indexes for each point
		int[] oldToNew = new int[ structure.points.size ];
		Arrays.fill(oldToNew,-1); // crash is bug

		GrowQueue_I32 prune = new GrowQueue_I32(); // List of point ID's which are to be removed.
		for (int i = 0; i < structure.points.size; i++) {
			if( structure.points.data[i].views.size < count ) {
				prune.add(i);
			} else {
				oldToNew[i] = i-prune.size;
			}

		}
		pruneUpdatePointID(oldToNew, prune);
	}