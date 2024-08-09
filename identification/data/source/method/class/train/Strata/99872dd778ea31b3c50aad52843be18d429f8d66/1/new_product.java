@Override
  public HolidayCalendar resolve(ReferenceData refData) {
    return refData.getValue(this);
  }