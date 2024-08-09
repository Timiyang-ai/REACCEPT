public RateCalculationSwapLeg toLeg(
      LocalDate startDate,
      LocalDate endDate,
      PayReceive payReceive,
      double notional,
      double fixedRate) {

    return RateCalculationSwapLeg
        .builder()
        .payReceive(payReceive)
        .accrualSchedule(PeriodicSchedule.builder()
            .startDate(startDate)
            .endDate(endDate)
            .frequency(accrualFrequency)
            .businessDayAdjustment(accrualBusinessDayAdjustment)
            .startDateBusinessDayAdjustment(startDateBusinessDayAdjustment)
            .endDateBusinessDayAdjustment(endDateBusinessDayAdjustment)
            .stubConvention(stubConvention)
            .rollConvention(rollConvention)
            .build())
        .paymentSchedule(PaymentSchedule.builder()
            .paymentFrequency(getPaymentFrequency())
            .paymentDateOffset(getPaymentDateOffset())
            .compoundingMethod(getCompoundingMethod())
            .build())
        .notionalSchedule(NotionalSchedule.of(currency, notional))
        .calculation(FixedRateCalculation.of(fixedRate, dayCount))
        .build();
  }