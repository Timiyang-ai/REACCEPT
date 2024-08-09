  @Test
  public void test_accruedInterest() {
    // settle before start
    LocalDate settleDate1 = START_DATE.minusDays(5);
    double accruedInterest1 = PRICER.accruedInterest(PRODUCT, settleDate1);
    assertThat(accruedInterest1).isEqualTo(0d);
    // settle between endDate and endDate -lag
    LocalDate settleDate2 = date(2015, 10, 8);
    double accruedInterest2 = PRICER.accruedInterest(PRODUCT, settleDate2);
    assertThat(accruedInterest2).isCloseTo(-4.0 / 365.0 * FIXED_RATE * NOTIONAL, offset(EPS));
    // normal
    LocalDate settleDate3 = date(2015, 4, 18); // not adjusted
    ResolvedFixedCouponBond product = FixedCouponBond.builder()
        .securityId(SECURITY_ID)
        .dayCount(DAY_COUNT)
        .fixedRate(FIXED_RATE)
        .legalEntityId(ISSUER_ID)
        .currency(EUR)
        .notional(NOTIONAL)
        .accrualSchedule(PERIOD_SCHEDULE)
        .settlementDateOffset(DATE_OFFSET)
        .yieldConvention(YIELD_CONVENTION)
        .exCouponPeriod(DaysAdjustment.NONE)
        .build()
        .resolve(REF_DATA);
    double accruedInterest3 = PRICER.accruedInterest(product, settleDate3);
    assertThat(accruedInterest3).isCloseTo(6.0 / 365.0 * FIXED_RATE * NOTIONAL, offset(EPS));
  }