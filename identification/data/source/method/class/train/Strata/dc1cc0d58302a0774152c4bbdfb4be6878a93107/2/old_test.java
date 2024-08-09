  @Test
  public void test_parSpread_after_trade_date() {
    IborIndexRates mockIbor = mock(IborIndexRates.class);
    SimpleRatesProvider prov = new SimpleRatesProvider();
    prov.setIborRates(mockIbor);
    prov.setValuationDate(FUTURE_TRADE.getTradedPrice().get().getTradeDate().plusDays(1));
    when(mockIbor.rate(FUTURE.getIborRate().getObservation())).thenReturn(RATE);
    double lastClosingPrice = 0.99;
    double parSpreadExpected = PRICER_TRADE.price(FUTURE_TRADE, prov) - lastClosingPrice;
    double parSpreadComputed = PRICER_TRADE.parSpread(FUTURE_TRADE, prov, lastClosingPrice);
    assertThat(parSpreadComputed).isCloseTo(parSpreadExpected, offset(TOLERANCE_PRICE));
  }