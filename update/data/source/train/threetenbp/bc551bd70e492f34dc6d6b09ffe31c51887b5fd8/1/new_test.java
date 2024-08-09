@Test(groups={"tck"})
    public void test_toString_formatter() {
        DateTimeFormatter f = DateTimeFormatters.pattern("y M d");
        String t = OffsetDate.of(2010, 12, 3, OFFSET_PONE).toString(f);
        assertEquals(t, "2010 12 3");
    }