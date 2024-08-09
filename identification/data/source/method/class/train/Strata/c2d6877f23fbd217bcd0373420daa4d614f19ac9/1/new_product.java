public int eventsPerYear() {
    if (eventsPerYear == -1) {
      throw new IllegalArgumentException("Unable to calculate events per year: " + this);
    }
    return eventsPerYear;
  }