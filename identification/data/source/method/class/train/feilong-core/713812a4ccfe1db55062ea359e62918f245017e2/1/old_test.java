@Test
    public void testGetIntervalDayList(){
        List<Date> dates = DateExtensionUtil.getIntervalDayList(FROMSTRING, TOSTRING, DatePattern.COMMON_DATE_AND_TIME);
        LOGGER.debug(JsonUtil.format(dates));
    }