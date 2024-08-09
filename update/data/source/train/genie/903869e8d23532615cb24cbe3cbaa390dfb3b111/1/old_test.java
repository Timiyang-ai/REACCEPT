@Test
    public void testCreateApplication() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final Application app = new Application
            .Builder(APP_1_NAME, APP_1_USER, APP_1_VERSION, ApplicationStatus.ACTIVE)
            .withId(id)
            .build();
        final String createdId = this.appService.createApplication(app);
        Assert.assertThat(createdId, Matchers.is(id));
        final Application created = this.appService.getApplication(id);
        Assert.assertNotNull(created);
        Assert.assertEquals(id, created.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(APP_1_NAME, created.getName());
        Assert.assertEquals(APP_1_USER, created.getUser());
        Assert.assertEquals(ApplicationStatus.ACTIVE, created.getStatus());
        this.appService.deleteApplication(id);
        try {
            this.appService.getApplication(id);
            Assert.fail("Should have thrown exception");
        } catch (final GenieException ge) {
            Assert.assertEquals(
                HttpURLConnection.HTTP_NOT_FOUND,
                ge.getErrorCode()
            );
        }
    }