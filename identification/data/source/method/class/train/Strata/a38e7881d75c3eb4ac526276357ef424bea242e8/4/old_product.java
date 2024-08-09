public RateCalculationSwapLeg toLeg(
      LocalDate startDate,
      LocalDate endDate,
      PayReceive payReceive,
      BusinessDayAdjustment businessDayAdjustment,
      DaysAdjustment paymentDateOffset,
      double notional) {

    return RateCalculationSwapLeg
        .builder()
        .payReceive(payReceive)
        .accrualSchedule(PeriodicSchedule.builder()
            .startDate(startDate)
            .endDate(endDate)
            .frequency(Frequency.TERM)
            .businessDayAdjustment(businessDayAdjustment)
            .build())
        .paymentSchedule(PaymentSchedule.builder()
            .paymentFrequency(Frequency.TERM)
            .paymentDateOffset(paymentDateOffset)
            .build())
        .calculation(InflationRateCalculation.builder()
            .index(index)
            .indexCalculationMethod(indexCalculationMethod)
            .lag(lag)
            .build())
        .notionalSchedule(NotionalSchedule.of(getCurrency(), notional))
        .build();
  }