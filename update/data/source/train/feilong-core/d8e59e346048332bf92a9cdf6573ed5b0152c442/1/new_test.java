@Test
    public void readAllPropertiesToMap(){
        LOGGER.debug(JsonUtil.format(ResourceBundleUtil.readAllPropertiesToMap(BASE_NAME, Locale.CHINA)));
    }