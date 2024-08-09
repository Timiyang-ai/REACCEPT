@Test
    public void testGetApplication() throws GenieException {
        final Application app = this.appService.getApplication(APP_1_ID);
        Assert.assertEquals(APP_1_ID, app.getId());
        Assert.assertEquals(APP_1_NAME, app.getMetadata().getName());
        Assert.assertEquals(APP_1_USER, app.getMetadata().getUser());
        Assert.assertEquals(APP_1_VERSION, app.getMetadata().getVersion().orElseGet(RandomSuppliers.STRING));
        Assert.assertEquals(APP_1_STATUS, app.getMetadata().getStatus());
        Assert.assertFalse(app.getMetadata().getType().isPresent());
        Assert.assertEquals(1, app.getMetadata().getTags().size());
        Assert.assertEquals(2, app.getResources().getConfigs().size());
        Assert.assertEquals(2, app.getResources().getDependencies().size());

        final Application app2 = this.appService.getApplication(APP_2_ID);
        Assert.assertEquals(APP_2_ID, app2.getId());
        Assert.assertEquals(APP_2_NAME, app2.getMetadata().getName());
        Assert.assertEquals(APP_2_USER, app2.getMetadata().getUser());
        Assert.assertEquals(APP_2_VERSION, app2.getMetadata().getVersion().orElseGet(RandomSuppliers.STRING));
        Assert.assertEquals(APP_2_STATUS, app2.getMetadata().getStatus());
        Assert.assertThat(app2.getMetadata().getType().orElseGet(RandomSuppliers.STRING), Matchers.is(APP_2_TYPE));
        Assert.assertEquals(2, app2.getMetadata().getTags().size());
        Assert.assertEquals(2, app2.getResources().getConfigs().size());
        Assert.assertEquals(1, app2.getResources().getDependencies().size());

        final Application app3 = this.appService.getApplication(APP_3_ID);
        Assert.assertEquals(APP_3_ID, app3.getId());
        Assert.assertEquals(APP_3_NAME, app3.getMetadata().getName());
        Assert.assertEquals(APP_3_USER, app3.getMetadata().getUser());
        Assert.assertEquals(APP_3_VERSION, app3.getMetadata().getVersion().orElseGet(RandomSuppliers.STRING));
        Assert.assertEquals(APP_3_STATUS, app3.getMetadata().getStatus());
        Assert.assertThat(app3.getMetadata().getType().orElseGet(RandomSuppliers.STRING), Matchers.is(APP_3_TYPE));
        Assert.assertEquals(1, app3.getMetadata().getTags().size());
        Assert.assertEquals(1, app3.getResources().getConfigs().size());
        Assert.assertEquals(2, app3.getResources().getDependencies().size());
    }