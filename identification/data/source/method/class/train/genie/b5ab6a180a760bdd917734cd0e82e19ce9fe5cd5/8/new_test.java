@Test
    public void testSetConfigs() {
        Assert.assertNotNull(this.c.getConfigs());
        Assert.assertTrue(this.c.getConfigs().isEmpty());
        final FileEntity config = new FileEntity();
        config.setFile("s3://netflix.configFile");
        final Set<FileEntity> configs = Sets.newHashSet(config);
        this.c.setConfigs(configs);
        Assert.assertEquals(configs, this.c.getConfigs());

        this.c.setConfigs(null);
        Assert.assertThat(this.c.getConfigs(), Matchers.empty());
    }