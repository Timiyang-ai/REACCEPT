public RateCalculationSwapLeg toLeg(
      LocalDate startDate,
      LocalDate endDate,
      PayReceive payReceive,
      double notional,
      double spread) {

    return RateCalculationSwapLeg
        .builder()
        .payReceive(payReceive)
        .accrualSchedule(PeriodicSchedule.builder()
            .startDate(startDate)
            .endDate(endDate)
            .frequency(getAccrualFrequency())
            .businessDayAdjustment(getAccrualBusinessDayAdjustment())
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
        .notionalSchedule(NotionalSchedule.of(getCurrency(), notional))
        .calculation(IborRateCalculation.builder()
            .index(index)
            .dayCount(getDayCount())
            .fixingRelativeTo(getFixingRelativeTo())
            .fixingDateOffset(getFixingDateOffset())
            .spread(spread != 0 ? ValueSchedule.of(spread) : null)
            .build())
        .build();
  }