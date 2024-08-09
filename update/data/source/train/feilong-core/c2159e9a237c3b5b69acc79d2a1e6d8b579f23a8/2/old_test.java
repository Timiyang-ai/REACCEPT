@Test
    public void testGetIntervalForView(){
        Date now = DateUtil.string2Date("2011-05-19 11:31:25.456", DatePattern.COMMON_DATE_AND_TIME);
        now = new Date();
        Date date = DateUtil.string2Date("2012-12-03 00:00:00", DatePattern.COMMON_DATE_AND_TIME);
        LOGGER.debug(DateExtensionUtil.getIntervalForView(now, date));
        LOGGER.debug(DateUtil.getIntervalTime(now, date) + "");
    }