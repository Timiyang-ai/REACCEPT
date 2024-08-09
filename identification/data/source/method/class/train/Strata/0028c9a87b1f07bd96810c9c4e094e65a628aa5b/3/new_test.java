  @Test
  public void test_currencyExposure() {
    double eps = 1.0e-14;
    ImmutableRatesProvider prov = ImmutableRatesProvider.builder(VAL_DATE)
        .fxRateProvider(FX_MATRIX)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .discountCurve(USD, DISCOUNT_CURVE_USD)
        .build();
    DiscountingFxResetNotionalExchangePricer test = new DiscountingFxResetNotionalExchangePricer();
    // USD
    MultiCurrencyAmount computedUSD = test.currencyExposure(FX_RESET_NOTIONAL_EXCHANGE_REC_USD, prov);
    PointSensitivities pointUSD = test.presentValueSensitivity(FX_RESET_NOTIONAL_EXCHANGE_REC_USD, prov).build();
    MultiCurrencyAmount expectedUSD = prov.currencyExposure(pointUSD.convertedTo(USD, prov)).plus(CurrencyAmount.of(
        FX_RESET_NOTIONAL_EXCHANGE_REC_USD.getCurrency(), test.presentValue(FX_RESET_NOTIONAL_EXCHANGE_REC_USD, prov)));
    assertThat(computedUSD.contains(GBP)).isFalse(); // 0 GBP
    assertThat(computedUSD.getAmount(USD).getAmount()).isCloseTo(expectedUSD.getAmount(USD).getAmount(), offset(eps * NOTIONAL));
    // GBP
    MultiCurrencyAmount computedGBP = test.currencyExposure(FX_RESET_NOTIONAL_EXCHANGE_PAY_GBP, prov);
    PointSensitivities pointGBP = test.presentValueSensitivity(FX_RESET_NOTIONAL_EXCHANGE_PAY_GBP, prov).build();
    MultiCurrencyAmount expectedGBP = prov.currencyExposure(pointGBP.convertedTo(GBP, prov)).plus(CurrencyAmount.of(
        FX_RESET_NOTIONAL_EXCHANGE_PAY_GBP.getCurrency(), test.presentValue(FX_RESET_NOTIONAL_EXCHANGE_PAY_GBP, prov)));
    assertThat(computedGBP.contains(USD)).isFalse(); // 0 USD
    assertThat(computedGBP.getAmount(GBP).getAmount()).isCloseTo(expectedGBP.getAmount(GBP).getAmount(), offset(eps * NOTIONAL));
    // FD approximation
    FxMatrix fxMatrixUp = FxMatrix.of(GBP, USD, FX_RATE + EPS_FD);
    ImmutableRatesProvider provUp = ImmutableRatesProvider.builder(VAL_DATE)
        .fxRateProvider(fxMatrixUp)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .discountCurve(USD, DISCOUNT_CURVE_USD)
        .build();
    double expectedFdUSD = -(test.presentValue(FX_RESET_NOTIONAL_EXCHANGE_REC_USD, provUp) -
        test.presentValue(FX_RESET_NOTIONAL_EXCHANGE_REC_USD, prov)) * FX_RATE * FX_RATE / EPS_FD;
    assertThat(computedUSD.getAmount(USD).getAmount()).isCloseTo(expectedFdUSD, offset(EPS_FD * NOTIONAL));
    double expectedFdGBP = (test.presentValue(FX_RESET_NOTIONAL_EXCHANGE_PAY_GBP, provUp) -
        test.presentValue(FX_RESET_NOTIONAL_EXCHANGE_PAY_GBP, prov)) / EPS_FD;
    assertThat(computedGBP.getAmount(GBP).getAmount()).isCloseTo(expectedFdGBP, offset(EPS_FD * NOTIONAL));
  }