public double accruedInterest(ResolvedCapitalIndexedBond bond, LocalDate settlementDate) {
    if (bond.getUnadjustedStartDate().isAfter(settlementDate)) {
      return 0d;
    }
    double notional = bond.getNotional();
    CapitalIndexedBondPaymentPeriod period = bond.findPeriod(settlementDate)
        .orElseThrow(() -> new IllegalArgumentException("Date outside range of bond"));
    LocalDate previousAccrualDate = period.getUnadjustedStartDate();
    double realCoupon = period.getRealCoupon();
    double couponPerYear = bond.getFrequency().eventsPerYear();
    double rate = realCoupon * couponPerYear;
    double accruedInterest = bond.yearFraction(previousAccrualDate, settlementDate) * rate * notional;
    double result = 0d;
    if (bond.hasExCouponPeriod() && !settlementDate.isBefore(period.getDetachmentDate())) {
      result = accruedInterest - notional * rate * period.getYearFraction();
    } else {
      result = accruedInterest;
    }
    return result;
  }