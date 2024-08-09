  @Test
  public void test_toLeg() {
    InflationRateSwapLegConvention base = InflationRateSwapLegConvention.of(GB_HICP, LAG_3M, MONTHLY, BDA_MOD_FOLLOW);
    LocalDate startDate = LocalDate.of(2015, 5, 5);
    LocalDate endDate = LocalDate.of(2020, 5, 5);
    RateCalculationSwapLeg test = base.toLeg(
        startDate,
        endDate,
        PAY,
        NOTIONAL_2M);

    RateCalculationSwapLeg expected = RateCalculationSwapLeg.builder()
        .payReceive(PAY)
        .accrualSchedule(PeriodicSchedule.builder()
            .frequency(Frequency.TERM)
            .startDate(startDate)
            .endDate(endDate)
            .businessDayAdjustment(BDA_MOD_FOLLOW)
            .build())
        .paymentSchedule(PaymentSchedule.builder()
            .paymentFrequency(Frequency.TERM)
            .paymentDateOffset(DaysAdjustment.NONE)
            .build())
        .notionalSchedule(NotionalSchedule.of(GBP, NOTIONAL_2M))
        .calculation(InflationRateCalculation.of(GB_HICP, 3, MONTHLY))
        .build();
    assertThat(test).isEqualTo(expected);
  }