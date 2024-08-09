@Test
    public void testGetTagsForCluster() throws GenieException {
        Assert.assertEquals(3, this.service.getTagsForCluster(CLUSTER_1_ID).size());
    }