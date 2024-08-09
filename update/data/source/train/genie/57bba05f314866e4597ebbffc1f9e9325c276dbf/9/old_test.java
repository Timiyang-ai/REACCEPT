@Test
    public void testOnCreateOrUpdateApplication() throws GeniePreconditionException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE, VERSION);
        this.a.onCreateOrUpdateApplication();
    }