@Test
    public void testRemoveAllTagsForCommand() throws GenieException {
        Assert.assertEquals(3, this.service.getTagsForCommand(COMMAND_1_ID).size());
        this.service.removeAllTagsForCommand(COMMAND_1_ID);
        Assert.assertEquals(0, this.service.getTagsForCommand(COMMAND_1_ID).size());
    }