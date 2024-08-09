public HolidayCalendarId combinedWith(HolidayCalendarId other) {
    ArgChecker.notNull(other, "other");
    if (this.equals(other)) {
      return this;
    }
    if (this == HolidayCalendarIds.NO_HOLIDAYS) {
      return other;
    }
    if (other == HolidayCalendarIds.NO_HOLIDAYS) {
      return this;
    }
    return new HolidayCalendarId(name + "+" + other.name);
  }