public static PointRDD SpatialRangeQueryUsingIndex(PointRDD pointRDD, Envelope envelope, Integer condition) {
        if(pointRDD.indexedRDDNoId == null) {
            throw new NullPointerException("Need to invoke buildIndex() first, indexedRDDNoId is null");
        }
		JavaRDD<Point> result = pointRDD.indexedRDDNoId.mapPartitions(new PointRangeFilterUsingIndex(envelope));
		return new PointRDD(result);
	}