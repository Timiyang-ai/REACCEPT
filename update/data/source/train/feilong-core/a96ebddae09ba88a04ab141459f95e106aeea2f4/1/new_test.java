@Test
    public void isContainIgnoreCase(){
        LOGGER.info(StringUtil.containsIgnoreCase(null, "") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, null) + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "feilong") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "feilong1") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "feiLong") + "");
    }