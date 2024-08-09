public double accruedInterest(ResolvedFixedCouponBond bond, LocalDate settlementDate) {
    if (bond.getUnadjustedStartDate().isAfter(settlementDate)) {
      return 0d;
    }
    double notional = bond.getNotional();
    FixedCouponBondPaymentPeriod period = bond.findPeriod(settlementDate)
        .orElseThrow(() -> new IllegalArgumentException("Date outside range of bond"));
    LocalDate previousAccrualDate = period.getUnadjustedStartDate();
    double fixedRate = bond.getFixedRate();
    double accruedInterest = bond.yearFraction(previousAccrualDate, settlementDate) * fixedRate * notional;
    double result = 0d;
    if (settlementDate.isAfter(period.getDetachmentDate())) {
      result = accruedInterest - notional * fixedRate * period.getYearFraction();
    } else {
      result = accruedInterest;
    }
    return result;
  }