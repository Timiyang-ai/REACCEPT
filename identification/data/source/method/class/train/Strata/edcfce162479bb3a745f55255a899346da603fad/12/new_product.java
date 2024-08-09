private void explainPresentValue(
      RateAccrualPeriod accrualPeriod,
      Currency currency,
      double notional,
      RatesProvider provider,
      ExplainMapBuilder builder) {

    double rawRate = rateObservationFn.explainRate(
        accrualPeriod.getRateObservation(), accrualPeriod.getStartDate(), accrualPeriod.getEndDate(), provider, builder);
    double payOffRate = rawRate * accrualPeriod.getGearing() + accrualPeriod.getSpread();
    double ua = unitNotionalAccrual(accrualPeriod, accrualPeriod.getSpread(), provider);

    // Note that the future value is not published since this is potentially misleading when
    // compounding is being applied, and when it isn't then it's the same as the future
    // value of the payment period.
    
    builder.put(ExplainKey.ENTRY_TYPE, "AccrualPeriod");
    builder.put(ExplainKey.START_DATE, accrualPeriod.getStartDate());
    builder.put(ExplainKey.UNADJUSTED_START_DATE, accrualPeriod.getUnadjustedStartDate());
    builder.put(ExplainKey.END_DATE, accrualPeriod.getEndDate());
    builder.put(ExplainKey.UNADJUSTED_END_DATE, accrualPeriod.getUnadjustedEndDate());
    builder.put(ExplainKey.ACCRUAL_YEAR_FRACTION, accrualPeriod.getYearFraction());
    builder.put(ExplainKey.ACCRUAL_DAYS, (int) DAYS.between(accrualPeriod.getStartDate(), accrualPeriod.getEndDate()));
    builder.put(ExplainKey.GEARING, accrualPeriod.getGearing());
    builder.put(ExplainKey.SPREAD, accrualPeriod.getSpread());
    builder.put(ExplainKey.PAY_OFF_RATE, accrualPeriod.getNegativeRateMethod().adjust(payOffRate));
    builder.put(ExplainKey.UNIT_AMOUNT, ua);
  }