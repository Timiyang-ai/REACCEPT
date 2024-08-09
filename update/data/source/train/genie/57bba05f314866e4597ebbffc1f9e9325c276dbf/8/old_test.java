@Test
    public void testOnCreateOrUpdateCommand() throws GeniePreconditionException {
        this.c = new Command(NAME, USER, CommandStatus.ACTIVE, EXECUTABLE, VERSION);
        this.c.onCreateOrUpdateCommand();
    }