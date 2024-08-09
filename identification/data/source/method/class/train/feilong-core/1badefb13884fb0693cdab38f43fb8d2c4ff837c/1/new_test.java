@Test
    public void testReadAllPropertiesToMap(){
        LOGGER.debug(JsonUtil.format(readPropertiesToMap(BASE_NAME, Locale.CHINA)));
    }