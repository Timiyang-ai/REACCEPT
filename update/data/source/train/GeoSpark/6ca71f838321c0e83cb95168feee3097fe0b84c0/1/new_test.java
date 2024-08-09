@Test
    public void testBuildIndex() throws Exception {
        PointRDD pointRDD = new PointRDD(sc, InputLocation, offset, splitter, gridType, numPartitions);
        pointRDD.buildIndex("");
        List<Point> result = pointRDD.indexedRDD.take(1).get(0)._2().query(pointRDD.boundaryEnvelope);
    }