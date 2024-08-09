@Test(groups={"tck"})
    public void test_serialization_format() throws ClassNotFoundException, IOException {
        ZonedDateTime zdt = LocalDateTime.of(2012, 9, 16, 22, 17, 59, 470 * 1000000).atZone(ZoneId.of("Europe/London"));
        assertEqualsSerialisedForm(zdt);
    }