public void buildIndex(String indexType) {

        if (this.gridPolygonRDD == null) {
            throw new IllegalClassException("To build index, you must build grid first");
        }

        //Use GroupByKey, since I have repartition data, it should be much faster.
        //todo: Need to test performance here...
        JavaPairRDD<Integer, Iterable<Polygon>> gridedRectangleListRDD = this.gridPolygonRDD.groupByKey();

        this.indexedRDD = gridedRectangleListRDD.flatMapValues(new Function<Iterable<Polygon>, Iterable<STRtree>>() {
            @Override
            public Iterable<STRtree> call(Iterable<Polygon> polygons) throws Exception {
                STRtree rt = new STRtree();
                for (Polygon p : polygons)
                    rt.insert(p.getEnvelopeInternal(), p);
                ArrayList<STRtree> result = new ArrayList<STRtree>();
                result.add(rt);
                return result;
            }
        });
        this.indexedRDD.cache();
    }