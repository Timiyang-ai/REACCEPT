@Test
    public void test_getText() {
        assertEquals(DayOfWeek.MONDAY.getDisplayName(TextStyle.SHORT, Locale.US), "Mon");
    }