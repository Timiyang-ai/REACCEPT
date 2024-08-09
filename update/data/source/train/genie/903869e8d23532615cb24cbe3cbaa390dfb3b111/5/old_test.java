@Test
    public void testUpdateApplication() throws GenieException {
        final Application getApp = this.appService.getApplication(APP_1_ID);
        Assert.assertEquals(APP_1_USER, getApp.getUser());
        Assert.assertEquals(ApplicationStatus.INACTIVE, getApp.getStatus());
        Assert.assertEquals(3, getApp.getTags().size());
        final Instant updateTime = getApp.getUpdated().orElseThrow(IllegalArgumentException::new);

        final Set<String> tags = Sets.newHashSet("prod", "tez", "yarn", "hadoop");
        tags.addAll(getApp.getTags());
        final Application.Builder updateApp = new Application
            .Builder(getApp.getName(), APP_2_USER, getApp.getVersion(), ApplicationStatus.ACTIVE)
            .withId(getApp.getId().orElseThrow(IllegalArgumentException::new))
            .withCreated(getApp.getCreated().orElseThrow(IllegalArgumentException::new))
            .withUpdated(getApp.getUpdated().orElseThrow(IllegalArgumentException::new))
            .withTags(tags)
            .withConfigs(getApp.getConfigs())
            .withDependencies(getApp.getDependencies());

        getApp.getDescription().ifPresent(updateApp::withDescription);
        getApp.getSetupFile().ifPresent(updateApp::withSetupFile);
        this.appService.updateApplication(APP_1_ID, updateApp.build());

        final Application updated = this.appService.getApplication(APP_1_ID);
        Assert.assertNotEquals(updated.getUpdated(), Matchers.is(updateTime));
        Assert.assertEquals(APP_2_USER, updated.getUser());
        Assert.assertEquals(ApplicationStatus.ACTIVE, updated.getStatus());
        Assert.assertEquals(6, updated.getTags().size());
    }