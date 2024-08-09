@Test
    public void testUpdateTagsForCluster() throws GenieException {
        final String newTag1 = UUID.randomUUID().toString();
        final String newTag2 = UUID.randomUUID().toString();
        final String newTag3 = UUID.randomUUID().toString();

        final Set<String> newTags = Sets.newHashSet(newTag1, newTag2, newTag3);

        Assert.assertEquals(3, this.service.getTagsForCluster(CLUSTER_1_ID).size());
        this.service.updateTagsForCluster(CLUSTER_1_ID, newTags);
        final Set<String> finalTags = this.service.getTagsForCluster(CLUSTER_1_ID);
        Assert.assertEquals(3, finalTags.size());
        Assert.assertTrue(finalTags.contains(newTag1));
        Assert.assertTrue(finalTags.contains(newTag2));
        Assert.assertTrue(finalTags.contains(newTag3));
    }