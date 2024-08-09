@Test
    public void testSetCommands() throws GeniePreconditionException {
        Assert.assertNull(this.c.getCommands());
        final Command one = new Command();
        one.setId("one");
        final Command two = new Command();
        two.setId("two");
        final List<Command> commands = new ArrayList<>();
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