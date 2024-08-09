@Test
    public void testSpatialRangeQuery() throws Exception {
    	LineStringRDD spatialRDD = new LineStringRDD(sc, InputLocation, splitter, true,StorageLevel.MEMORY_ONLY());
    	for(int i=0;i<loopTimes;i++)
    	{
    		long resultSize = RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, false,false).count();
    		assert resultSize>-1;
    	}
    	assert RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, false,false).take(10).get(1).getUserData().toString()!=null;
        
    }