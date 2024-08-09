  @Test
  public void test_presentValueSensitivityNormalVolatility() {
    SwaptionSensitivity computedRec = PRICER_SWAPTION
        .presentValueSensitivityModelParamsVolatility(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyAmount pvRecUp = PRICER_SWAPTION.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER,
        SwaptionNormalVolatilityDataSets.normalVolSwaptionProviderUsdStsShifted(FD_EPS));
    CurrencyAmount pvRecDw = PRICER_SWAPTION.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER,
        SwaptionNormalVolatilityDataSets.normalVolSwaptionProviderUsdStsShifted(-FD_EPS));
    double expectedRec = 0.5 * (pvRecUp.getAmount() - pvRecDw.getAmount()) / FD_EPS;
    assertThat(computedRec.getCurrency()).isEqualTo(USD);
    assertThat(computedRec.getSensitivity()).isCloseTo(expectedRec, offset(FD_EPS * NOTIONAL));
    assertThat(computedRec.getVolatilitiesName()).isEqualTo(VOLS.getName());
    assertThat(computedRec.getExpiry()).isEqualTo(VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry()));
    assertThat(computedRec.getTenor()).isCloseTo(SWAP_TENOR_YEAR, offset(TOL));
    assertThat(computedRec.getStrike()).isCloseTo(STRIKE, offset(TOL));
    assertThat(computedRec.getForward()).isCloseTo(PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER), offset(TOL));
    SwaptionSensitivity computedPay = PRICER_SWAPTION
        .presentValueSensitivityModelParamsVolatility(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    CurrencyAmount pvUpPay = PRICER_SWAPTION.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER,
        SwaptionNormalVolatilityDataSets.normalVolSwaptionProviderUsdStsShifted(FD_EPS));
    CurrencyAmount pvDwPay = PRICER_SWAPTION.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER,
        SwaptionNormalVolatilityDataSets.normalVolSwaptionProviderUsdStsShifted(-FD_EPS));
    double expectedPay = 0.5 * (pvUpPay.getAmount() - pvDwPay.getAmount()) / FD_EPS;
    assertThat(computedPay.getCurrency()).isEqualTo(USD);
    assertThat(computedPay.getSensitivity()).isCloseTo(expectedPay, offset(FD_EPS * NOTIONAL));
    assertThat(computedPay.getVolatilitiesName()).isEqualTo(VOLS.getName());
    assertThat(computedPay.getExpiry()).isEqualTo(VOLS.relativeTime(SWAPTION_PAY_SHORT.getExpiry()));
    assertThat(computedPay.getTenor()).isCloseTo(SWAP_TENOR_YEAR, offset(TOL));
    assertThat(computedPay.getStrike()).isCloseTo(STRIKE, offset(TOL));
    assertThat(computedPay.getForward()).isCloseTo(PRICER_SWAP.parRate(RSWAP_PAY, RATE_PROVIDER), offset(TOL));
  }