@Test
    public void testGetEnvMapForLog(){
        LOGGER.debug(JsonUtil.format(SystemUtil.getEnvMapForLog()));
    }