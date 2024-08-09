public static PointRDD SpatialRangeQuery(PointRDD pointRDD, Envelope envelope, Integer condition) {
		JavaRDD<Point> result = pointRDD.getRawPointRDD().filter(new PointRangeFilter(envelope, condition));
		return new PointRDD(result);
	}