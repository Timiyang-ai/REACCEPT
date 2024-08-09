@Override
  public void explainPresentValue(FxResetNotionalExchange event, RatesProvider provider, ExplainMapBuilder builder) {
    Currency currency = event.getCurrency();
    LocalDate paymentDate = event.getPaymentDate();

    builder.put(ExplainKey.ENTRY_TYPE, "FxResetNotionalExchange");
    builder.put(ExplainKey.PAYMENT_DATE, paymentDate);
    builder.put(ExplainKey.PAYMENT_CURRENCY, currency);
    builder.put(ExplainKey.TRADE_NOTIONAL, event.getNotionalAmount());
    if (paymentDate.isBefore(provider.getValuationDate())) {
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      builder.addListEntry(ExplainKey.OBSERVATIONS, child -> {
        child.put(ExplainKey.OBSERVED_INDEX, event.getIndex());
        child.put(ExplainKey.FIXING_DATE, event.getFixingDate());
        child.put(ExplainKey.OBSERVED_RATE, fxRate(event, provider));
      });
      builder.put(ExplainKey.DISCOUNT_FACTOR, provider.discountFactor(currency, paymentDate));
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.of(currency, futureValue(event, provider)));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.of(currency, presentValue(event, provider)));
    }
  }