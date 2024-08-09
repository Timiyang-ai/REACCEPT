@Test
    public void testGetIntervalDayList1(){
        Date fromDate = DateUtil.string2Date(FROMSTRING, DatePattern.COMMON_DATE_AND_TIME);
        Date toDate = DateUtil.string2Date(TOSTRING, DatePattern.COMMON_DATE_AND_TIME);
        LOGGER.debug(JsonUtil.format(DateExtensionUtil.getIntervalDayList(fromDate, toDate)));
    }