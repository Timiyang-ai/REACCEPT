@Test
    public void test_lengthOfYear() {
        assertEquals(YearMonth.of(2007, 6).lengthOfYear(), 365);
        assertEquals(YearMonth.of(2008, 6).lengthOfYear(), 366);
    }