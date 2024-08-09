@Test(dataProvider="plus", groups={"tck"})
    public void test_plus_long(int base, long amount, int expected) {
        assertEquals(DayOfWeek.of(base).plus(amount), DayOfWeek.of(expected));
    }