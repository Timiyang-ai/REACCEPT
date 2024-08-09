@Test
    public void testSetEnvPropFile() {
        Assert.assertNull(this.a.getEnvPropFile());
        final String propFile = "s3://netflix.propFile";
        this.a.setEnvPropFile(propFile);
        Assert.assertEquals(propFile, this.a.getEnvPropFile());
    }