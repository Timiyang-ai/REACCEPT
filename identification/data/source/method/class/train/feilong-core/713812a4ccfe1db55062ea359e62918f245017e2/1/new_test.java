@Test
    public void testGetIntervalDayList(){
        LOGGER.debug(JsonUtil.format(DateExtensionUtil.getIntervalDayList(FROMSTRING, TOSTRING, DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(JsonUtil.format(DateExtensionUtil.getIntervalDayList(TOSTRING, FROMSTRING, DatePattern.COMMON_DATE_AND_TIME)));
    }