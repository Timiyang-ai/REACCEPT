@Test
    public void testSpatialRangeQuery() throws Exception {
    	RectangleRDD spatialRDD = new RectangleRDD(sc, InputLocation, offset, splitter, true);
    	for(int i=0;i<loopTimes;i++)
    	{
    		long resultSize = RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, 0,false).count();
    		assert resultSize>-1;
    	}
    	assert RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, 0,false).take(10).get(1).getUserData().toString()!=null;
        
    }