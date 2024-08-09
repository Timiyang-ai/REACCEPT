public void prunePoints(int neighbors , double distance ) {

		// Use a nearest neighbor search to find near by points
		Point3D_F64 worldX = new Point3D_F64();
		List<Point3D_F64> cloud = new ArrayList<>();
		for (int i = 0; i < structure.points.length; i++) {
			BundleAdjustmentSceneStructure.Point structureP = structure.points[i];
			structureP.get(worldX);
			cloud.add(worldX.copy());
		}

		NearestNeighbor<Point3D_F64> nn = FactoryNearestNeighbor.kdtree(new KdTreePoint3D_F64());
		nn.setPoints(cloud,false);
		FastQueue<NnData<Point3D_F64>> resultsNN = new FastQueue(NnData.class,true);

		// Create a look up table containing from old to new indexes for each point
		int oldToNew[] = new int[ structure.points.length ];
		Arrays.fill(oldToNew,-1); // crash is bug
		// List of point ID's which are to be removed.
		GrowQueue_I32 prunePointID = new GrowQueue_I32();

		// identify points which need to be pruned
		for (int pointId = 0; pointId < structure.points.length; pointId++) {
			BundleAdjustmentSceneStructure.Point structureP = structure.points[pointId];
			structureP.get(worldX);

			// distance is squared
			nn.findNearest(cloud.get(pointId),distance*distance,neighbors+1,resultsNN);

			// always finds itself
			if( resultsNN.size() > neighbors ) {
				oldToNew[pointId] = pointId-prunePointID.size;
				continue;
			}

			prunePointID.add(pointId);

			// Remove observations of this point
			for (int viewIdx = 0; viewIdx < structureP.views.size; viewIdx++) {
				BundleAdjustmentObservations.View v = observations.getView(structureP.views.get(viewIdx));

				int pointIdx = v.point.indexOf(pointId);
				v.remove(pointIdx);
			}
		}

		pruneUpdatePointID(oldToNew, prunePointID);
	}