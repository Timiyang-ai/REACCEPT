@Test
    public void testReadAllPropertiesToMap(){
        LOGGER.debug(JsonUtil.format(readAllPropertiesToMap(BASE_NAME, Locale.CHINA)));
    }