@Test
    public void testGetTagsForCommand() throws GenieException {
        Assert.assertEquals(5,
            this.service.getTagsForCommand(COMMAND_1_ID).size());
    }