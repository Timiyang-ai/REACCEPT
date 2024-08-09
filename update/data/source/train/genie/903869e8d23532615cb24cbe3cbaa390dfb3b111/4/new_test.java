@Test
    public void testGetCommandsForApplication() throws GenieException {
        final Set<Command> commands = this.appService.getCommandsForApplication(APP_1_ID, null);
        Assert.assertEquals(1, commands.size());
        Assert.assertEquals(COMMAND_1_ID, commands.iterator().next().getId());
    }