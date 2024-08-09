public static ImmutableHolidayCalendar combined(ImmutableHolidayCalendar cal1, ImmutableHolidayCalendar cal2) {
    // do not override combinedWith(), as this is too slow
    if (cal1 == cal2) {
      return ArgChecker.notNull(cal1, "cal1");
    }
    LocalDateRange newRange = cal1.range.union(cal2.range);  // exception if no overlap
    ImmutableSortedSet<LocalDate> newHolidays =
        ImmutableSortedSet.copyOf(Iterables.concat(cal1.holidays, cal2.holidays))
            .subSet(newRange.getStart(), newRange.getEndExclusive());
    ImmutableSet<DayOfWeek> newWeekends = ImmutableSet.copyOf(Iterables.concat(cal1.weekendDays, cal2.weekendDays));
    return new ImmutableHolidayCalendar(cal1.id.combinedWith(cal2.id), newHolidays, newWeekends);
  }