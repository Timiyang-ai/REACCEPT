  @Test
  public void test_currencyExposure() {
    for (int i = 0; i < NB_STRIKES; ++i) {
      CurrencyAmount pvCall = PRICER.presentValue(CALLS[i], RATES_PROVIDER, VOLS);
      PointSensitivityBuilder pvSensiCall = PRICER.presentValueSensitivityRatesStickyStrike(CALLS[i], RATES_PROVIDER, VOLS);
      MultiCurrencyAmount computedCall = PRICER.currencyExposure(CALLS[i], RATES_PROVIDER, VOLS);
      MultiCurrencyAmount expectedCall = RATES_PROVIDER.currencyExposure(pvSensiCall.build()).plus(pvCall);
      assertThat(computedCall.getAmount(EUR).getAmount()).isCloseTo(expectedCall.getAmount(EUR).getAmount(), offset(NOTIONAL * TOL));
      assertThat(computedCall.getAmount(USD).getAmount()).isCloseTo(expectedCall.getAmount(USD).getAmount(), offset(NOTIONAL * TOL));
      CurrencyAmount pvPut = PRICER.presentValue(PUTS[i], RATES_PROVIDER, VOLS);
      PointSensitivityBuilder pvSensiPut = PRICER.presentValueSensitivityRatesStickyStrike(PUTS[i], RATES_PROVIDER, VOLS);
      MultiCurrencyAmount computedPut = PRICER.currencyExposure(PUTS[i], RATES_PROVIDER, VOLS);
      MultiCurrencyAmount expectedPut = RATES_PROVIDER.currencyExposure(pvSensiPut.build()).plus(pvPut);
      assertThat(computedPut.getAmount(EUR).getAmount()).isCloseTo(expectedPut.getAmount(EUR).getAmount(), offset(NOTIONAL * TOL));
      assertThat(computedPut.getAmount(USD).getAmount()).isCloseTo(expectedPut.getAmount(USD).getAmount(), offset(NOTIONAL * TOL));
    }
  }