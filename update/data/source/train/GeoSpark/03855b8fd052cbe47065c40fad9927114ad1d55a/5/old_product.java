public static RectangleRDD SpatialRangeQuery(RectangleRDD rectangleRDD, Envelope envelope,Integer condition)
	{
		JavaRDD<Envelope> result= rectangleRDD.getRawRectangleRDD().filter(new RectangleRangeFilter(envelope,condition));
		return new RectangleRDD(result);
	}