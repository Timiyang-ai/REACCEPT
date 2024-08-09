@Test
    public void testGetCommandsForApplication() throws GenieException {
        final List<Command> commands
                = this.service.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(1, commands.size());
        Assert.assertEquals(COMMAND_1_ID, commands.iterator().next().getId());
    }