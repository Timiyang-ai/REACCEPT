@Test
    public void testSpatialKnnQueryUsingIndex() throws Exception {
    	PolygonRDD polygonRDD = new PolygonRDD(sc, InputLocation, offset, splitter);
    	polygonRDD.buildIndex(IndexType.RTREE,false);
    	for(int i=0;i<loopTimes;i++)
    	{
    		List<Polygon> result = KNNQuery.SpatialKnnQuery(polygonRDD, queryPoint, 5, true);
    		assert result.size()>-1;
    		assert result.get(0).getUserData().toString()!=null;
    		//System.out.println(result.get(0).getUserData().toString());
    	}

    }