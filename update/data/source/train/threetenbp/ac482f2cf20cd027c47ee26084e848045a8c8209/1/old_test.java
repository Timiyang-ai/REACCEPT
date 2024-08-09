@Test(expectedExceptions=NullPointerException.class, groups={"tck"})
    public void test_with_null_adjustDate() {
        TEST_2007_07_15_12_30_40_987654321.with(new MockDateAdjusterReturnsNull());
    }