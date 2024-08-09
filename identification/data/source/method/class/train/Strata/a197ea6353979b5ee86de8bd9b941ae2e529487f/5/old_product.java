public double accruedInterest(ResolvedFixedCouponBond bond, LocalDate settlementDate) {
    Schedule scheduleAdjusted = bond.getPeriodicSchedule().createSchedule();
    Schedule scheduleUnadjusted = scheduleAdjusted.toUnadjusted();
    if (scheduleUnadjusted.getPeriods().get(0).getStartDate().isAfter(settlementDate)) {
      return 0d;
    }
    double notional = bond.getNotional();
    int couponIndex = couponIndex(scheduleUnadjusted, settlementDate);
    SchedulePeriod schedulePeriod = scheduleUnadjusted.getPeriod(couponIndex);
    LocalDate previousAccrualDate = schedulePeriod.getStartDate();
    LocalDate paymentDate = scheduleAdjusted.getPeriod(couponIndex).getEndDate();
    double fixedRate = bond.getFixedRate();
    double accruedInterest = bond.getDayCount()
        .yearFraction(previousAccrualDate, settlementDate, scheduleUnadjusted) * fixedRate * notional;
    DaysAdjustment exCouponDays = bond.getExCouponPeriod();
    double result = 0d;
    if (exCouponDays.getDays() != 0 && settlementDate.isAfter(exCouponDays.adjust(paymentDate))) {
      result = accruedInterest - notional * fixedRate *
          schedulePeriod.yearFraction(bond.getDayCount(), scheduleUnadjusted);
    } else {
      result = accruedInterest;
    }
    return result;
  }