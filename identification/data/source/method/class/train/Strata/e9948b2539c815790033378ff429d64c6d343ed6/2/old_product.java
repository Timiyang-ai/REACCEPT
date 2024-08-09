public void explainPresentValue(
      CmsPeriod period, 
      RatesProvider ratesProvider, 
      SabrParametersSwaptionVolatilities swaptionVolatilities,
      ExplainMapBuilder builder) {
    
    String type = period.getCmsPeriodType().toString();
    Currency ccy = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    builder.put(ExplainKey.ENTRY_TYPE, "Cms" + type + "Period");
    builder.put(ExplainKey.STRIKE_VALUE, period.getStrike());
    builder.put(ExplainKey.NOTIONAL, CurrencyAmount.of(ccy, period.getNotional()));
    builder.put(ExplainKey.PAYMENT_DATE, period.getPaymentDate());
    builder.put(ExplainKey.DISCOUNT_FACTOR, ratesProvider.discountFactor(ccy, paymentDate));
    builder.put(ExplainKey.START_DATE, period.getStartDate());
    builder.put(ExplainKey.END_DATE, period.getEndDate());
    builder.put(ExplainKey.FIXING_DATE, period.getFixingDate());
    builder.put(ExplainKey.ACCRUAL_YEAR_FRACTION, period.getYearFraction());
    builder.put(ExplainKey.PRESENT_VALUE, presentValue(period, ratesProvider, swaptionVolatilities));
    builder.put(ExplainKey.FORWARD_RATE, swapPricer.parRate(period.getUnderlyingSwap(), ratesProvider));
    builder.put(ExplainKey.CONVEXITY_ADJUSTED_RATE, adjustedForwardRate(period, ratesProvider, swaptionVolatilities));
  }