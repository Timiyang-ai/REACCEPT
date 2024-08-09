@Test
    public void testRemoveAllTagsForCluster() throws GenieException {
        Assert.assertEquals(3, this.service.getTagsForCluster(CLUSTER_1_ID).size());
        this.service.removeAllTagsForCluster(CLUSTER_1_ID);
        Assert.assertEquals(0, this.service.getTagsForCluster(CLUSTER_1_ID).size());
    }