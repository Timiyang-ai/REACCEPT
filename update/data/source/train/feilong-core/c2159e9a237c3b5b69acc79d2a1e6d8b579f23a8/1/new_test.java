@Test
    public void testGetDayOfYear(){
        assertEquals(1, DateUtil.getDayOfYear(DateUtil.string2Date("2013-01-01", DatePattern.COMMON_DATE)));
        LOGGER.debug(DateUtil.getDayOfYear(NOW) + "");
    }