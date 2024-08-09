@Test(expectedExceptions=NullPointerException.class, groups={"tck"})
    public void test_query_null() {
        TEST_2007_07_15_12_30_40_987654321.query(null);
    }