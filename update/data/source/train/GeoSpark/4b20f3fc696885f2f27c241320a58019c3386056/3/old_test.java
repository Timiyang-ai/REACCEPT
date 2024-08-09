@Test
    public void testSpatialKnnQuery() throws Exception {
    	RectangleRDD rectangleRDD = new RectangleRDD(sc, InputLocation, offset, splitter, true);

    	for(int i=0;i<loopTimes;i++)
    	{
    		List<Envelope> result = KNNQuery.SpatialKnnQuery(rectangleRDD, queryPoint, 5, false);
    		assert result.size()>-1;
    		assert result.get(0).getUserData().toString()!=null;
    		//System.out.println(result.get(0).getUserData().toString());
    	}

    }