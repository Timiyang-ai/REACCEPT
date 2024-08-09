public double accruedYearFraction(LocalDate stepinDate) {
    if (stepinDate.isBefore(getAccrualStartDate())) {
      return 0d;
    }
    if (stepinDate.isEqual(getAccrualEndDate())) {
      return periodicPayments.get(periodicPayments.size() - 1).getYearFraction();
    }
    CreditCouponPaymentPeriod period = findPeriod(stepinDate)
        .orElseThrow(() -> new IllegalArgumentException("Date outside range"));
    return dayCount.relativeYearFraction(period.getStartDate(), stepinDate);
  }