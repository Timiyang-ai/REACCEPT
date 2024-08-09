@Test
    public void testRemoveAllTagsForCluster() throws GenieException {
        Assert.assertEquals(5, this.service.getTagsForCluster(CLUSTER_1_ID).size());
        this.service.removeAllTagsForCluster(CLUSTER_1_ID);
        Assert.assertEquals(2, this.service.getTagsForCluster(CLUSTER_1_ID).size());
    }