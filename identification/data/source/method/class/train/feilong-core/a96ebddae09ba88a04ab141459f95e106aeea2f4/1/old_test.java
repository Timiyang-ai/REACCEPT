@Test
    public void isContainIgnoreCase(){
        LOGGER.info(StringUtil.isContainIgnoreCase(null, "") + "");
        LOGGER.info(StringUtil.isContainIgnoreCase(TEXT, null) + "");
        LOGGER.info(StringUtil.isContainIgnoreCase(TEXT, "") + "");
        LOGGER.info(StringUtil.isContainIgnoreCase(TEXT, "feilong") + "");
        LOGGER.info(StringUtil.isContainIgnoreCase(TEXT, "feilong1") + "");
        LOGGER.info(StringUtil.isContainIgnoreCase(TEXT, "feiLong") + "");
    }