  @Test
  public void priceSensitivityTest() {
    PointSensitivities pointNext = PRICER.priceSensitivity(PRODUCT_NEXTDAY, RATES_PROVIDER,
        PRODUCT_NEXTDAY.getSettlementDateOffset().adjust(RATES_PROVIDER.getValuationDate(), REF_DATA), REF_DATA).build();
    CurrencyParameterSensitivities resNext = RATES_PROVIDER.parameterSensitivity(pointNext);
    CurrencyParameterSensitivities expNext =
        CALC_FD.sensitivity(RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER.price(PRODUCT_NEXTDAY,
            p, PRODUCT_NEXTDAY.getSettlementDateOffset().adjust(p.getValuationDate(), REF_DATA), CLEAN, REF_DATA)));
    equalWithRelativeTolerance(resNext, expNext, NOTIONAL * EPS);

    PointSensitivities pointBefore = PRICER.priceSensitivity(PRODUCT_BEFORE, RATES_PROVIDER,
        PRODUCT_BEFORE.getSettlementDateOffset().adjust(RATES_PROVIDER.getValuationDate(), REF_DATA), REF_DATA).build();
    CurrencyParameterSensitivities resBefore = RATES_PROVIDER.parameterSensitivity(pointBefore);
    CurrencyParameterSensitivities expBefore =
        CALC_FD.sensitivity(RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER.price(PRODUCT_BEFORE,
            p, PRODUCT_BEFORE.getSettlementDateOffset().adjust(p.getValuationDate(), REF_DATA), CLEAN, REF_DATA)));
    equalWithRelativeTolerance(resBefore, expBefore, NOTIONAL * EPS);

    PointSensitivities pointAfter = PRICER.priceSensitivity(PRODUCT_AFTER, RATES_PROVIDER,
        PRODUCT_AFTER.getSettlementDateOffset().adjust(RATES_PROVIDER.getValuationDate(), REF_DATA), REF_DATA).build();
    CurrencyParameterSensitivities resAfter = RATES_PROVIDER.parameterSensitivity(pointAfter);
    CurrencyParameterSensitivities expAfter =
        CALC_FD.sensitivity(RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER.price(PRODUCT_AFTER,
            p, PRODUCT_AFTER.getSettlementDateOffset().adjust(p.getValuationDate(), REF_DATA), CLEAN, REF_DATA)));
    equalWithRelativeTolerance(resAfter, expAfter, NOTIONAL * EPS);
  }