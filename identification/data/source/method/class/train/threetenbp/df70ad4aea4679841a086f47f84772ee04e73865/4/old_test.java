@Test(dataProvider="dateProvider")
    public void test_matchesCalendrical(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
        LocalDate date = LocalDate.date(startYear, startMonth, startDay);
        long offset = LocalDate.date(endYear, endMonth, endDay).toModifiedJulianDays() - date.toModifiedJulianDays();
        int week = 0;

        for (long l = 0; l < offset; l++) {
            if (l % 7 == 0) {
                week++;
            }

            assertTrue(WeekOfWeekBasedYear.weekOfWeekBasedYear(week).matchesCalendrical(date));
            assertFalse(WeekOfWeekBasedYear.weekOfWeekBasedYear(week).matchesCalendrical(date.plusDays(7)));
            date = date.plusDays(1);
        }
    }