public double accruedInterest(CapitalIndexedBond product, LocalDate settlementDate) {
    Schedule scheduleAdjusted = product.getPeriodicSchedule().createSchedule();
    Schedule scheduleUnadjusted = scheduleAdjusted.toUnadjusted();
    if (scheduleUnadjusted.getPeriods().get(0).getStartDate().isAfter(settlementDate)) {
      return 0d;
    }
    double notional = product.getNotional();
    int couponIndex = couponIndex(scheduleUnadjusted, settlementDate);
    SchedulePeriod schedulePeriod = scheduleUnadjusted.getPeriod(couponIndex);
    LocalDate previousAccrualDate = schedulePeriod.getStartDate();
    LocalDate paymentDate = scheduleAdjusted.getPeriod(couponIndex).getEndDate();

    double realCoupon = product.getRateCalculation().getGearing().orElse(ValueSchedule.ALWAYS_1)
        .resolveValues(scheduleAdjusted.getPeriods()).get(couponIndex);
    double couponPerYear = product.getPeriodicSchedule().getFrequency().eventsPerYear();
    double accruedInterest = product.getDayCount()
        .yearFraction(previousAccrualDate, settlementDate, scheduleUnadjusted) * realCoupon * couponPerYear * notional;
    DaysAdjustment exCouponDays = product.getExCouponPeriod();
    double result = 0d;
    if (exCouponDays.getDays() != 0 && !settlementDate.isBefore(exCouponDays.adjust(paymentDate))) {
      result = accruedInterest - notional * realCoupon * couponPerYear *
          schedulePeriod.yearFraction(product.getDayCount(), scheduleUnadjusted);
    } else {
      result = accruedInterest;
    }
    return result;
  }