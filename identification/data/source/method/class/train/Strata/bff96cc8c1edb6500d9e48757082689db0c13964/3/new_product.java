public default HolidayCalendar combinedWith(HolidayCalendar other) {
    if (this.equals(other)) {
      return this;
    }
    if (other == HolidayCalendars.NO_HOLIDAYS) {
      return this;
    }
    return new CombinedHolidayCalendar(this, other);
  }