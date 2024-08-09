public HolidayCalendar getResultCalendar() {
    HolidayCalendar cal = adjustment.getCalendar();
    if (cal == HolidayCalendars.NO_HOLIDAYS) {
      cal = calendar;
    }
    return cal;
  }