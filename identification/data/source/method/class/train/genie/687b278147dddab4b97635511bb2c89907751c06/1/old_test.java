@Test
    public void testSetConfigs() {
        final Application a = new Application();
        Assert.assertNull(a.getConfigs());
        final Set<String> configs = new HashSet<String>();
        configs.add("s3://netflix.configFile");
        a.setConfigs(configs);
        Assert.assertEquals(configs, a.getConfigs());
    }