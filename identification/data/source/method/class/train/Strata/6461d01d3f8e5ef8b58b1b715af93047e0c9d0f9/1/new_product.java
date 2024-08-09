public void explainPresentValueWithZSpread(
      CapitalIndexedBondPaymentPeriod period,
      RatesProvider ratesProvider,
      IssuerCurveDiscountFactors issuerDiscountFactors,
      ExplainMapBuilder builder,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    Currency currency = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    builder.put(ExplainKey.ENTRY_TYPE, "CapitalIndexedBondPaymentPeriod");
    builder.put(ExplainKey.PAYMENT_DATE, paymentDate);
    builder.put(ExplainKey.PAYMENT_CURRENCY, currency);
    builder.put(ExplainKey.START_DATE, period.getStartDate());
    builder.put(ExplainKey.UNADJUSTED_START_DATE, period.getUnadjustedStartDate());
    builder.put(ExplainKey.END_DATE, period.getEndDate());
    builder.put(ExplainKey.UNADJUSTED_END_DATE, period.getUnadjustedEndDate());
    builder.put(ExplainKey.DAYS, (int) DAYS.between(period.getUnadjustedStartDate(), period.getUnadjustedEndDate()));
    if (paymentDate.isBefore(ratesProvider.getValuationDate())) {
      builder.put(ExplainKey.COMPLETED, Boolean.TRUE);
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      builder.put(ExplainKey.DISCOUNT_FACTOR, issuerDiscountFactors.discountFactor(paymentDate));
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.of(currency, forecastValue(period, ratesProvider)));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.of(currency, presentValueWithZSpread(
          period, ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear)));
    }
  }