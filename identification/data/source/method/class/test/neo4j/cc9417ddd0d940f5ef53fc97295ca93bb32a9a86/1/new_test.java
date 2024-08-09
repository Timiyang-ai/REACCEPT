    @Test
    public void getConfigOptions()
    {
        Map<String,String> config = stringMap(
                TestConfig.integer.name(), "123",
                TestConfig.string.name(), "bah",
                TestConfig.oldString.name(), "moo",
                TestConfig.dynamic.name(), "foo" );

        TestConfig testSettings = new TestConfig();

        List<ConfigOptions> options = testSettings.getConfigOptions();

        assertEquals( 4, options.size() );

        SettingGroup<?> integerSetting = options.get( 0 ).settingGroup();
        assertEquals( 1, integerSetting.values( emptyMap() ).get( TestConfig.integer.name() ) );
        assertEquals( 123, integerSetting.values( config ).get( TestConfig.integer.name() ) );
        assertEquals( Optional.empty(), integerSetting.description() );
        assertFalse( integerSetting.deprecated() );
        assertFalse( integerSetting.dynamic() );
        assertEquals( Optional.empty(), integerSetting.replacement() );

        SettingGroup<?> stringSetting = options.get( 1 ).settingGroup();
        assertEquals( "bob", stringSetting.values( emptyMap() ).get( TestConfig.string.name() ) );
        assertEquals( "bah", stringSetting.values( config ).get( TestConfig.string.name() ) );
        assertEquals( "A string setting", stringSetting.description().get() );
        assertFalse( stringSetting.deprecated() );
        assertFalse( stringSetting.dynamic() );
        assertEquals( Optional.empty(), stringSetting.replacement() );

        SettingGroup<?> oldStringSetting = options.get( 2 ).settingGroup();
        assertEquals( "tim", oldStringSetting.values( emptyMap() ).get( TestConfig.oldString.name() ) );
        assertEquals( "moo", oldStringSetting.values( config ).get( TestConfig.oldString.name() ) );
        assertEquals( "A deprecated string setting", oldStringSetting.description().get() );
        assertTrue( oldStringSetting.deprecated() );
        assertFalse( oldStringSetting.dynamic() );
        assertEquals( TestConfig.string.name(), oldStringSetting.replacement().get() );

        SettingGroup<?> dynamicSetting = options.get( 3 ).settingGroup();
        assertEquals( "defaultDynamic", dynamicSetting.values( emptyMap() ).get( TestConfig.dynamic.name() ) );
        assertEquals( "foo", dynamicSetting.values( config ).get( TestConfig.dynamic.name() ) );
        assertEquals( "A dynamic string setting", dynamicSetting.description().get() );
        assertFalse( dynamicSetting.deprecated() );
        assertTrue( dynamicSetting.dynamic() );
        assertEquals( Optional.empty(), dynamicSetting.replacement() );
    }