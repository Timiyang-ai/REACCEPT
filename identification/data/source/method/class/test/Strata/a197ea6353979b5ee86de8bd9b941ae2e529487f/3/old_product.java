public double accruedInterest(FixedCouponBond product, LocalDate settlementDate) {
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
    double fixedRate = product.getFixedRate();
    double accruedInterest = product.getDayCount()
        .yearFraction(previousAccrualDate, settlementDate, scheduleUnadjusted) * fixedRate * notional;
    DaysAdjustment exCouponDays = product.getExCouponPeriod();
    double result = 0d;
    if (exCouponDays.getDays() != 0 && settlementDate.isAfter(exCouponDays.adjust(paymentDate))) {
      result = accruedInterest - notional * fixedRate *
          schedulePeriod.yearFraction(product.getDayCount(), scheduleUnadjusted);
    } else {
      result = accruedInterest;
    }
    return result;
  }