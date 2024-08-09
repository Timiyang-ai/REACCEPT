@Test
    public void testGetIntervalDayList1(){
        Date fromDate = DateUtil.toDate(FROMSTRING, DatePattern.COMMON_DATE_AND_TIME);
        Date toDate = DateUtil.toDate(TOSTRING, DatePattern.COMMON_DATE_AND_TIME);
        LOGGER.debug(JsonUtil.format(DateExtensionUtil.getIntervalDayList(fromDate, toDate)));
    }