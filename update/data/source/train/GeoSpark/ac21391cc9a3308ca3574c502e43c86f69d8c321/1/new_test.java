@Test
    public void testBuildIndex() throws Exception {
        RectangleRDD rectangleRDD = new RectangleRDD(sc, InputLocation, offset, splitter, gridType, numPartitions);
        rectangleRDD.buildIndex("R-Tree");
        List<Polygon> result = rectangleRDD.indexedRDD.take(1).get(0)._2().query(rectangleRDD.boundaryEnvelope);
        //todo, here have their might be a problem where the result is essentially a point(dirty data) and jts will throw exception.
        try {
            for(Polygon e: result) {
                System.out.println(e.getEnvelopeInternal());
            }
        } catch (Exception e) {

        }
    }