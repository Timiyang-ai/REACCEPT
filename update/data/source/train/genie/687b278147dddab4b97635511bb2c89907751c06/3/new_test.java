@Test
    public void testOnCreateOrUpdate() throws GenieException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE);
        this.a.onCreateOrUpdate();
    }