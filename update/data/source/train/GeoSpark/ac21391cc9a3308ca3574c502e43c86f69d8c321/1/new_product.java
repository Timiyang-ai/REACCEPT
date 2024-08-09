public void buildIndex(String indexType) {

		if (this.gridRectangleRDD == null) {
			throw new IllegalClassException("To build index, you must build grid first");
		}

		//Use GroupByKey, since I have repartition data, it should be much faster.
		//todo: Need to test performance here...
		JavaPairRDD<Integer, Iterable<Envelope>> gridedRectangleListRDD = this.gridRectangleRDD.groupByKey();

		this.indexedRDD = gridedRectangleListRDD.flatMapValues(new Function<Iterable<Envelope>, Iterable<STRtree>>() {
			@Override
			public Iterable<STRtree> call(Iterable<Envelope> envelopes) throws Exception {
				STRtree rt = new STRtree();
				GeometryFactory geometryFactory = new GeometryFactory();
				for (Envelope e : envelopes)
					try {
						rt.insert(e, geometryFactory.toGeometry(e));
					} catch (ClassCastException e1) {
					}
				ArrayList<STRtree> result = new ArrayList<STRtree>();
				result.add(rt);
				return result;
			}
		});
		this.indexedRDD.cache();
	}