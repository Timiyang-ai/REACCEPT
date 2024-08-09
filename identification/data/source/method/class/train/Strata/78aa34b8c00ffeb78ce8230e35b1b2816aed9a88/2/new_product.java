@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof ImmutableHolidayCalendar) {
      return id.equals(((ImmutableHolidayCalendar) obj).id);
    }
    return false;
  }