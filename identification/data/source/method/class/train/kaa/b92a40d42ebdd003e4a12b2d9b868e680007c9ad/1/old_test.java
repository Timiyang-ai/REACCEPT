@Test
    public void testGetApplication() throws Exception {
        ApplicationDto application = createApplication();

        ApplicationDto storedApplication = client.getApplication(application.getId());

        Assert.assertNotNull(storedApplication);
        assertApplicationsEquals(application, storedApplication);
    }