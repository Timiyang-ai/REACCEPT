public void explainPresentValue(
      FixedCouponBondPaymentPeriod period,
      IssuerCurveDiscountFactors discountFactors,
      ExplainMapBuilder builder) {

    Currency currency = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    explainBasics(period, builder, currency, paymentDate);
    if (paymentDate.isBefore(discountFactors.getValuationDate())) {
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      builder.put(ExplainKey.DISCOUNT_FACTOR, discountFactors.discountFactor(paymentDate));
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.of(currency, futureValue(period, discountFactors)));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.of(currency, presentValue(period, discountFactors)));
    }
  }