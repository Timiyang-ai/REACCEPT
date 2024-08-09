@Test(groups={"tck"})
    public void test_getText() {
        assertEquals(DayOfWeek.MONDAY.getDisplayName(TextStyle.SHORT, Locale.US), "Mon");
    }