public HolidayCalendar combineWith(HolidayCalendar other) {
    ArgChecker.notNull(other, "other");
    if (this.equals(other)) {
      return this;
    }
    LocalDateRange newRange = range.union(other.range);  // exception if no overlap
    ImmutableSortedSet<LocalDate> newHolidays =
        ImmutableSortedSet.copyOf(Iterables.concat(holidays, other.holidays))
            .subSet(newRange.getStart(), newRange.getEndExclusive());
    ImmutableSet<DayOfWeek> newWeekends = ImmutableSet.copyOf(Iterables.concat(weekendDays, other.weekendDays));
    return new HolidayCalendar(newHolidays, newWeekends);
  }