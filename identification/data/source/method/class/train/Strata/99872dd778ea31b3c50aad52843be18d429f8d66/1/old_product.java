@Override
  public HolidayCalendar resolve(ReferenceData refData) {
    return resolver.apply(refData);
  }