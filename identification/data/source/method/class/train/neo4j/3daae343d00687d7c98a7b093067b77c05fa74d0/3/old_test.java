    @Test
    public void augmentDefaults()
    {
        Config config = Config();
        assertEquals( "Hello, World!", config.get( MySettingsWithDefaults.hello ) );
        config.augmentDefaults( MySettingsWithDefaults.hello, "new default" );
        assertEquals( "new default", config.get( MySettingsWithDefaults.hello ) );
    }