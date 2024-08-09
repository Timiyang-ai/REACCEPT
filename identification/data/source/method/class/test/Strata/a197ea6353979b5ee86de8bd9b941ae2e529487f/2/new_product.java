public double accruedInterest(ResolvedFixedCouponBond bond, LocalDate settlementDate) {
    if (bond.getUnadjustedStartDate().isAfter(settlementDate)) {
      return 0d;
    }
    double notional = bond.getNotional();
    FixedCouponBondPaymentPeriod period = bond.findPeriod(settlementDate)
        .orElseThrow(() -> new IllegalArgumentException("Date outside range of bond"));
    LocalDate previousAccrualDate = period.getUnadjustedStartDate();
    LocalDate paymentDate = period.getEndDate();
    double fixedRate = bond.getFixedRate();
    double accruedInterest = bond.yearFraction(previousAccrualDate, settlementDate) * fixedRate * notional;
    DaysAdjuster exCouponDays = bond.getExCouponPeriod();
    double result = 0d;
    if (exCouponDays.getDays() != 0 && settlementDate.isAfter(exCouponDays.adjust(paymentDate))) {
      result = accruedInterest - notional * fixedRate * period.getYearFraction();
    } else {
      result = accruedInterest;
    }
    return result;
  }