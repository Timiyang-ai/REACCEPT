@Test
    public void testGetDayOfYear(){
        Date date1 = DateUtil.string2Date("2013-01-05", DatePattern.COMMON_DATE);
        LOGGER.debug(DateUtil.getDayOfYear(date1) + "");
        LOGGER.debug(DateUtil.getDayOfYear(NOW) + "");
    }