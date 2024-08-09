@Test
    public void test_getOffset_long() {
		// Test for method int java.util.TimeZone.getOffset(long time)

        // test on subclass SimpleTimeZone
        TimeZone st1 = TimeZone.getTimeZone("EST");
        long time1 = new GregorianCalendar(1998, Calendar.NOVEMBER, 11)
                .getTimeInMillis();
        assertEquals("T1. Incorrect offset returned",
                -(5 * ONE_HOUR), st1.getOffset(time1));

        long time2 = new GregorianCalendar(1998, Calendar.JUNE, 11)
                .getTimeInMillis();
        st1 = TimeZone.getTimeZone("EST");
        assertEquals("T2. Incorrect offset returned",
                -(5 * ONE_HOUR), st1.getOffset(time2));

        // test on subclass Support_TimeZone, an instance with daylight savings
        TimeZone tz1 = new Support_TimeZone(-5 * ONE_HOUR, true);
        assertEquals("T3. Incorrect offset returned, ",
                -(5 * ONE_HOUR), tz1.getOffset(time1));
        assertEquals("T4. Incorrect offset returned, ",
                -(4 * ONE_HOUR), tz1.getOffset(time2));

        // an instance without daylight savings
        tz1 = new Support_TimeZone(3 * ONE_HOUR, false);
        assertEquals("T5. Incorrect offset returned, ",
                (3 * ONE_HOUR), tz1.getOffset(time1));
        assertEquals("T6. Incorrect offset returned, ",
                (3 * ONE_HOUR), tz1.getOffset(time2));
    }