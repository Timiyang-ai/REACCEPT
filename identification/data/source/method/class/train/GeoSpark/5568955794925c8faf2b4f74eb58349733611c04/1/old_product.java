public static JavaRDD<Polygon> SpatialRangeQuery(PolygonRDD spatialRDD, Envelope queryWindow,boolean considerBoundaryIntersection,boolean useIndex) throws Exception
	{
		if(useIndex==true)
		{
	        if(spatialRDD.indexedRawRDD == null) {
	            throw new Exception("[RangeQuery][SpatialRangeQuery] Index doesn't exist. Please build index on rawSpatialRDD.");
	        }
			JavaRDD<Object> result = spatialRDD.indexedRawRDD.mapPartitions(new RangeFilterUsingIndex(queryWindow,considerBoundaryIntersection));
			return result.map(new Function<Object, Polygon>()
			{

				@Override
				public Polygon call(Object spatialObject) throws Exception {
					return (Polygon)spatialObject;
				}
				
			});
		}
		else{
			JavaRDD<Object> result = spatialRDD.getRawSpatialRDD().filter(new RangeFilter(queryWindow, considerBoundaryIntersection));
			return result.map(new Function<Object,Polygon>()
			{
				@Override
				public Polygon call(Object spatialObject) throws Exception {
					return (Polygon)spatialObject;
				}
				
			});
		}
	}