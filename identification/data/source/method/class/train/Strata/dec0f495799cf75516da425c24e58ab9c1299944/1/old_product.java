public double yearFraction(LocalDate startDate, LocalDate endDate) {
    ArgChecker.inOrderOrEqual(getUnadjustedStartDate(), startDate, "bond.unadjustedStartDate", "startDate");
    ArgChecker.inOrderOrEqual(startDate, endDate, "startDate", "endDate");
    ArgChecker.inOrderOrEqual(endDate, getUnadjustedEndDate(), "endDate", "bond.unadjustedEndDate");
    // bond day counts often need ScheduleInfo
    ScheduleInfo info = new ScheduleInfo() {
      @Override
      public LocalDate getStartDate() {
        return getUnadjustedStartDate();
      }

      @Override
      public LocalDate getEndDate() {
        return getUnadjustedEndDate();
      }

      @Override
      public Frequency getFrequency() {
        return frequency;
      }

      @Override
      public LocalDate getPeriodEndDate(LocalDate date) {
        return periodicPayments.stream()
            .filter(p -> p.contains(date))
            .map(p -> p.getUnadjustedEndDate())
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Date is not contained in any period"));
      }

      @Override
      public boolean isEndOfMonthConvention() {
        return rollConvention == RollConventions.EOM;
      }
    };
    return dayCount.yearFraction(startDate, endDate, info);
  }