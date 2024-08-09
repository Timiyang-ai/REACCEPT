@Test(dataProvider="calendars")
    public void test_date_comparisons(Chronology chrono) {
        List<ChronoLocalDate> dates = new ArrayList<>();

        ChronoLocalDate date = chrono.date(LocalDate.of(1900, 1, 1));

        // Insert dates in order, no duplicates
        if (chrono != JapaneseChronology.INSTANCE) {
            dates.add(date.minus(1000, ChronoUnit.YEARS));
            dates.add(date.minus(100, ChronoUnit.YEARS));
        }
        dates.add(date.minus(10, ChronoUnit.YEARS));
        dates.add(date.minus(1, ChronoUnit.YEARS));
        dates.add(date.minus(1, ChronoUnit.MONTHS));
        dates.add(date.minus(1, ChronoUnit.WEEKS));
        dates.add(date.minus(1, ChronoUnit.DAYS));
        dates.add(date);
        dates.add(date.plus(1, ChronoUnit.DAYS));
        dates.add(date.plus(1, ChronoUnit.WEEKS));
        dates.add(date.plus(1, ChronoUnit.MONTHS));
        dates.add(date.plus(1, ChronoUnit.YEARS));
        dates.add(date.plus(10, ChronoUnit.YEARS));
        dates.add(date.plus(100, ChronoUnit.YEARS));
        dates.add(date.plus(1000, ChronoUnit.YEARS));

        // Check these dates against the corresponding dates for every calendar
        for (Chronology[] clist : data_of_calendars()) {
            List<ChronoLocalDate> otherDates = new ArrayList<>();
            Chronology chrono2 = clist[0];
            if (chrono2 == JapaneseChronology.INSTANCE) {
                continue;
            }
            for (ChronoLocalDate d : dates) {
                otherDates.add(chrono2.date(d));
            }

            // Now compare  the sequence of original dates with the sequence of converted dates
            for (int i = 0; i < dates.size(); i++) {
                ChronoLocalDate a = dates.get(i);
                for (int j = 0; j < otherDates.size(); j++) {
                    ChronoLocalDate b = otherDates.get(j);
                    int cmp = ChronoLocalDate.DATE_COMPARATOR.compare(a, b);
                    if (i < j) {
                        assertTrue(cmp < 0, a + " compare " + b);
                        assertEquals(a.isBefore(b), true, a + " isBefore " + b);
                        assertEquals(a.isAfter(b), false, a + " isAfter " + b);
                        assertEquals(a.isEqual(b), false, a + " isEqual " + b);
                    } else if (i > j) {
                        assertTrue(cmp > 0, a + " compare " + b);
                        assertEquals(a.isBefore(b), false, a + " isBefore " + b);
                        assertEquals(a.isAfter(b), true, a + " isAfter " + b);
                        assertEquals(a.isEqual(b), false, a + " isEqual " + b);
                    } else {
                        assertTrue(cmp == 0, a + " compare " + b);
                        assertEquals(a.isBefore(b), false, a + " isBefore " + b);
                        assertEquals(a.isAfter(b), false, a + " isAfter " + b);
                        assertEquals(a.isEqual(b), true, a + " isEqual " + b);
                    }
                }
            }
        }
    }