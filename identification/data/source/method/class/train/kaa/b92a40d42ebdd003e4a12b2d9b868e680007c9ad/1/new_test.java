@Test
    public void testGetApplication() throws Exception {
        ApplicationDto application = createApplication();

        ApplicationDto storedApplication = client.getApplicationByApplicationToken(application.getApplicationToken());

        Assert.assertNotNull(storedApplication);
        assertApplicationsEquals(application, storedApplication);
    }