@Test
    public void testOnCreateApplication() throws GenieException {
        final Application a = new Application(NAME, USER, ApplicationStatus.ACTIVE);
        a.onCreateApplication();
    }