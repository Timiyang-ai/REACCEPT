public void explainPresentValueWithSpread(
      FixedCouponBondPaymentPeriod period,
      IssuerCurveDiscountFactors discountFactors,
      ExplainMapBuilder builder,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    Currency currency = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    explainBasics(period, builder, currency, paymentDate);
    if (paymentDate.isBefore(discountFactors.getValuationDate())) {
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      builder.put(ExplainKey.DISCOUNT_FACTOR,
          discountFactors.getDiscountFactors().discountFactorWithSpread(paymentDate, zSpread, periodic, periodsPerYear));
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.of(currency, futureValue(period, discountFactors)));
      builder.put(ExplainKey.PRESENT_VALUE,
          CurrencyAmount.of(currency, presentValueWithSpread(period, discountFactors, zSpread, periodic, periodsPerYear)));
    }
  }