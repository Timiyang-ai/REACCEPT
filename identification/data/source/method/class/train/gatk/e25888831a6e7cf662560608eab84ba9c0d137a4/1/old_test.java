@Test(dataProvider = "provideForTestGetAndValidateConfigFileContents")
    public void testGetAndValidateConfigFileContents(final Path configFilePath, final Properties expected) {
        final Properties properties = XsvLocatableTableCodec.getAndValidateConfigFileContents(configFilePath);
        Assert.assertEquals(properties, expected);
    }