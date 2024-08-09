@Test
    public void testSpatialRangeQueryUsingIndex() throws Exception {
    	PointRDD pointRDD = new PointRDD(sc, InputLocation, offset, splitter);
    	pointRDD.buildIndex("rtree");
    	for(int i=0;i<loopTimes;i++)
    	{
    		long resultSize = RangeQuery.SpatialRangeQueryUsingIndex(pointRDD, queryEnvelope, 0).getRawPointRDD().count();
    		assert resultSize>-1;
    	}
    	assert RangeQuery.SpatialRangeQueryUsingIndex(pointRDD, queryEnvelope, 0).getRawPointRDD().take(10).get(1).getUserData().toString() !=null;
        
    }