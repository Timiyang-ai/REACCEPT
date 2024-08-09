@Test
    public void testOnCreateOrUpdateCommand() throws GenieException {
        this.c = new Command(NAME, USER, CommandStatus.ACTIVE, EXECUTABLE, VERSION);
        this.c.onCreateOrUpdateCommand();
    }