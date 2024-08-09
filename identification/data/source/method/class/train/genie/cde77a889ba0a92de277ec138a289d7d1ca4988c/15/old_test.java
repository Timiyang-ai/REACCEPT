@Test
    public void testRemoveTagForCommand() throws GenieException {
        final Set<String> tags
                = this.service.getTagsForCommand(COMMAND_1_ID);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals(4,
                this.service.removeTagForCommand(
                        COMMAND_1_ID,
                        "tez").size()
        );
    }