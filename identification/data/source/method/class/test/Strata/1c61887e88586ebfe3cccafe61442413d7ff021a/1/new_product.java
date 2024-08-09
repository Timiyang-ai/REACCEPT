public OvernightRateSwapLeg toLeg(
      LocalDate startDate,
      LocalDate endDate,
      PayReceive payReceive,
      double notional,
      double spread) {

    return OvernightRateSwapLeg
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
        .calculation(OvernightRateCalculation.builder()
            .index(index)
            .dayCount(getDayCount())
            .accrualMethod(getAccrualMethod())
            .rateCutOffDays(getRateCutOffDays())
            .spread(spread != 0 ? ValueSchedule.of(spread) : null)
            .build())
        .build();
  }