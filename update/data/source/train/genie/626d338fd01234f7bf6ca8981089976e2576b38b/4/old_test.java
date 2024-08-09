@Test
    public void testUpdateTagsForCommand() throws GenieException {
        final String newTag1 = UUID.randomUUID().toString();
        final String newTag2 = UUID.randomUUID().toString();
        final String newTag3 = UUID.randomUUID().toString();

        final Set<String> newTags = Sets.newHashSet(newTag1, newTag2, newTag3);

        Assert.assertEquals(5, this.service.getTagsForCommand(COMMAND_1_ID).size());
        this.service.updateTagsForCommand(COMMAND_1_ID, newTags);
        final Set<String> finalTags = this.service.getTagsForCommand(COMMAND_1_ID);
        Assert.assertEquals(5, finalTags.size());
        Assert.assertTrue(finalTags.contains(newTag1));
        Assert.assertTrue(finalTags.contains(newTag2));
        Assert.assertTrue(finalTags.contains(newTag3));
    }