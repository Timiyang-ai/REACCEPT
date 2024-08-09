@Test
    public void testEuclideanWeighhted() {
        logger.info("euclideanWeighhted");
        AssociativeArray a1 = new AssociativeArray(getMap1());
        AssociativeArray a2 = new AssociativeArray(getMap2());
        Map<Object, Double> columnWeights = getWeights();
        double expResult = 2.449489742783178;
        double result = Distance.euclideanWeighted(a1, a2, columnWeights);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }