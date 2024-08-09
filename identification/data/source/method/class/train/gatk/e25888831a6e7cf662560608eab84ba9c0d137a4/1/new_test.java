@Test(dataProvider = "provideForTestGetAndValidateConfigFileContents")
    public void testGetAndValidateConfigFileContents(final Path configFilePath, final Properties expected) {
        final Pair<Boolean, Properties> validityAndPropertiesPair = XsvLocatableTableCodec.getAndValidateConfigFileContentsOnPath(configFilePath, false);
        final boolean isValid = validityAndPropertiesPair.getLeft();
        final Properties properties = validityAndPropertiesPair.getRight();

        Assert.assertEquals( isValid, true );
        Assert.assertEquals(properties, expected);
    }