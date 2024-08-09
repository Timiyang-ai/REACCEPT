@Test
    public void testUpdateApplication() throws GenieException {
        final Application getApp = this.appService.getApplication(APP_1_ID);
        Assert.assertEquals(APP_1_USER, getApp.getMetadata().getUser());
        Assert.assertEquals(ApplicationStatus.INACTIVE, getApp.getMetadata().getStatus());
        Assert.assertEquals(1, getApp.getMetadata().getTags().size());
        final Instant updateTime = getApp.getUpdated();

        final Set<String> tags = Sets.newHashSet("prod", "tez", "yarn", "hadoop");
        tags.addAll(getApp.getMetadata().getTags());
        final Application updateApp = new Application(
            getApp.getId(),
            getApp.getCreated(),
            getApp.getUpdated(),
            getApp.getResources(),
            new ApplicationMetadata.Builder(
                getApp.getMetadata().getName(),
                APP_2_USER,
                ApplicationStatus.ACTIVE
            )
                .withVersion(getApp.getMetadata().getVersion().orElse(null))
                .withDescription(getApp.getMetadata().getDescription().orElse(null))
                .withType(getApp.getMetadata().getType().orElse(null))
                .withTags(tags)
                .build()
        );

        this.appService.updateApplication(APP_1_ID, updateApp);

        final Application updated = this.appService.getApplication(APP_1_ID);
        Assert.assertNotEquals(updated.getUpdated(), Matchers.is(updateTime));
        Assert.assertEquals(APP_2_USER, updated.getMetadata().getUser());
        Assert.assertEquals(ApplicationStatus.ACTIVE, updated.getMetadata().getStatus());
        Assert.assertEquals(tags, updated.getMetadata().getTags());
    }