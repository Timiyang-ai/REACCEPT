@Test
    public void testResetYearEnd(){
        Date date = DateUtil.toDate("2016", DatePattern.yyyy);
        Calendar resetYearEnd = CalendarUtil.resetYearEnd(DateUtil.toCalendar(date));
        assertEquals("2016-12-31 23:59:59.999", CalendarUtil.toString(resetYearEnd, DatePattern.COMMON_DATE_AND_TIME_WITH_MILLISECOND));
    }