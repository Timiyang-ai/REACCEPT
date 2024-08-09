public int eventsPerYear() {
    if (isTerm()) {
      return 0;
    }
    long months = period.toTotalMonths();
    int days = period.getDays();
    if (isMonthBased()) {
      if (12 % months == 0) {
        return (int) (12 / months);
      }
    } else if (months == 0 && 364 % days == 0) {
      return (int) (364 / days);
    }
    throw new IllegalArgumentException("Unable to calculate events per year: " + this);
  }