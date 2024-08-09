@Test(groups={"tck"})
    public void test_with_LocalDate() {
        OffsetDate base = OffsetDate.of(2008, 6, 30, OFFSET_PONE);
        OffsetDate test = base.with(Year.of(2007));
        assertEquals(test.toLocalDate(), LocalDate.of(2007, 6, 30));
    }