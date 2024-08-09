  @Test
  public void priceSensitivityTest() {
    PointSensitivities point = PRICER.priceSensitivity(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, REF_DATA).build();
    CurrencyParameterSensitivities res = RATES_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities exp = CALC_FD.sensitivity(
        RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER.price(PRODUCT, p, SETTLEMENT_STD, CLEAN, REF_DATA)));
    equalWithRelativeTolerance(res, exp, NOTIONAL * EPS);
    PointSensitivities pointMarkit =
        PRICER_MARKIT.priceSensitivity(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, REF_DATA).build();
    CurrencyParameterSensitivities resMarkit = RATES_PROVIDER.parameterSensitivity(pointMarkit);
    CurrencyParameterSensitivities expMarkit = CALC_FD.sensitivity(
        RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER_MARKIT.price(PRODUCT, p, SETTLEMENT_STD, CLEAN, REF_DATA)));
    equalWithRelativeTolerance(resMarkit, expMarkit, NOTIONAL * EPS);
    PointSensitivities pointOg = PRICER_OG.priceSensitivity(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, REF_DATA).build();
    CurrencyParameterSensitivities resOg = RATES_PROVIDER.parameterSensitivity(pointOg);
    CurrencyParameterSensitivities expOg = CALC_FD.sensitivity(
        RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER_OG.price(PRODUCT, p, SETTLEMENT_STD, CLEAN, REF_DATA)));
    equalWithRelativeTolerance(resOg, expOg, NOTIONAL * EPS);
  }