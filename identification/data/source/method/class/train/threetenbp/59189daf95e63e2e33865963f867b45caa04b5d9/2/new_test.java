@Test(groups={"tck"})
    public void test_withMonth() {
        assertEquals(MonthDay.of(6, 30).withMonth(1), MonthDay.of(1, 30));
    }