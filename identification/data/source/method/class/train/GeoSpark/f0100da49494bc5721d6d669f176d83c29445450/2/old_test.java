@Test
    public void testSpatialKnnQueryUsingIndex() throws Exception {
    	PolygonRDD polygonRDD = new PolygonRDD(sc, InputLocation, offset, splitter);
    	polygonRDD.buildIndex(IndexType.RTREE);
    	for(int i=0;i<loopTimes;i++)
    	{
    		List<Polygon> result = KNNQuery.SpatialKnnQueryUsingIndex(polygonRDD, queryPoint, 5);
    		assert result.size()>-1;
    		assert result.get(0).getUserData().toString()!=null;
    		//System.out.println(result.get(0).getUserData().toString());
    	}

    }