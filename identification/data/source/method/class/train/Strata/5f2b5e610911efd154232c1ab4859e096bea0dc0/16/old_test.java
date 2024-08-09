  @Test
  public void test_resolve_cap() {
    IborRateCalculation rateCalc = IborRateCalculation.builder()
        .index(EUR_EURIBOR_3M)
        .fixingRelativeTo(FixingRelativeTo.PERIOD_END)
        .fixingDateOffset(EUR_EURIBOR_3M.getFixingDateOffset())
        .build();
    IborCapFloorLeg base = IborCapFloorLeg.builder()
        .calculation(rateCalc)
        .capSchedule(CAP)
        .notional(NOTIONAL)
        .paymentDateOffset(PAYMENT_OFFSET)
        .paymentSchedule(SCHEDULE)
        .payReceive(RECEIVE)
        .build();
    LocalDate[] unadjustedDates =
        new LocalDate[] {START, START.plusMonths(3), START.plusMonths(6), START.plusMonths(9), START.plusMonths(12)};
    IborCapletFloorletPeriod[] periods = new IborCapletFloorletPeriod[4];
    for (int i = 0; i < 4; ++i) {
      LocalDate start = BUSS_ADJ.adjust(unadjustedDates[i], REF_DATA);
      LocalDate end = BUSS_ADJ.adjust(unadjustedDates[i + 1], REF_DATA);
      double yearFraction = EUR_EURIBOR_3M.getDayCount().relativeYearFraction(start, end);
      periods[i] = IborCapletFloorletPeriod.builder()
          .caplet(CAP.getInitialValue())
          .currency(EUR)
          .startDate(start)
          .endDate(end)
          .unadjustedStartDate(unadjustedDates[i])
          .unadjustedEndDate(unadjustedDates[i + 1])
          .paymentDate(PAYMENT_OFFSET.adjust(end, REF_DATA))
          .notional(NOTIONALS[i])
          .iborRate(IborRateComputation.of(EUR_EURIBOR_3M, rateCalc.getFixingDateOffset().adjust(end, REF_DATA), REF_DATA))
          .yearFraction(yearFraction)
          .build();
    }
    ResolvedIborCapFloorLeg expected = ResolvedIborCapFloorLeg.builder()
        .capletFloorletPeriods(periods)
        .payReceive(RECEIVE)
        .build();
    ResolvedIborCapFloorLeg computed = base.resolve(REF_DATA);
    assertThat(computed).isEqualTo(expected);
  }