@Test
    public void testContainsIgnoreCase(){
        assertEquals(false, StringUtils.containsIgnoreCase(null, ""));
        LOGGER.debug(StringUtils.containsIgnoreCase(TEXT, null) + "");
        LOGGER.debug(StringUtils.containsIgnoreCase(TEXT, "") + "");
        LOGGER.debug(StringUtils.containsIgnoreCase(TEXT, "feilong") + "");
        LOGGER.debug(StringUtils.containsIgnoreCase(TEXT, "feilong1") + "");
        LOGGER.debug(StringUtils.containsIgnoreCase(TEXT, "feiLong") + "");
        assertEquals(true, StringUtils.containsIgnoreCase("jiiiiiinxin.feilong", "Xin"));
    }