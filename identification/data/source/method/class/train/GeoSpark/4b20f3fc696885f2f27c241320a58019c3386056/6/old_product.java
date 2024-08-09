public static List<Envelope> SpatialKnnQuery(RectangleRDD spatialRDD, Point queryCenter, Integer k, boolean useIndex) {
		// For each partation, build a priority queue that holds the topk
		//@SuppressWarnings("serial")
		if(useIndex)
		{
	        if(spatialRDD.indexedRawRDD == null) {
	            throw new NullPointerException("Need to invoke buildIndex() first, indexedRDDNoId is null");
	        }
			JavaRDD<Object> tmp = spatialRDD.indexedRawRDD.mapPartitions(new KnnJudgementUsingIndex(queryCenter,k));
			JavaRDD<Envelope> result = tmp.map(new Function<Object, Envelope>()
			{

				@Override
				public Envelope call(Object spatialObject) throws Exception {
					Envelope returnSpatialObject = ((Geometry)spatialObject).getEnvelopeInternal();
					if( ((Geometry)spatialObject).getUserData()!=null)
					{
						returnSpatialObject.setUserData(((Geometry)spatialObject).getUserData());
					}
					return returnSpatialObject;
				}
				
			});
			// Take the top k
			return result.takeOrdered(k, new RectangleDistanceComparator(queryCenter));
		}
		else
		{
			JavaRDD<Object> tmp = spatialRDD.getRawSpatialRDD().mapPartitions(new RectangleKnnJudgement(queryCenter,k));
			JavaRDD<Envelope> result = tmp.map(new Function<Object,Envelope>()
			{

				@Override
				public Envelope call(Object spatialObject) throws Exception {
					return (Envelope) spatialObject;
				}
			});
			// Take the top k
			return result.takeOrdered(k, new RectangleDistanceComparator(queryCenter));
		}
	}