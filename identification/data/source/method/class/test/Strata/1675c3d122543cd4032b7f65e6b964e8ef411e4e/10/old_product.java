public double accruedInterest(ResolvedCapitalIndexedBond bond, LocalDate settlementDate) {
    Schedule scheduleAdjusted = bond.getPeriodicSchedule().createSchedule(REF_DATA);
    Schedule scheduleUnadjusted = scheduleAdjusted.toUnadjusted();
    if (scheduleUnadjusted.getPeriods().get(0).getStartDate().isAfter(settlementDate)) {
      return 0d;
    }
    double notional = bond.getNotional();
    CapitalIndexedBondPaymentPeriod period = bond.findPeriod(settlementDate)
        .orElseThrow(() -> new IllegalArgumentException("Date outside range of bond"));

    int couponIndex = couponIndex(scheduleUnadjusted, settlementDate);
    SchedulePeriod schedulePeriod = scheduleUnadjusted.getPeriod(couponIndex);
    LocalDate previousAccrualDate = schedulePeriod.getStartDate();
    double realCoupon = bond.getRateCalculation().getGearing().orElse(ValueSchedule.ALWAYS_1)
        .resolveValues(scheduleAdjusted.getPeriods()).get(couponIndex);
    double couponPerYear = bond.getPeriodicSchedule().getFrequency().eventsPerYear();
    double accruedInterest = bond.getDayCount()
        .yearFraction(previousAccrualDate, settlementDate, scheduleUnadjusted) * realCoupon * couponPerYear * notional;
    double result = 0d;
    if (bond.hasExCouponPeriod() && !settlementDate.isBefore(period.getDetachmentDate())) {
      result = accruedInterest - notional * realCoupon * couponPerYear *
          schedulePeriod.yearFraction(bond.getDayCount(), scheduleUnadjusted);
    } else {
      result = accruedInterest;
    }
    return result;
  }