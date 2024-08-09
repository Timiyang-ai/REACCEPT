@Test
    public void testBuildIndex() throws Exception {
        RectangleRDD rectangleRDD = new RectangleRDD(sc, InputLocation, offset, splitter, gridType, numPartitions);
        rectangleRDD.buildIndex("R-Tree");
        List<Polygon> result = rectangleRDD.indexedRDD.take(1).get(0)._2().query(rectangleRDD.boundaryEnvelope);
        for(Polygon e: result) {
            System.out.println(e.getEnvelopeInternal());
        }
    }