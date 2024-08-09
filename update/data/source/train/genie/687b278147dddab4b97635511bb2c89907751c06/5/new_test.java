@Test
    public void testSetConfigs() {
        Assert.assertNull(this.a.getConfigs());
        final Set<String> configs = new HashSet<String>();
        configs.add("s3://netflix.configFile");
        this.a.setConfigs(configs);
        Assert.assertEquals(configs, this.a.getConfigs());
    }