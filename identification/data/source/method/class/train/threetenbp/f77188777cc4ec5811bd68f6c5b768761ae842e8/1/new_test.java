@Test(groups={"tck"})
    public void test_plus_adjuster() {
        Period p = Period.ofTime(0, 0, 62, 3);
        LocalTime t = TEST_12_30_40_987654321.plus(p);
        assertEquals(t, LocalTime.of(12, 31, 42, 987654324));
    }