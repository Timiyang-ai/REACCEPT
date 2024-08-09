public void test_atOffset() {
        LocalDate t = LocalDate.date(2008, 6, 30);
        assertEquals(t.atOffset(OFFSET_PTWO), OffsetDate.date(2008, 6, 30, OFFSET_PTWO));
    }