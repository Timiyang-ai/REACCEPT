@Test
    public void testCreateApplication() throws GenieException {
        final String id = UUID.randomUUID().toString();
        final ApplicationRequest app = new ApplicationRequest.Builder(
            new ApplicationMetadata.Builder(
                APP_1_NAME,
                APP_1_USER,
                ApplicationStatus.ACTIVE
            )
                .withVersion(APP_1_VERSION)
                .build()
        )
            .withRequestedId(id)
            .build();
        final String createdId = this.appService.createApplication(app);
        Assert.assertThat(createdId, Matchers.is(id));
        final Application created = this.appService.getApplication(id);
        Assert.assertNotNull(created);
        Assert.assertEquals(id, created.getId());
        Assert.assertEquals(APP_1_NAME, created.getMetadata().getName());
        Assert.assertEquals(APP_1_USER, created.getMetadata().getUser());
        Assert.assertEquals(ApplicationStatus.ACTIVE, created.getMetadata().getStatus());
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