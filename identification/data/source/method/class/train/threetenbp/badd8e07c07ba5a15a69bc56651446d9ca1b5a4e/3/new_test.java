@Test(groups={"tck"})
    public void test_minus_Adjuster() {
        TemporalSubtractor p = Period.ofTime(0, 0, 62, 3);
        LocalTime t = TEST_12_30_40_987654321.minus(p);
        assertEquals(t, LocalTime.of(12, 29, 38, 987654318));
    }