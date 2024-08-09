@Test
    public void testSetConfigs() {
        Assert.assertNotNull(this.c.getConfigs());
        this.c.setConfigs(this.configs);
        Assert.assertEquals(this.configs, this.c.getConfigs());
    }