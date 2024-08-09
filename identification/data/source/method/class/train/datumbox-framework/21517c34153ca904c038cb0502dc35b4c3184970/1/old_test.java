@Test
    public void testWeightedProbabilitySampling() {
        TestUtils.log(this.getClass(), "weightedProbabilitySampling");
        AssociativeArray frequencyTable = new AssociativeArray();
        frequencyTable.put(1, 0.20);
        frequencyTable.put(2, 0.30);
        frequencyTable.put(3, 0.25);
        frequencyTable.put(4, 0.25);
        
        int n = 100;
        boolean withReplacement = true;
        double expResult = n;
        FlatDataCollection sampledIds = SRS.weightedProbabilitySampling(frequencyTable, n, withReplacement);
        double result = sampledIds.size();
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }