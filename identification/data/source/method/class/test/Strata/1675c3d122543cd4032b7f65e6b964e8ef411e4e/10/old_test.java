  @Test
  public void test_accruedInterest() {
    LocalDate refDate = LocalDate.of(2014, 6, 10);
    double computed = PRODUCT.accruedInterest(refDate);
    Schedule sch = SCHEDULE.createSchedule(REF_DATA).toUnadjusted();
    CapitalIndexedBondPaymentPeriod period = PRODUCT.getPeriodicPayments().get(16);
    double factor = ACT_ACT_ICMA.relativeYearFraction(period.getUnadjustedStartDate(), refDate, sch);
    assertThat(computed).isCloseTo(factor * REAL_COUPON_VALUE * NOTIONAL * 2d, offset(TOL * REAL_COUPON_VALUE * NOTIONAL));
  }