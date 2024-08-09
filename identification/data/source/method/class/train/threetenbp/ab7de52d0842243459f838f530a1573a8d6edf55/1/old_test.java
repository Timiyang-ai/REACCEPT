@Test(groups={"tck"})
    public void test_appendFraction_3arg() throws Exception {
        builder.appendFraction(MINUTE_OF_HOUR, 1, 9);
        DateTimeFormatter f = builder.toFormatter();
        assertEquals(f.toString(), "Fraction(MinuteOfHour,1,9)");
    }