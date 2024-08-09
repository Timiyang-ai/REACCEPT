@Test
    public void testSetEnvPropFile() {
        Assert.assertNull(this.a.getSetupFile());
        final String propFile = "s3://netflix.propFile";
        this.a.setSetupFile(propFile);
        Assert.assertEquals(propFile, this.a.getSetupFile());
    }