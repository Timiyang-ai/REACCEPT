@Test
    public void testUpdateTagsForApplication() throws GenieException {
        final String newTag1 = UUID.randomUUID().toString();
        final String newTag2 = UUID.randomUUID().toString();
        final String newTag3 = UUID.randomUUID().toString();

        final Set<String> newTags = Sets.newHashSet(newTag1, newTag2, newTag3);

        Assert.assertEquals(1, this.appService.getTagsForApplication(APP_1_ID).size());
        this.appService.updateTagsForApplication(APP_1_ID, newTags);
        final Set<String> finalTags = this.appService.getTagsForApplication(APP_1_ID);
        Assert.assertEquals(3, finalTags.size());
        Assert.assertTrue(finalTags.contains(newTag1));
        Assert.assertTrue(finalTags.contains(newTag2));
        Assert.assertTrue(finalTags.contains(newTag3));
    }