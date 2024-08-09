@Test
    public void testGetConfigsForCluster() throws GenieException {
        Assert.assertEquals(1,
                this.service.getConfigsForCluster(CLUSTER_1_ID).size());
    }