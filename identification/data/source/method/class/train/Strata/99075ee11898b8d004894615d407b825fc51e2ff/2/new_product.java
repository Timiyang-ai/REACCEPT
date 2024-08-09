public HolidayCalendarId getResultCalendar() {
    HolidayCalendarId cal = adjustment.getCalendar();
    if (cal == HolidayCalendarIds.NO_HOLIDAYS) {
      cal = calendar;
    }
    return cal;
  }