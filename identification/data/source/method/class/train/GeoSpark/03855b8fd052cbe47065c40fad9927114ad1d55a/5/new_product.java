public static JavaRDD<LineString> SpatialRangeQuery(LineStringRDD spatialRDD, Envelope queryWindow,Integer condition,boolean useIndex) throws Exception
	{
		if(useIndex==true)
		{
	        if(spatialRDD.indexedRawRDD == null) {
	            throw new Exception("[RangeQuery][SpatialRangeQuery] Index doesn't exist. Please build index on rawSpatialRDD.");
	        }
			JavaRDD<Object> result = spatialRDD.indexedRawRDD.mapPartitions(new RangeFilterUsingIndex(queryWindow));
			return result.map(new Function<Object, LineString>()
			{

				@Override
				public LineString call(Object spatialObject) throws Exception {
					return (LineString)spatialObject;
				}
				
			});
		}
		else{
			JavaRDD<Object> result = spatialRDD.getRawSpatialRDD().filter(new GeometryRangeFilter(queryWindow, condition));
			return result.map(new Function<Object,LineString>()
			{
				@Override
				public LineString call(Object spatialObject) throws Exception {
					return (LineString)spatialObject;
				}
				
			});
		}
	}