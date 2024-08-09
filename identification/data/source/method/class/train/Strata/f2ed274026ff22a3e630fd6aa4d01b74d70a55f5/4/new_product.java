public HolidayCalendarId combinedWith(HolidayCalendarId other) {
    if (this == other) {
      return this;
    }
    if (this == HolidayCalendarIds.NO_HOLIDAYS) {
      return ArgChecker.notNull(other, "other");
    }
    if (other == HolidayCalendarIds.NO_HOLIDAYS) {
      return this;
    }
    return HolidayCalendarId.of(name + '+' + other.name);
  }