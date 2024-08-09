public static JavaRDD<Point> SpatialRangeQuery(PointRDD spatialRDD, Polygon queryWindow, Integer condition, boolean useIndex) throws Exception {
		if(useIndex==true)
		{
	        if(spatialRDD.indexedRawRDD == null) {
	            throw new Exception("[RangeQuery][SpatialRangeQuery] Index doesn't exist. Please build index on rawSpatialRDD.");
	        }
			JavaRDD<Object> result = spatialRDD.indexedRawRDD.mapPartitions(new RangeFilterUsingIndex(queryWindow));
			return result.map(new Function<Object, Point>()
			{

				@Override
				public Point call(Object spatialObject) throws Exception {
					return (Point)spatialObject;
				}
				
			});
		}
		else{
			JavaRDD<Object> result = spatialRDD.getRawSpatialRDD().filter(new GeometryRangeFilter(queryWindow, condition));
			return result.map(new Function<Object,Point>()
			{
				@Override
				public Point call(Object spatialObject) throws Exception {
					return (Point)spatialObject;
				}
				
			});
		}
	}