@Override
  public HolidayCalendar combineWith(HolidayCalendar other) {
    ArgChecker.notNull(other, "other");
    if (this.equals(other)) {
      return this;
    }
    if (other == HolidayCalendars.NO_HOLIDAYS) {
      return this;
    }
    if (other instanceof ImmutableHolidayCalendar) {
      ImmutableHolidayCalendar otherCal = (ImmutableHolidayCalendar) other;
      LocalDateRange newRange = range.union(otherCal.range);  // exception if no overlap
      ImmutableSortedSet<LocalDate> newHolidays =
          ImmutableSortedSet.copyOf(Iterables.concat(holidays, otherCal.holidays))
              .subSet(newRange.getStart(), newRange.getEndExclusive());
      ImmutableSet<DayOfWeek> newWeekends = ImmutableSet.copyOf(Iterables.concat(weekendDays, otherCal.weekendDays));
      String combinedName = name + "+" + otherCal.name;
      return new ImmutableHolidayCalendar(combinedName, newHolidays, newWeekends);
    }
    return HolidayCalendar.super.combineWith(other);
  }