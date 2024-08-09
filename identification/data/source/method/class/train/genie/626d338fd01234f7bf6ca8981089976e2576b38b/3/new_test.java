@Test
    public void testGetTagsForCommand() throws GenieException {
        Assert.assertEquals(3, this.service.getTagsForCommand(COMMAND_1_ID).size());
    }