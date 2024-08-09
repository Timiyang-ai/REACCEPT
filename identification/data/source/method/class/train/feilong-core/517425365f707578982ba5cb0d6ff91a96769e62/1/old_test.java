@Test
    public void testGetPropertiesMapForLog(){
        LOGGER.debug(JsonUtil.format(SystemUtil.getPropertiesMapForLog()));
    }