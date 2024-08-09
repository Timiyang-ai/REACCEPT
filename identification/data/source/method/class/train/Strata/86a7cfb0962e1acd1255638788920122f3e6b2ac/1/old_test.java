  @Test
  public void bucketedCs01Test() {
    double[] expectedFd = new double[] {
        0.02446907003406107, 0.1166137422736746, 0.5196553952424576, 1.4989046391578054, 3.5860718603647483, 4233.77162264947,
        0.0};
    CurrencyParameterSensitivity fd = CS01_FD.bucketedCs01(CDS1, ImmutableList.copyOf(MARKET_CDS), RATES_PROVIDER, REF_DATA);
    CurrencyParameterSensitivity analytic =
        CS01_AN.bucketedCs01(CDS1, ImmutableList.copyOf(MARKET_CDS), RATES_PROVIDER, REF_DATA);
    assertThat(fd.getCurrency()).isEqualTo(USD);
    assertThat(fd.getMarketDataName()).isEqualTo(CurveName.of("impliedSpreads"));
    assertThat(fd.getParameterCount()).isEqualTo(NUM_MARKET_CDS);
    assertThat(fd.getParameterMetadata()).isEqualTo(CDS_METADATA);
    assertThat(DoubleArrayMath.fuzzyEquals(fd.getSensitivity().multipliedBy(ONE_BP).toArray(), expectedFd, NOTIONAL * TOL)).isTrue();
    assertThat(analytic.getCurrency()).isEqualTo(USD);
    assertThat(analytic.getMarketDataName()).isEqualTo(CurveName.of("impliedSpreads"));
    assertThat(analytic.getParameterCount()).isEqualTo(NUM_MARKET_CDS);
    assertThat(analytic.getParameterMetadata()).isEqualTo(CDS_METADATA);
    assertThat(DoubleArrayMath.fuzzyEquals(
        analytic.getSensitivity().toArray(), fd.getSensitivity().toArray(), NOTIONAL * ONE_BP * 10d)).isTrue();
    PointSensitivities point = PRICER.presentValueOnSettleSensitivity(CDS1, RATES_PROVIDER, REF_DATA);
    CurrencyParameterSensitivity paramSensi = RATES_PROVIDER.singleCreditCurveParameterSensitivity(point, LEGAL_ENTITY, USD);
    CurrencyParameterSensitivities quoteSensi =
        QUOTE_CAL.sensitivity(CurrencyParameterSensitivities.of(paramSensi), RATES_PROVIDER);
    assertThat(DoubleArrayMath.fuzzyEquals(
        quoteSensi.getSensitivities().get(0).getSensitivity().toArray(), analytic.getSensitivity().toArray(), NOTIONAL * TOL)).isTrue();
  }