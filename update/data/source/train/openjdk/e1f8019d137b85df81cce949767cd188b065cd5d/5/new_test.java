@Test
    public void test_atTime_OffsetTime() {
        LocalDate t = LocalDate.of(2008, 6, 30);
        assertEquals(t.atTime(OffsetTime.of(11, 30, 0, 0, OFFSET_PONE)), OffsetDateTime.of(2008, 6, 30, 11, 30, 0, 0, OFFSET_PONE));
    }