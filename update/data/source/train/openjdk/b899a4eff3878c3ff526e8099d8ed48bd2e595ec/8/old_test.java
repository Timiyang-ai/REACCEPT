@Test(groups={"tck"})
    public void test_getText() {
        assertEquals(DayOfWeek.MONDAY.getText(TextStyle.SHORT, Locale.US), "Mon");
    }