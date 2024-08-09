  @Test
  public void test_accruedYearFraction() {
    double eps = 1.0e-15;
    ResolvedCds test = ResolvedCds.builder()
        .buySell(BUY)
        .dayCount(ACT_360)
        .legalEntityId(LEGAL_ENTITY)
        .paymentOnDefault(ACCRUED_PREMIUM)
        .protectionStart(BEGINNING)
        .paymentPeriods(PAYMENTS)
        .protectionEndDate(PAYMENTS.get(PAYMENTS.size() - 1).getEffectiveEndDate())
        .settlementDateOffset(SETTLE_DAY_ADJ)
        .stepinDateOffset(STEPIN_DAY_ADJ)
        .build();
    double accStart = test.accruedYearFraction(START_DATE.minusDays(1));
    double accNextMinusOne = test.accruedYearFraction(START_DATE.plusMonths(3).minusDays(1));
    double accNext = test.accruedYearFraction(START_DATE.plusMonths(3));
    double accNextOne = test.accruedYearFraction(START_DATE.plusMonths(3).plusDays(1));
    double accMod = test.accruedYearFraction(START_DATE.plusYears(1));
    double accEnd = test.accruedYearFraction(END_DATE);
    double accEndOne = test.accruedYearFraction(END_DATE.plusDays(1));
    assertThat(accStart).isEqualTo(0d);
    assertThat(accNext).isEqualTo(0d);
    assertThat(accNextMinusOne)
        .isCloseTo(ACT_360.relativeYearFraction(START_DATE, START_DATE.plusMonths(3).minusDays(1)), offset(eps));
    assertThat(accNextOne).isCloseTo(1d / 360d, offset(eps));
    // 2.x
    assertThat(accMod).isCloseTo(0.24722222222222223, offset(eps));
    assertThat(accEnd).isCloseTo(0.25555555555555554, offset(eps));
    assertThat(accEndOne).isCloseTo(0.25833333333333336, offset(eps));
  }