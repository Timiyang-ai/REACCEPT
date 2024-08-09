@Test
    public void testManhattanWeighhted() {
        logger.info("manhattanWeighhted");
        AssociativeArray a1 = new AssociativeArray(getMap1());
        AssociativeArray a2 = new AssociativeArray(getMap2());
        Map<Object, Double> columnWeights = getWeights();
        double expResult = 5.0;
        double result = Distance.manhattanWeighted(a1, a2, columnWeights);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }