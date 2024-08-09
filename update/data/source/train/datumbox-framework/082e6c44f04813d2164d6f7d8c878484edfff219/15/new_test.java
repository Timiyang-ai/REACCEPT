@Test
    public void testNbar() {
        logger.info("Nbar");
        TransposeDataList clusterIdList = generateClusterIdList();
        
        double expResult = 10.0;
        double result = ClusterSampling.nBar(clusterIdList);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }