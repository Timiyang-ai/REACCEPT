@Test(dataProvider="calendars")
    public void test_from_TemporalAccessor(Chronology chrono) {
        LocalDate refDate = LocalDate.of(2013, 1, 1);
        ChronoLocalDate date = chrono.date(refDate);
        ChronoLocalDate test1 = ChronoLocalDate.from(date);
        assertEquals(test1, date);
        ChronoLocalDate test2 = ChronoLocalDate.from(date.atTime(LocalTime.of(12, 30)));
        assertEquals(test2, date);
        ChronoLocalDate test3 = ChronoLocalDate.from(date.atTime(LocalTime.of(12, 30)).atZone(ZoneOffset.UTC));
        assertEquals(test3, date);
    }