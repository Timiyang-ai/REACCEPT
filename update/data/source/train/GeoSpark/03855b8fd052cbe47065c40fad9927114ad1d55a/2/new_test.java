@Test
    public void testSpatialRangeQueryUsingIndex() throws Exception {
    	PointRDD spatialRDD = new PointRDD(sc, InputLocation, offset, splitter, true);
    	spatialRDD.buildIndex(IndexType.RTREE,false);
    	for(int i=0;i<loopTimes;i++)
    	{
    		long resultSize = RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, 0,true).count();
    		assert resultSize>-1;
    	}
    	assert RangeQuery.SpatialRangeQuery(spatialRDD, queryEnvelope, 0,true).take(10).get(1).getUserData().toString() !=null;
    }