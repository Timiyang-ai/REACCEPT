  @Test
  public void parSpreadSensitivityTest() {
    PointSensitivities point = PRICER.parSpreadSensitivity(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, REF_DATA).build();
    CurrencyParameterSensitivities res = RATES_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities exp = CALC_FD.sensitivity(
        RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER.parSpread(PRODUCT, p, SETTLEMENT_STD, REF_DATA)));
    equalWithRelativeTolerance(res, exp, NOTIONAL * EPS);
    PointSensitivities pointMarkit =
        PRICER_MARKIT.parSpreadSensitivity(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, REF_DATA).build();
    CurrencyParameterSensitivities resMarkit = RATES_PROVIDER.parameterSensitivity(pointMarkit);
    CurrencyParameterSensitivities expMarkit = CALC_FD.sensitivity(
        RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER_MARKIT.parSpread(PRODUCT, p, SETTLEMENT_STD, REF_DATA)));
    equalWithRelativeTolerance(resMarkit, expMarkit, NOTIONAL * EPS);
    PointSensitivities pointOg = PRICER_OG.parSpreadSensitivity(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, REF_DATA).build();
    CurrencyParameterSensitivities resOg = RATES_PROVIDER.parameterSensitivity(pointOg);
    CurrencyParameterSensitivities expOg = CALC_FD.sensitivity(
        RATES_PROVIDER, p -> CurrencyAmount.of(USD, PRICER_OG.parSpread(PRODUCT, p, SETTLEMENT_STD, REF_DATA)));
    equalWithRelativeTolerance(resOg, expOg, NOTIONAL * EPS);
  }