@Test
    public void testUpdateApplication() throws GenieException {
        final Application updateApp = this.appService.getApplication(APP_1_ID);
        Assert.assertEquals(APP_1_USER, updateApp.getUser());
        Assert.assertEquals(ApplicationStatus.INACTIVE, updateApp.getStatus());
        Assert.assertEquals(3, updateApp.getTags().size());

        updateApp.setStatus(ApplicationStatus.ACTIVE);
        updateApp.setUser(APP_2_USER);
        final Set<String> tags = updateApp.getTags();
        tags.add("prod");
        tags.add("tez");
        tags.add("yarn");
        tags.add("hadoop");
        this.appService.updateApplication(APP_1_ID, updateApp);

        final Application updated = this.appService.getApplication(APP_1_ID);
        Assert.assertEquals(APP_2_USER, updated.getUser());
        Assert.assertEquals(ApplicationStatus.ACTIVE, updated.getStatus());
        Assert.assertEquals(6, updated.getTags().size());
    }