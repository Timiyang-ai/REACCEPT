public void explainPresentValueWithSpread(
      FixedCouponBondPaymentPeriod period,
      IssuerCurveDiscountFactors discountFactors,
      ExplainMapBuilder builder,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    Currency currency = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    explainBasics(period, builder, currency, paymentDate);
    if (paymentDate.isBefore(discountFactors.getValuationDate())) {
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      builder.put(ExplainKey.DISCOUNT_FACTOR, discountFactors.getDiscountFactors()
          .discountFactorWithSpread(paymentDate, zSpread, compoundedRateType, periodsPerYear));
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.of(currency, forecastValue(period, discountFactors)));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.of(currency,
          presentValueWithSpread(period, discountFactors, zSpread, compoundedRateType, periodsPerYear)));
    }
  }