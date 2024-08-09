@Test
    public void testUpdateApplication() throws Exception {
        final Application application1 = constructApplicationDTO(null);
        applicationClient.createApplication(application1);

        final Application application2
            = applicationClient.getApplication(application1.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(application2.getName(), application1.getName());

        final Application application3
            = new Application.Builder("newname", "newuser", "new version", ApplicationStatus.ACTIVE)
            .withId(application1.getId().orElseThrow(IllegalArgumentException::new))
            .build();

        applicationClient
            .updateApplication(application1.getId().orElseThrow(IllegalArgumentException::new), application3);

        final Application application4
            = applicationClient.getApplication(application1.getId().orElseThrow(IllegalArgumentException::new));

        Assert.assertEquals("newname", application4.getName());
        Assert.assertEquals("newuser", application4.getUser());
        Assert.assertEquals("new version", application4.getVersion());
        Assert.assertEquals(ApplicationStatus.ACTIVE, application4.getStatus());
        Assert.assertFalse(application4.getSetupFile().isPresent());
        Assert.assertFalse(application4.getDescription().isPresent());
        Assert.assertEquals(Collections.emptySet(), application4.getConfigs());
        Assert.assertEquals(application4.getTags().contains("foo"), false);
    }