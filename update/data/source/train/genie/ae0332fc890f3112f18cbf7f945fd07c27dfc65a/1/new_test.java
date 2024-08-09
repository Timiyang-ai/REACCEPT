@Test
    public void testOnCreateOrUpdateApplication() throws GenieException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE, VERSION);
        this.a.onCreateOrUpdateApplication();
    }