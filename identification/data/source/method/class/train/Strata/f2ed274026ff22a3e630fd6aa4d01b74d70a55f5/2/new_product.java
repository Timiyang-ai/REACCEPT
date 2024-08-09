public static ImmutableHolidayCalendar combined(ImmutableHolidayCalendar cal1, ImmutableHolidayCalendar cal2) {
    // do not override combinedWith(), as this is too slow
    if (cal1 == cal2) {
      return ArgChecker.notNull(cal1, "cal1");
    }
    HolidayCalendarId newId = cal1.id.combinedWith(cal2.id);

    // use slow version if lookup arrays do not overlap
    int endYear1 = cal1.startYear + cal1.lookup.length / 12;
    int endYear2 = cal2.startYear + cal2.lookup.length / 12;
    if (endYear1 < cal2.startYear || endYear2 < cal1.startYear) {
      ImmutableSortedSet<LocalDate> newHolidays =
          ImmutableSortedSet.copyOf(Iterables.concat(cal1.getHolidays(), cal2.getHolidays()));
      ImmutableSet<DayOfWeek> newWeekends =
          ImmutableSet.copyOf(Iterables.concat(cal1.getWeekendDays(), cal2.getWeekendDays()));
      return of(newId, newHolidays, newWeekends);
    }

    // merge calendars using bitwise operations
    // figure out which has the lower start year and use that as the base
    boolean cal1Lower = cal1.startYear <= cal2.startYear;
    int[] lookup1 = cal1Lower ? cal1.lookup : cal2.lookup;
    int[] lookup2 = cal1Lower ? cal2.lookup : cal1.lookup;
    int newStartYear = cal1Lower ? cal1.startYear : cal2.startYear;
    int otherStartYear = cal1Lower ? cal2.startYear : cal1.startYear;
    // copy base array and map data from the other on top
    int newSize = Math.max(lookup1.length, lookup2.length + (otherStartYear - newStartYear) * 12);
    int offset = (otherStartYear - newStartYear) * 12;
    int[] newLookup = Arrays.copyOf(lookup1, newSize);
    for (int i = 0; i < lookup2.length; i++) {
      newLookup[i + offset] &= lookup2[i]; // use & because 1 = business day (not holiday)
    }
    int newWeekends = cal1.weekends | cal2.weekends; // use | because 1 = weekend day
    return new ImmutableHolidayCalendar(newId, newWeekends, newStartYear, newLookup, false);
  }