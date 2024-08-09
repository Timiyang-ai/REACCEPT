@Test
    public void testGetApplication() throws GenieException {
        final Application app = this.appService.getApplication(APP_1_ID);
        Assert.assertEquals(APP_1_ID, app.getId().orElseGet(RandomSuppliers.STRING));
        Assert.assertEquals(APP_1_NAME, app.getName());
        Assert.assertEquals(APP_1_USER, app.getUser());
        Assert.assertEquals(APP_1_VERSION, app.getVersion());
        Assert.assertEquals(APP_1_STATUS, app.getStatus());
        Assert.assertFalse(app.getType().isPresent());
        Assert.assertEquals(3, app.getTags().size());
        Assert.assertEquals(2, app.getConfigs().size());
        Assert.assertEquals(2, app.getDependencies().size());

        final Application app2 = this.appService.getApplication(APP_2_ID);
        Assert.assertEquals(APP_2_ID, app2.getId().orElseGet(RandomSuppliers.STRING));
        Assert.assertEquals(APP_2_NAME, app2.getName());
        Assert.assertEquals(APP_2_USER, app2.getUser());
        Assert.assertEquals(APP_2_VERSION, app2.getVersion());
        Assert.assertEquals(APP_2_STATUS, app2.getStatus());
        Assert.assertThat(app2.getType().orElseGet(RandomSuppliers.STRING), Matchers.is(APP_2_TYPE));
        Assert.assertEquals(4, app2.getTags().size());
        Assert.assertEquals(2, app2.getConfigs().size());
        Assert.assertEquals(1, app2.getDependencies().size());

        final Application app3 = this.appService.getApplication(APP_3_ID);
        Assert.assertEquals(APP_3_ID, app3.getId().orElseGet(RandomSuppliers.STRING));
        Assert.assertEquals(APP_3_NAME, app3.getName());
        Assert.assertEquals(APP_3_USER, app3.getUser());
        Assert.assertEquals(APP_3_VERSION, app3.getVersion());
        Assert.assertEquals(APP_3_STATUS, app3.getStatus());
        Assert.assertThat(app3.getType().orElseGet(RandomSuppliers.STRING), Matchers.is(APP_3_TYPE));
        Assert.assertEquals(3, app3.getTags().size());
        Assert.assertEquals(1, app3.getConfigs().size());
        Assert.assertEquals(2, app3.getDependencies().size());
    }