@Test
    public void testFormatDate() {
        assertEquals("null", FormatUtil.formatDate(null));
        assertEquals("01/01/2017", FormatUtil.formatDate(LocalDate.parse("2017-01-01")));
        assertEquals(null, FormatUtil.formatStringDate(null));
        assertEquals(null, FormatUtil.formatStringDate("Unparseable"));
        assertEquals(LocalDate.parse("2017-01-01"), FormatUtil.formatStringDate("01/01/2017"));
    }