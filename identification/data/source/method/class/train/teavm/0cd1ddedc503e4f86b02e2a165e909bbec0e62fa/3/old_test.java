    @Test
    public void test_getInstance() {
        // test getInstance(Locale)
        Calendar usCalendar = Calendar.getInstance(Locale.US);
        //Calendar ch_calendar = Calendar.getInstance(Locale.CHINESE);
        assertEquals(Calendar.SUNDAY, usCalendar
                .getFirstDayOfWeek());
        //assertEquals(Calendar.MONDAY, ch_calendar
        //        .getFirstDayOfWeek());

        // test getInstance(Locale, TimeZone)
        Calendar gmtCalendar = Calendar.getInstance(TimeZone
                .getTimeZone("GMT"), Locale.US);
        assertEquals(TimeZone.getTimeZone("GMT"),
                gmtCalendar.getTimeZone());
        Calendar estCalendar = Calendar.getInstance(TimeZone
                .getTimeZone("EST"), Locale.US);
        assertEquals(TimeZone.getTimeZone("EST")
                .getID(), estCalendar.getTimeZone().getID());
    }