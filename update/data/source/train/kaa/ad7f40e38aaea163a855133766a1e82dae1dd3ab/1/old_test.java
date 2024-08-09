@SuppressWarnings("static-access")
    @Test
    public void testCreateSdkGenerator() throws Exception {
        SdkGeneratorFactory factory = new SdkGeneratorFactory();
        SdkGenerator generator = factory.createSdkGenerator(SdkPlatform.JAVA);
        Assert.assertNotNull(generator);
        generator = factory.createSdkGenerator(SdkPlatform.CPP);
        Assert.assertNull(generator);
    }