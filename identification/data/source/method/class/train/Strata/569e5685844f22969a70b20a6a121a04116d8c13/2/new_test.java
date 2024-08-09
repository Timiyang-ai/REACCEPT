  @Test
  public void test_presentValue() {
    SimpleRatesProvider prov = createProvider(VAL_DATE);

    double pvExpected = AMOUNT_1000 * DISCOUNT_FACTOR;
    double pvComputed = PRICER.presentValue(PERIOD, prov);
    assertThat(pvComputed).isCloseTo(pvExpected, offset(TOLERANCE_PV));
  }