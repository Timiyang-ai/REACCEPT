@Override
  public void explainPresentValue(FxResetNotionalExchange event, RatesProvider provider, ExplainMapBuilder builder) {
    Currency currency = event.getCurrency();
    LocalDate paymentDate = event.getPaymentDate();

    builder.put(ExplainKey.ENTRY_TYPE, "FxResetNotionalExchange");
    builder.put(ExplainKey.PAYMENT_DATE, paymentDate);
    builder.put(ExplainKey.PAYMENT_CURRENCY, currency);
    builder.put(ExplainKey.TRADE_NOTIONAL, event.getNotionalAmount());
    if (paymentDate.isBefore(provider.getValuationDate())) {
      builder.put(ExplainKey.COMPLETED, Boolean.TRUE);
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      builder.addListEntry(ExplainKey.OBSERVATIONS, child -> {
        child.put(ExplainKey.ENTRY_TYPE, "FxObservation");
        child.put(ExplainKey.INDEX, event.getObservation().getIndex());
        child.put(ExplainKey.FIXING_DATE, event.getObservation().getFixingDate());
        child.put(ExplainKey.INDEX_VALUE, fxRate(event, provider));
      });
      builder.put(ExplainKey.DISCOUNT_FACTOR, provider.discountFactor(currency, paymentDate));
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.of(currency, forecastValue(event, provider)));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.of(currency, presentValue(event, provider)));
    }
  }