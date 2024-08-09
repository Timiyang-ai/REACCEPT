@Test
    public void testGetCommandsForApplication() throws GenieException {
        final Set<Command> commands
                = this.service.getCommandsForApplication(APP_1_ID);
        Assert.assertEquals(1, commands.size());
        Assert.assertEquals(COMMAND_1_ID, commands.iterator().next().getId());
    }