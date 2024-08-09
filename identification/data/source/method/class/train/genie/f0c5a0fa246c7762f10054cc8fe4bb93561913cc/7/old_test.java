@Test
    public void testOnCreateOrUpdateCommand() throws GeniePreconditionException {
        this.c = new Command(NAME, USER, VERSION, CommandStatus.ACTIVE, EXECUTABLE);
        Assert.assertNull(this.c.getTags());
        this.c.onCreateOrUpdateCommand();
        Assert.assertEquals(2, this.c.getTags().size());
    }