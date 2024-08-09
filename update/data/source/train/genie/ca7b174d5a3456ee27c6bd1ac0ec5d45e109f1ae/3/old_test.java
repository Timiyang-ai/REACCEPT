@Test
    public void testSetCommands() throws GeniePreconditionException {
        Assert.assertNotNull(this.c.getCommands());
        Assert.assertTrue(this.c.getCommands().isEmpty());
        final CommandEntity one = new CommandEntity();
        one.setId("one");
        final CommandEntity two = new CommandEntity();
        two.setId("two");
        final List<CommandEntity> commands = new ArrayList<>();
        commands.add(one);
        commands.add(two);
        this.c.setCommands(commands);
        Assert.assertEquals(commands, this.c.getCommands());
        Assert.assertTrue(one.getClusters().contains(this.c));
        Assert.assertTrue(two.getClusters().contains(this.c));
        this.c.setCommands(null);
        Assert.assertNull(this.c.getCommands());
        Assert.assertFalse(one.getClusters().contains(this.c));
        Assert.assertFalse(two.getClusters().contains(this.c));
    }