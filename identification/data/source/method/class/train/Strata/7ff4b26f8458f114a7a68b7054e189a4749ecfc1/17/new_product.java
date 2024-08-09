public default HolidayCalendar combineWith(HolidayCalendar other) {
    ArgChecker.notNull(other, "other");
    if (this.equals(other)) {
      return this;
    }
    if (other == HolidayCalendars.NONE) {
      return this;
    }
    return new HolidayCalendars.Combined(this, other);
  }