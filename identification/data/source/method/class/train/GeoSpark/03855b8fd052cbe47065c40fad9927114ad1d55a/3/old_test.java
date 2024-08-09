@Test
    public void testSpatialRangeQuery() throws Exception {
    	RectangleRDD rectangleRDD = new RectangleRDD(sc, InputLocation, offset, splitter);
    	for(int i=0;i<loopTimes;i++)
    	{
    		long resultSize = RangeQuery.SpatialRangeQuery(rectangleRDD, queryEnvelope, 0).getRawRectangleRDD().count();
    		assert resultSize>-1;
    	}
    	assert RangeQuery.SpatialRangeQuery(rectangleRDD, queryEnvelope, 0).getRawRectangleRDD().take(10).get(1).getUserData().toString()!=null;
    }