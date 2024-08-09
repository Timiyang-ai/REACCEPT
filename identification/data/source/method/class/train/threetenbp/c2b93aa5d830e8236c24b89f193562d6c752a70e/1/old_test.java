@Test
    public void test_toString_formatter() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("H m s");
        String t = OffsetTime.of(LocalTime.of(11, 30), OFFSET_PONE).toString(f);
        assertEquals(t, "11 30 0");
    }