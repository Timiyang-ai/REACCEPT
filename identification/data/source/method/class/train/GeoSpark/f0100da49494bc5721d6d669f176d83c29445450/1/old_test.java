@Test
    public void testSpatialJoinQueryUsingIndex() throws Exception {

    	PolygonRDD polygonRDD = new PolygonRDD(sc, InputLocation, offset, splitter, numPartitions);

    	PolygonRDD objectRDD = new PolygonRDD(sc, InputLocation, offset, splitter, gridType, numPartitions);

    	objectRDD.buildIndex(IndexType.RTREE);

        JoinQuery joinQuery = new JoinQuery(sc,objectRDD,polygonRDD);
        
        List<Tuple2<Polygon, HashSet<Polygon>>> result = joinQuery.SpatialJoinQueryUsingIndex(objectRDD,polygonRDD).collect();
        assert result.get(0)._1().getUserData()!=null;
        for(int i=0;i<result.size();i++)
        {
        	if(result.get(i)._2().size()!=0)
        	{
        		assert result.get(i)._2().iterator().next().getUserData()!=null;
        	}
        }
    }