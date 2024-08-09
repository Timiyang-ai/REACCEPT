default CdsTrade toTrade(
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double coupon,
      ReferenceInformation referenceInformation,
      double upfrontFeeAmount,
      LocalDate upfrontFeePaymentDate) {

    return CdsTrade.builder()
        .product(Cds.builder()
            .startDate(startDate)
            .endDate(endDate)
            .buySellProtection(buySell)
            .businessDayAdjustment(getBusinessDayAdjustment())
            .referenceInformation(referenceInformation)
            .feeLeg(
                FeeLeg.of(
                    SinglePayment.of(
                        getCurrency(),
                        upfrontFeeAmount,
                        upfrontFeePaymentDate),
                    PeriodicPayments.of(
                        CurrencyAmount.of(getCurrency(), notional),
                        coupon,
                        getDayCount(),
                        getPaymentFrequency(),
                        getStubConvention(),
                        getRollConvention())))
            .payAccruedOnDefault(true)
            .build())
        .build();
  }