@Test
    public void testRemoveAllTagsForCommand() throws GenieException {
        Assert.assertEquals(5, this.service.getTagsForCommand(COMMAND_1_ID).size());
        this.service.removeAllTagsForCommand(COMMAND_1_ID);
        Assert.assertEquals(2, this.service.getTagsForCommand(COMMAND_1_ID).size());
    }