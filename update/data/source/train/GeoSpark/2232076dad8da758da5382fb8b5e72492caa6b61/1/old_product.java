public static List<Point> SpatialKnnQuery(PointRDD spatialRDD, Point queryCenter, Integer k, boolean useIndex) {
		// For each partation, build a priority queue that holds the topk
		//@SuppressWarnings("serial")
		if(useIndex)
		{
	        if(spatialRDD.indexedRawRDD == null) {
	            throw new NullPointerException("Need to invoke buildIndex() first, indexedRDDNoId is null");
	        }
			JavaRDD<Object> tmp = spatialRDD.indexedRawRDD.mapPartitions(new KnnJudgementUsingIndex(queryCenter,k));
			List<Object> result = tmp.takeOrdered(k, new GeometryDistanceComparator(queryCenter,true));
			List<Point> geometryResult = new ArrayList<Point>();
			for(Object spatialObject:result)
			{
				geometryResult.add((Point)spatialObject);
			}
			// Take the top k
			return geometryResult;
		}
		else
		{
			JavaRDD<Object> tmp = spatialRDD.getRawSpatialRDD().mapPartitions(new GeometryKnnJudgement(queryCenter,k));
			List<Object> result = tmp.takeOrdered(k, new GeometryDistanceComparator(queryCenter,true));
			List<Point> geometryResult = new ArrayList<Point>();
			for(Object spatialObject:result)
			{
				geometryResult.add((Point)spatialObject);
			}
			// Take the top k
			return geometryResult;
		}
	}