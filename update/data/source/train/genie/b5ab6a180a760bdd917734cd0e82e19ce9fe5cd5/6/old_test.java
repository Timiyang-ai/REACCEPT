@Test
    public void testAddCommand() throws GeniePreconditionException {
        final CommandEntity commandEntity = new CommandEntity();
        commandEntity.setId("commandId");
        Assert.assertNotNull(this.c.getCommands());
        Assert.assertTrue(this.c.getCommands().isEmpty());
        this.c.addCommand(commandEntity);
        Assert.assertTrue(this.c.getCommands().contains(commandEntity));
        Assert.assertTrue(commandEntity.getClusters().contains(this.c));
    }