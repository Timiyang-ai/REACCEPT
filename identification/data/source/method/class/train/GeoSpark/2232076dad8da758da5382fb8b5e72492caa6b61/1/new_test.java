@Test
    public void testSpatialKnnQuery() throws Exception {
    	RectangleRDD rectangleRDD = new RectangleRDD(sc, InputLocation, offset, splitter, true);

    	for(int i=0;i<loopTimes;i++)
    	{
    		List<Polygon> result = KNNQuery.SpatialKnnQuery(rectangleRDD, queryPoint, topK, false);
    		assert result.size()>-1;
    		assert result.get(0).getUserData().toString()!=null;
    		//System.out.println(result.get(0).getUserData().toString());
    	}

    }