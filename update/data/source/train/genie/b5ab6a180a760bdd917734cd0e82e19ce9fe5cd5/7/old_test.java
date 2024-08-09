@Test
    public void testSetConfigs() {
        Assert.assertNotNull(this.c.getConfigs());
        Assert.assertTrue(this.c.getConfigs().isEmpty());
        final Set<String> configs = Sets.newHashSet("s3://netflix.configFile");
        this.c.setConfigs(configs);
        Assert.assertEquals(configs, this.c.getConfigs());

        this.c.setConfigs(null);
        Assert.assertThat(this.c.getConfigs(), Matchers.empty());
    }