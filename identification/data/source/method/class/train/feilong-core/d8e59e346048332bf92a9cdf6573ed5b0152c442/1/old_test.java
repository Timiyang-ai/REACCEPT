@Test
    public void readAllPropertiesToMap(){
        Map<String, String> map = ResourceBundleUtil.readAllPropertiesToMap(BASE_NAME, Locale.CHINA);
        LOGGER.debug(JsonUtil.format(map));
    }