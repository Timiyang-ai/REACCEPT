@Test
    public void test_parseBest_firstOption() throws Exception {
        DateTimeFormatter test = DateTimeFormatter.ofPattern("yyyy-MM[-dd]");
        TemporalAccessor result = test.parseBest("2011-06-30", LocalDate.FROM, YearMonth.FROM);
        assertEquals(result, LocalDate.of(2011, 6, 30));
    }