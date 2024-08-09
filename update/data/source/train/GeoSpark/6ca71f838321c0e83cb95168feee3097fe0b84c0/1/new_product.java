public void buildIndex(String indexType) {

        if (this.gridPolygonRDD == null) {
        	
        	//This index is built on top of unpartitioned SRDD
            this.indexedRDDNoId =  this.rawPolygonRDD.mapPartitions(new FlatMapFunction<Iterator<Polygon>,STRtree>()
            		{
						@Override
						public Iterable<STRtree> call(Iterator<Polygon> t)
								throws Exception {
							// TODO Auto-generated method stub
							 STRtree rt = new STRtree();
							while(t.hasNext()){
								Polygon polygon=t.next();
			                    rt.insert(polygon.getEnvelopeInternal(), polygon);
							}
							HashSet<STRtree> result = new HashSet<STRtree>();
			                    result.add(rt);
			                    return result;
						}
            	
            		});
            this.indexedRDDNoId.persist(StorageLevel.MEMORY_ONLY());
        }
        else
        {
        //Use GroupByKey, since I have repartition data, it should be much faster.
        //todo: Need to test performance here...
        JavaPairRDD<Integer, Iterable<Polygon>> gridedRectangleListRDD = this.gridPolygonRDD.groupByKey();

        this.indexedRDD = gridedRectangleListRDD.flatMapValues(new Function<Iterable<Polygon>, Iterable<STRtree>>() {
            @Override
            public Iterable<STRtree> call(Iterable<Polygon> polygons) throws Exception {
                STRtree rt = new STRtree();
                for (Polygon p : polygons)
                    rt.insert(p.getEnvelopeInternal(), p);
                HashSet<STRtree> result = new HashSet<STRtree>();
                result.add(rt);
                return result;
            }
        });
        this.indexedRDD.persist(StorageLevel.MEMORY_ONLY());
        }
    }