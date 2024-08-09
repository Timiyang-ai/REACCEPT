public abstract CdsTrade toTrade(
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double coupon,
      ReferenceInformation referenceInformation,
      double upfrontFeeAmount,
      LocalDate upfrontFeePaymentDate);