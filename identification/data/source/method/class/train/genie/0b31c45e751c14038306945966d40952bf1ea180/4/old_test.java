@Test
    public void testAddCommand() throws GenieException {
        final Command command = new Command();
        command.setId("commandId");
        Assert.assertNull(this.c.getCommands());
        this.c.addCommand(command);
        Assert.assertTrue(this.c.getCommands().contains(command));
        Assert.assertTrue(command.getClusters().contains(this.c));
    }