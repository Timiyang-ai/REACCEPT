@Override
  public void explainPresentValue(RatePaymentPeriod paymentPeriod, RatesProvider provider, ExplainMapBuilder builder) {
    Currency currency = paymentPeriod.getCurrency();
    LocalDate paymentDate = paymentPeriod.getPaymentDate();

    double fxRate = fxRate(paymentPeriod, provider);
    double notional = paymentPeriod.getNotional() * fxRate;
    builder.put(ExplainKey.ENTRY_TYPE, "RatePaymentPeriod");
    builder.put(ExplainKey.PAYMENT_DATE, paymentDate);
    builder.put(ExplainKey.PAYMENT_CURRENCY, currency);
    builder.put(ExplainKey.NOTIONAL, CurrencyAmount.of(currency, notional));
    builder.put(ExplainKey.TRADE_NOTIONAL, paymentPeriod.getNotionalAmount());
    if (paymentDate.isBefore(provider.getValuationDate())) {
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      paymentPeriod.getFxReset().ifPresent(fxReset -> {
        builder.addListEntry(ExplainKey.OBSERVATIONS, child -> {
          child.put(ExplainKey.OBSERVED_INDEX, fxReset.getIndex());
          child.put(ExplainKey.FIXING_DATE, fxReset.getFixingDate());
          child.put(ExplainKey.OBSERVED_RATE, fxRate);
        });
      });
      for (RateAccrualPeriod accrualPeriod : paymentPeriod.getAccrualPeriods()) {
        builder.addListEntry(
            ExplainKey.ACCRUAL_PERIODS, child -> explainPresentValue(accrualPeriod, currency, notional, provider, child));
      }
      builder.put(ExplainKey.COMPOUNDING, paymentPeriod.getCompoundingMethod());
      builder.put(ExplainKey.DISCOUNT_FACTOR, provider.discountFactor(currency, paymentDate));
      builder.put(ExplainKey.FUTURE_VALUE, CurrencyAmount.of(currency, futureValue(paymentPeriod, provider)));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.of(currency, presentValue(paymentPeriod, provider)));
    }
  }