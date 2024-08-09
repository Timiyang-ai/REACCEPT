  @Test
  public void test_currencyExposure() {
    for (ResolvedFxSingleBarrierOption option : OPTION_ALL) {
      CurrencyAmount pv = PRICER.presentValue(option, RATE_PROVIDER, VOLS_FLAT);
      MultiCurrencyAmount computed = PRICER.currencyExposure(option, RATE_PROVIDER, VOLS_FLAT);
      FxMatrix fxMatrix = FxMatrix.builder().addRate(EUR, USD, SPOT + FD_EPS).build();
      ImmutableRatesProvider provBumped = RATE_PROVIDER.toBuilder().fxRateProvider(fxMatrix).build();
      CurrencyAmount pvBumped = PRICER.presentValue(option, provBumped, VOLS_FLAT);
      double ceCounterFD = pvBumped.getAmount() - pv.getAmount();
      double ceBaseFD = pvBumped.getAmount() / (SPOT + FD_EPS) - pv.getAmount() / SPOT;
      assertThat(computed.getAmount(EUR).getAmount() * FD_EPS).isCloseTo(ceCounterFD, offset(NOTIONAL * TOL));
      assertThat(computed.getAmount(USD).getAmount() * (1.0d / (SPOT + FD_EPS) - 1.0d / SPOT)).isCloseTo(ceBaseFD, offset(NOTIONAL * TOL));
    }
  }