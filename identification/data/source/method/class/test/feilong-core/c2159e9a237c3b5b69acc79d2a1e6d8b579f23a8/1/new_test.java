@Test
    public void testGetHourOfYear(){
        assertEquals(0, DateUtil.getHourOfYear(DateUtil.string2Date("2013-01-01 00:00:05", DatePattern.COMMON_DATE_AND_TIME)));
        assertEquals(31 * 24, DateUtil.getHourOfYear(DateUtil.string2Date("2016-02-01 00:00:05", DatePattern.COMMON_DATE_AND_TIME)));
        assertEquals(24, DateUtil.getHourOfYear(DateUtil.string2Date("2013-01-02 00:00:05", DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateUtil.getHourOfYear(DateUtil.string2Date("2013-01-05 12:00:05", DatePattern.COMMON_DATE_AND_TIME)) + "");

        LOGGER.debug(DateUtil.getHourOfYear(DateUtil.string2Date("2013-09-16 11:42:22", DatePattern.COMMON_DATE_AND_TIME)) + "");
        LOGGER.debug(DateUtil.getHourOfYear(NOW) + "");
    }