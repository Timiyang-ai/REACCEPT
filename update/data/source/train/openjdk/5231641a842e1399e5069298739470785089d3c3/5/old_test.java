@Test(dataProvider="weekFields")
    public void test_parse_resolve_localizedWomDow(DayOfWeek firstDayOfWeek, int minDays) {
        LocalDate date = LocalDate.of(2012, 12, 15);
        WeekFields week = WeekFields.of(firstDayOfWeek, minDays);
        TemporalField dowField = week.dayOfWeek();
        TemporalField womField = week.weekOfMonth();

        for (int i = 1; i <= 15; i++) {
            // Test that with dayOfWeek and Week of month it computes the date
            DateTimeFormatter f = new DateTimeFormatterBuilder()
                    .appendValue(YEAR).appendLiteral('-')
                    .appendValue(MONTH_OF_YEAR).appendLiteral('-')
                    .appendValue(womField).appendLiteral('-')
                    .appendValue(dowField).toFormatter();
            String str = date.getYear() + "-" + date.getMonthValue() + "-" +
                    date.get(womField) + "-" + date.get(dowField);
            LocalDate parsed = LocalDate.parse(str, f);
            assertEquals(parsed, date, " :: " + str + " " + i);

            date = date.plusDays(1);
        }
    }