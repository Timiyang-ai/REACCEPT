@Test
    public void testIsToday(){
        assertSame(false, isToday(toDate("2016-06-16 22:59:00", COMMON_DATE_AND_TIME)));
        assertSame(true, isToday(new Date()));
    }