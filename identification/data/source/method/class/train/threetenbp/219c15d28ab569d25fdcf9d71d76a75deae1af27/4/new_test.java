@Test(groups={"tck"})
    public void test_atDate() {
        LocalTime t = LocalTime.of(11, 30);
        assertEquals(t.atDate(LocalDate.of(2012, 6, 30)), LocalDateTime.of(2012, 6, 30, 11, 30));
    }