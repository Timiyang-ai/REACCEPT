public ExplainMap explainPresentValue(Payment payment, BaseProvider provider) {
    Currency currency = payment.getCurrency();
    LocalDate paymentDate = payment.getDate();

    ExplainMapBuilder builder = ExplainMap.builder();
    builder.put(ExplainKey.ENTRY_TYPE, "Payment");
    builder.put(ExplainKey.PAYMENT_DATE, paymentDate);
    builder.put(ExplainKey.PAYMENT_CURRENCY, currency);
    if (paymentDate.isBefore(provider.getValuationDate())) {
      builder.put(ExplainKey.FORECAST_VALUE, CurrencyAmount.zero(currency));
      builder.put(ExplainKey.PRESENT_VALUE, CurrencyAmount.zero(currency));
    } else {
      builder.put(ExplainKey.DISCOUNT_FACTOR, provider.discountFactor(currency, paymentDate));
      builder.put(ExplainKey.FORECAST_VALUE, forecastValue(payment, provider));
      builder.put(ExplainKey.PRESENT_VALUE, presentValue(payment, provider));
    }
    return builder.build();
  }