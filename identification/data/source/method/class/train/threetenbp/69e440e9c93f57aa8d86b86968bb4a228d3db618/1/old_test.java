@Test
    public void test_minus_adjuster() {
        Period p = Period.ofTime(0, 0, 62, 3);
        LocalDateTime t = TEST_2007_07_15_12_30_40_987654321.minus(p);
        assertEquals(t, LocalDateTime.of(2007, 7, 15, 12, 29, 38, 987654318));
    }