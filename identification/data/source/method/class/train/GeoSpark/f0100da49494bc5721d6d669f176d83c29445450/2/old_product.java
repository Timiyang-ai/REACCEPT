public static List<Point> SpatialKnnQueryUsingIndex(PointRDD pointRDD, Point queryCenter, Integer k) {
		// For each partation, build a priority queue that holds the topk
		//@SuppressWarnings("serial")

        if(pointRDD.indexedRDDNoId == null) {
            throw new NullPointerException("Need to invoke buildIndex() first, indexedRDDNoId is null");
        }
		JavaRDD<Point> tmp = pointRDD.indexedRDDNoId.mapPartitions(new PointKnnJudgementUsingIndex(queryCenter,k));

		// Take the top k

		return tmp.takeOrdered(k, new PointDistanceComparator(queryCenter));

	}