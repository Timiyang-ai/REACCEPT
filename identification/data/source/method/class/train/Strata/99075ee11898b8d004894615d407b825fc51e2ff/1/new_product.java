public DaysAdjustment normalized() {
    if (days == 0) {
      if (calendar != HolidayCalendarIds.NO_HOLIDAYS) {
        return DaysAdjustment.ofCalendarDays(days, adjustment);
      }
      return this;
    }
    if (calendar.equals(adjustment.getCalendar())) {
      return DaysAdjustment.ofBusinessDays(days, calendar);
    }
    return this;
  }