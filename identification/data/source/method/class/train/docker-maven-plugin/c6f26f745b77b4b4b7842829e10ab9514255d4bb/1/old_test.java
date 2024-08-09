    @Test
    public void initAndValidate() throws Exception {
        List<ImageConfiguration> configs = Arrays.asList(new ImageConfiguration.Builder().name("test").build());
        String api = ConfigHelper.initAndValidate(configs, "v1.16", ConfigHelper.NameFormatter.IDENTITY, null);
        assertEquals("v1.16",api);
    }