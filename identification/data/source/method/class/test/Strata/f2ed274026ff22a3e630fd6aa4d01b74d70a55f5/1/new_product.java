public HolidayCalendarId combinedWith(HolidayCalendarId other) {
    ArgChecker.notNull(other, "other");
    if (this == other) {
      return this;
    }
    if (this == HolidayCalendarIds.NO_HOLIDAYS) {
      return other;
    }
    if (other == HolidayCalendarIds.NO_HOLIDAYS) {
      return this;
    }
    return HolidayCalendarId.of(name + '+' + other.name);
  }