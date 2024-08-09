  @Test
  public void test_price() {
    IborIndexRates mockIbor = mock(IborIndexRates.class);
    SimpleRatesProvider prov = new SimpleRatesProvider();
    prov.setIborRates(mockIbor);
    when(mockIbor.rate(FUTURE.getIborRate().getObservation())).thenReturn(RATE);

    assertThat(PRICER_TRADE.price(FUTURE_TRADE, prov)).isCloseTo(1.0 - RATE, offset(TOLERANCE_PRICE));
  }