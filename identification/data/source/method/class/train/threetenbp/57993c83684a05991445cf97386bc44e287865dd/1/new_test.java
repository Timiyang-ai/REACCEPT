@Test(groups={"tck"})
    public void test_print_basicIsoDate() {
        DateTimeCalendrical test = LocalDateTime.of(2008, 6, 3, 11, 5, 30);
        assertEquals(DateTimeFormatters.basicIsoDate().print(test), "20080603");
    }