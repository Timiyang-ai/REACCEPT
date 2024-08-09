@Test
    public void testParseDateWithLeniency() throws Exception {
        final GregorianCalendar cal = new GregorianCalendar(1998, 6, 30);
        final String dateStr = "02 942, 1996";
        final String[] parsers = new String[] {"MM DDD, yyyy"};
        
        Date date = DateUtils.parseDate(dateStr, parsers);
        assertEquals(cal.getTime(), date);
        
        try {
            date = DateUtils.parseDateStrictly(dateStr, parsers);
            fail();
        } catch (final ParseException ex) {}
    }