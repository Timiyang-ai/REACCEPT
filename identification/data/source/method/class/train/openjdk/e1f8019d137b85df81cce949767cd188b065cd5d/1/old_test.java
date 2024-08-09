@Test(groups={"tck"})
    public void test_toString_formatter() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("y M d");
        String t = LocalDate.of(2010, 12, 3).toString(f);
        assertEquals(t, "2010 12 3");
    }