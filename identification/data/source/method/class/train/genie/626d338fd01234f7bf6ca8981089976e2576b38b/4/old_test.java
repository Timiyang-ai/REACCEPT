@Test
    public void testGetTagsForCluster() throws GenieException {
        Assert.assertEquals(5,
            this.service.getTagsForCluster(CLUSTER_1_ID).size());
    }