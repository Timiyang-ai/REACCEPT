@Test
    public void testSpatialRangeQuery() throws Exception {
    	PointRDD spatialRDD = new PointRDD(sc, InputLocation, offset, splitter, true);
    	for(int i=0;i<loopTimes;i++)
    	{
    		long resultSize = RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, false,false).count();
    		assert resultSize>-1;
    	}
    	assert RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, false,false).take(10).get(1).getUserData().toString()!=null;
        
    }