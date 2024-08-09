@Test
    public void testResetYearEnd(){
        Calendar resetYearEnd = CalendarUtil.resetYearEnd(DateUtil.toCalendar(new Date()));
        LOGGER.debug(CalendarUtil.toString(resetYearEnd, DatePattern.COMMON_DATE_AND_TIME_WITH_MILLISECOND));
    }