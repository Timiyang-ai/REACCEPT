@Test
    public void testPatchApplication() throws GenieException, IOException {
        final Application getApp = this.appService.getApplication(APP_1_ID);
        Assert.assertEquals(APP_1_USER, getApp.getUser());
        final Instant updateTime = getApp.getUpdated().orElseThrow(IllegalArgumentException::new);

        final String patchString = "[{ \"op\": \"replace\", \"path\": \"/user\", \"value\": \"" + APP_2_USER + "\" }]";
        final JsonPatch patch = JsonPatch.fromJson(GenieObjectMapper.getMapper().readTree(patchString));

        this.appService.patchApplication(APP_1_ID, patch);

        final Application updated = this.appService.getApplication(APP_1_ID);
        Assert.assertNotEquals(updated.getUpdated(), Matchers.is(updateTime));
        Assert.assertEquals(APP_2_USER, updated.getUser());
    }