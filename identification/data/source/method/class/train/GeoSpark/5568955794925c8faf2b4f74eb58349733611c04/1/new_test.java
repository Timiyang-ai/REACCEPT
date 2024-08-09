@Test
    public void testSpatialRangeQuery() throws Exception {
    	PointRDD spatialRDD = new PointRDD(sc, InputLocation, offset, splitter, true,StorageLevel.MEMORY_ONLY());
    	for(int i=0;i<loopTimes;i++)
    	{
    		long resultSize = RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, false,false).count();
    		System.out.println("testSpatialRangeQuery: "+resultSize);
    		assert resultSize==3157;
    	}
    	assert RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, false,false).take(10).get(1).getUserData().toString()!=null;
        
    }