@Test
    public void testSpatialJoinQueryWithPolygonRDDUsingQuadTreeIndex() throws Exception {
        sc = new JavaSparkContext(conf);
    	
        PolygonRDD queryRDD = new PolygonRDD(sc, InputLocationQueryPolygon, offset, splitter, numPartitions);

        PolygonRDD spatialRDD = new PolygonRDD(sc, InputLocation, offset, splitter, numPartitions);
        
        spatialRDD.spatialPartitioning(gridType);
        
        spatialRDD.buildIndex(IndexType.QUADTREE, true);
        
        queryRDD.spatialPartitioning(spatialRDD.grids);
        
        List<Tuple2<Polygon, HashSet<Polygon>>> result = JoinQuery.SpatialJoinQuery(spatialRDD,queryRDD,false).collect();
        
        assert result.get(1)._1().getUserData()!=null;
        for(int i=0;i<result.size();i++)
        {
        	if(result.get(i)._2().size()!=0)
        	{
        		assert result.get(i)._2().iterator().next().getUserData()!=null;
        	}
        }
        sc.close();
    }