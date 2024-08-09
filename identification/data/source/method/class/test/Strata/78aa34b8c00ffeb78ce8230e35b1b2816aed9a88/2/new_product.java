@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof ImmutableHolidayCalendar) {
      return name.equals(((ImmutableHolidayCalendar) obj).name);
    }
    return false;
  }