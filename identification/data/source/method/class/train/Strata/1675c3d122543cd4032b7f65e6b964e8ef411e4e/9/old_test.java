  @Test
  public void test_resolve() {
    CapitalIndexedBond base = sut();
    LocalDate[] unAdjDates = new LocalDate[] {LocalDate.of(2008, 1, 13), LocalDate.of(2008, 7, 13),
        LocalDate.of(2009, 1, 13), LocalDate.of(2009, 7, 13), LocalDate.of(2010, 1, 13)};
    CapitalIndexedBondPaymentPeriod[] periodic = new CapitalIndexedBondPaymentPeriod[4];
    for (int i = 0; i < 4; ++i) {
      LocalDate start = SCHEDULE_ADJ.adjust(unAdjDates[i], REF_DATA);
      LocalDate end = SCHEDULE_ADJ.adjust(unAdjDates[i + 1], REF_DATA);
      LocalDate detachment = EX_COUPON.adjust(end, REF_DATA);
      RateComputation comp = RATE_CALC.createRateComputation(end);
      periodic[i] = CapitalIndexedBondPaymentPeriod.builder()
          .currency(USD)
          .startDate(start)
          .endDate(end)
          .unadjustedStartDate(unAdjDates[i])
          .unadjustedEndDate(unAdjDates[i + 1])
          .detachmentDate(detachment)
          .realCoupon(COUPONS[i])
          .rateComputation(comp)
          .notional(NOTIONAL)
          .build();
    }
    CapitalIndexedBondPaymentPeriod nominalExp =
        periodic[3].withUnitCoupon(periodic[0].getStartDate(), periodic[0].getUnadjustedStartDate());
    ResolvedCapitalIndexedBond expected = ResolvedCapitalIndexedBond.builder()
        .securityId(SECURITY_ID)
        .dayCount(ACT_ACT_ISDA)
        .legalEntityId(LEGAL_ENTITY)
        .nominalPayment(nominalExp)
        .periodicPayments(periodic)
        .frequency(SCHEDULE.getFrequency())
        .rollConvention(SCHEDULE.calculatedRollConvention())
        .settlementDateOffset(SETTLE_OFFSET)
        .yieldConvention(US_IL_REAL)
        .rateCalculation(base.getRateCalculation())
        .build();
    assertThat(base.resolve(REF_DATA)).isEqualTo(expected);
  }