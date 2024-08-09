@Test
    public void testUpdateApplication() throws Exception {
        final Application application1 = constructApplicationDTO(null);
        applicationClient.createApplication(application1);

        final Application application2 = applicationClient.getApplication(application1.getId());
        Assert.assertEquals(application2.getName(), application1.getName());

        final Application application3 = new
            Application.Builder("newname", "newuser", "new version", ApplicationStatus.ACTIVE)
            .withId(application1.getId())
            .build();

        applicationClient.updateApplication(application1.getId(), application3);

        final Application application4 = applicationClient.getApplication(application1.getId());

        Assert.assertEquals("newname", application4.getName());
        Assert.assertEquals("newuser", application4.getUser());
        Assert.assertEquals("new version", application4.getVersion());
        Assert.assertEquals(ApplicationStatus.ACTIVE, application4.getStatus());
        Assert.assertEquals(null, application4.getSetupFile());
        Assert.assertEquals(null, application4.getDescription());
        Assert.assertEquals(Collections.emptySet(), application4.getConfigs());
        Assert.assertEquals(application4.getTags().contains("foo"), false);
    }