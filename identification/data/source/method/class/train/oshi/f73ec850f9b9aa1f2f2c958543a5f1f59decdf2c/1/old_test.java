@Test
    public void testFormatDate() {
        assertEquals("null", FormatUtil.formatDate(null));
        try {
            assertEquals("01-01-2017", FormatUtil.formatDate(DATE_FORMAT.parse("01-01-2017")));
        } catch (ParseException e) {
            assertEquals("01-01-2017", e.getMessage());
        }
    }