  @Test
  public void test_presentValueSensitivityStrike() {
    double computedCaplet = PRICER.presentValueSensitivityStrike(CAPLET, RATES_PROVIDER, VOLATILITIES);
    double expectedCaplet = 0.5 * (PRICER.presentValue(CAPLET_UP, RATES_PROVIDER, VOLATILITIES).getAmount()
        - PRICER.presentValue(CAPLET_DW, RATES_PROVIDER, VOLATILITIES).getAmount()) / EPS;
    assertThat(computedCaplet).isCloseTo(expectedCaplet, offset(NOTIONAL * EPS));
    double computedFloorlet = PRICER.presentValueSensitivityStrike(FLOORLET, RATES_PROVIDER, VOLATILITIES);
    double expectedFloorlet = 0.5 * (PRICER.presentValue(FLOORLET_UP, RATES_PROVIDER, VOLATILITIES).getAmount()
        - PRICER.presentValue(FLOORLET_DW, RATES_PROVIDER, VOLATILITIES).getAmount()) / EPS;
    assertThat(computedFloorlet).isCloseTo(expectedFloorlet, offset(NOTIONAL * EPS));
  }