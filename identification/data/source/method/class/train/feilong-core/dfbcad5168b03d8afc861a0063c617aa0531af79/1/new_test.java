@Test
    public void testToSafeArrayValueMap(){
        LOGGER.info(JsonUtil.format(ParamUtil.toSafeArrayValueMap("a=1&b=2&a", CharsetType.UTF8)));
        LOGGER.info(JsonUtil.format(ParamUtil.toSafeArrayValueMap("a=&b=2&a", CharsetType.UTF8)));
        LOGGER.info(JsonUtil.format(ParamUtil.toSafeArrayValueMap("a=1&b=2&a=5", CharsetType.UTF8)));
        LOGGER.info(JsonUtil.format(ParamUtil.toSafeArrayValueMap("a=1=2&b=2&a=5", CharsetType.UTF8)));
    }