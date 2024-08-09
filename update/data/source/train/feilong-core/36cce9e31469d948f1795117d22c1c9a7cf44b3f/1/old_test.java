@Test
    public void testContainsIgnoreCase(){
        assertEquals(false, StringUtil.containsIgnoreCase(null, ""));
        LOGGER.debug(StringUtil.containsIgnoreCase(TEXT, null) + "");
        LOGGER.debug(StringUtil.containsIgnoreCase(TEXT, "") + "");
        LOGGER.debug(StringUtil.containsIgnoreCase(TEXT, "feilong") + "");
        LOGGER.debug(StringUtil.containsIgnoreCase(TEXT, "feilong1") + "");
        LOGGER.debug(StringUtil.containsIgnoreCase(TEXT, "feiLong") + "");
        assertEquals(true, StringUtil.containsIgnoreCase("jiiiiiinxin.feilong", "Xin"));
    }