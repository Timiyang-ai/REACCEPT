@Test
    public void testCreateApplication() throws GenieException {
        final Application app = new Application(
                APP_1_NAME,
                APP_1_USER,
                ApplicationStatus.ACTIVE,
                APP_1_VERSION
        );
        final String id = UUID.randomUUID().toString();
        app.setId(id);
        final Application created = this.service.createApplication(app);
        Assert.assertNotNull(this.service.getApplication(id));
        Assert.assertEquals(id, created.getId());
        Assert.assertEquals(APP_1_NAME, created.getName());
        Assert.assertEquals(APP_1_USER, created.getUser());
        Assert.assertEquals(ApplicationStatus.ACTIVE, created.getStatus());
        this.service.deleteApplication(id);
        try {
            this.service.getApplication(id);
            Assert.fail("Should have thrown exception");
        } catch (final GenieException ge) {
            Assert.assertEquals(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    ge.getErrorCode()
            );
        }
    }