  @Test
  public void test_resolve() {
    BusinessDayAdjustment bussAdj = BusinessDayAdjustment.of(FOLLOWING, SAT_SUN);
    ResolvedCdsIndex test = PRODUCT.resolve(REF_DATA);
    int nDates = 44;
    LocalDate[] dates = new LocalDate[nDates];
    for (int i = 0; i < nDates; ++i) {
      dates[i] = START_DATE.plusMonths(3 * i);
    }
    List<CreditCouponPaymentPeriod> payments = new ArrayList<>(nDates - 1);
    for (int i = 0; i < nDates - 2; ++i) {
      LocalDate start = i == 0 ? dates[i] : bussAdj.adjust(dates[i], REF_DATA);
      LocalDate end = bussAdj.adjust(dates[i + 1], REF_DATA);
      payments.add(CreditCouponPaymentPeriod.builder()
          .startDate(start)
          .endDate(end)
          .unadjustedStartDate(dates[i])
          .unadjustedEndDate(dates[i + 1])
          .effectiveStartDate(start.minusDays(1))
          .effectiveEndDate(end.minusDays(1))
          .paymentDate(end).currency(USD)
          .notional(NOTIONAL)
          .fixedRate(COUPON)
          .yearFraction(ACT_360.relativeYearFraction(start, end))
          .build());
    }
    LocalDate start = bussAdj.adjust(dates[nDates - 2], REF_DATA);
    LocalDate end = dates[nDates - 1];
    payments.add(CreditCouponPaymentPeriod.builder()
        .startDate(start)
        .endDate(end.plusDays(1))
        .unadjustedStartDate(dates[nDates - 2])
        .unadjustedEndDate(end)
        .effectiveStartDate(start.minusDays(1))
        .effectiveEndDate(end)
        .paymentDate(bussAdj.adjust(end, REF_DATA))
        .currency(USD)
        .notional(NOTIONAL)
        .fixedRate(COUPON)
        .yearFraction(ACT_360.relativeYearFraction(start, end.plusDays(1)))
        .build());
    ResolvedCdsIndex expected = ResolvedCdsIndex.builder()
        .buySell(BUY)
        .cdsIndexId(INDEX_ID)
        .legalEntityIds(LEGAL_ENTITIES)
        .dayCount(ACT_360)
        .paymentOnDefault(ACCRUED_PREMIUM)
        .paymentPeriods(payments)
        .protectionStart(BEGINNING)
        .protectionEndDate(END_DATE)
        .settlementDateOffset(SETTLE_DAY_ADJ)
        .stepinDateOffset(STEPIN_DAY_ADJ)
        .build();
    assertThat(test).isEqualTo(expected);
  }