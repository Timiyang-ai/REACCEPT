  @Test
  public void test_dirtyPriceFromStandardYield() {
    double yield = 0.0175;
    LocalDate standardSettle = SETTLE_OFFSET.adjust(VALUATION, REF_DATA);
    double computed = PRICER.dirtyPriceFromStandardYield(PRODUCT, RATES_PROVIDER, standardSettle, yield);
    Schedule sch = SCHEDULE.createSchedule(REF_DATA).toUnadjusted();
    CapitalIndexedBondPaymentPeriod period = PRODUCT.getPeriodicPayments().get(16);
    double factorPeriod =
        ACT_ACT_ICMA.relativeYearFraction(period.getUnadjustedStartDate(), period.getUnadjustedEndDate(), sch);
    double factorSpot = ACT_ACT_ICMA.relativeYearFraction(period.getUnadjustedStartDate(), standardSettle, sch);
    double factorToNext = (factorPeriod - factorSpot) / factorPeriod;
    double dscFactor = 1d / (1d + 0.5 * yield);
    double expected = Math.pow(dscFactor, 3);
    for (int i = 0; i < 4; ++i) {
      expected += REAL_COUPON_VALUE * Math.pow(dscFactor, i);
    }
    expected *= Math.pow(dscFactor, factorToNext);
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }