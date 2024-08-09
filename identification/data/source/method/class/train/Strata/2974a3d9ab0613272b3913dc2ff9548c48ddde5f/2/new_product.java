public ExplainMap explainPresentValue(ResolvedFra fra, RatesProvider provider) {
    ExplainMapBuilder builder = ExplainMap.builder();
    Currency currency = fra.getCurrency();
    builder.put(ExplainKey.ENTRY_TYPE, "FRA");
    builder.put(ExplainKey.PAYMENT_DATE, fra.getPaymentDate());
    builder.put(ExplainKey.START_DATE, fra.getStartDate());
    builder.put(ExplainKey.END_DATE, fra.getEndDate());
    builder.put(ExplainKey.ACCRUAL_YEAR_FRACTION, fra.getYearFraction());
    builder.put(ExplainKey.ACCRUAL_DAYS, (int) DAYS.between(fra.getStartDate(), fra.getEndDate()));
    builder.put(ExplainKey.PAYMENT_CURRENCY, currency);
    builder.put(ExplainKey.NOTIONAL, CurrencyAmount.of(currency, fra.getNotional()));
    builder.put(ExplainKey.TRADE_NOTIONAL, CurrencyAmount.of(currency, fra.getNotional()));
    if (fra.getPaymentDate().isBefore(provider.getValuationDate())) {
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      double rate = rateObservationFn.explainRate(
          fra.getFloatingRate(), fra.getStartDate(), fra.getEndDate(), provider, builder);
      builder.put(ExplainKey.FIXED_RATE, fra.getFixedRate());
      builder.put(ExplainKey.DISCOUNT_FACTOR, provider.discountFactor(currency, fra.getPaymentDate()));
      builder.put(ExplainKey.PAY_OFF_RATE, rate);
      builder.put(ExplainKey.UNIT_AMOUNT, unitAmount(fra, provider));
      builder.put(ExplainKey.FORECAST_VALUE, forecastValue(fra, provider));
      builder.put(ExplainKey.PRESENT_VALUE, presentValue(fra, provider));
    }
    return builder.build();
  }