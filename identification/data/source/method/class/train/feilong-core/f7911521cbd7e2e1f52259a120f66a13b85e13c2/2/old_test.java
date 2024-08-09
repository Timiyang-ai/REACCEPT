@Test
    public void testGetValue1(){
        LOGGER.debug(ResourceBundleUtil.getValue(BASE_NAME, "config_date_hour", Locale.ENGLISH));
    }