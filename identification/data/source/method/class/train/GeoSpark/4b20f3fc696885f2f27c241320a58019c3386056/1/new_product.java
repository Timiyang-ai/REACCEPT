public static JavaRDD<Point> SpatialRangeQuery(PointRDD spatialRDD, Envelope queryWindow, boolean considerBoundaryIntersection, boolean useIndex) throws Exception {
		if(useIndex==true)
		{
	        if(spatialRDD.indexedRawRDD == null) {
	            throw new Exception("[RangeQuery][SpatialRangeQuery] Index doesn't exist. Please build index on rawSpatialRDD.");
	        }
			JavaRDD<Object> result = spatialRDD.indexedRawRDD.mapPartitions(new RangeFilterUsingIndex(queryWindow,considerBoundaryIntersection));
			return result.map(new Function<Object, Point>()
			{

				@Override
				public Point call(Object spatialObject) throws Exception {
					return (Point)spatialObject;
				}
				
			});
		}
		else{
			JavaRDD<Object> result = spatialRDD.getRawSpatialRDD().filter(new RangeFilter(queryWindow, considerBoundaryIntersection));
			return result.map(new Function<Object,Point>()
			{
				@Override
				public Point call(Object spatialObject) throws Exception {
					return (Point)spatialObject;
				}
				
			});
		}
	}