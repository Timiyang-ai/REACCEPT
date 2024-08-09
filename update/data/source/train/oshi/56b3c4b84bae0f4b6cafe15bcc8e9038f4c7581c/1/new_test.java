@Test
    public void testFormatDate() throws ParseException {
        assertEquals("null", FormatUtil.formatDate(null));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("01/01/2017", FormatUtil.formatDate(formatter.parse("2017-01-01")));
        assertEquals(null, FormatUtil.formatStringDate(null));
        assertEquals(null, FormatUtil.formatStringDate("Unparseable"));
        assertEquals(formatter.parse("2017-01-01"), FormatUtil.formatStringDate("01/01/2017"));
    }