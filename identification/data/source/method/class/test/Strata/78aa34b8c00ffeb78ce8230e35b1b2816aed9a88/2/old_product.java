@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ImmutableHolidayCalendar other = (ImmutableHolidayCalendar) obj;
      return JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getHolidays(), other.getHolidays()) &&
          JodaBeanUtils.equal(getWeekendDays(), other.getWeekendDays());
    }
    return false;
  }