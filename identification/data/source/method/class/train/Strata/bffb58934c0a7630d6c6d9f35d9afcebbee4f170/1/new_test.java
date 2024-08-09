  @Test
  public void test_toTrade() {
    LocalDate date = LocalDate.of(2015, 10, 20);
    Period start = Period.ofMonths(2);
    int number = 2; // Future should be 20 Dec 15 + 2 IMM = effective 15-Jun-2016, fixing 13-Jun-2016    
    IborFutureConvention convention = ImmutableIborFutureConvention.of(USD_LIBOR_3M, QUARTERLY_IMM);
    double quantity = 3;
    double price = 0.99;
    SecurityId secId = SecurityId.of("OG-Future", "GBP-LIBOR-3M-Jun16");
    IborFutureTrade trade = convention.createTrade(date, secId, start, number, quantity, NOTIONAL_1M, price, REF_DATA);
    assertThat(trade.getProduct().getFixingDate()).isEqualTo(LocalDate.of(2016, 6, 13));
    assertThat(trade.getProduct().getIndex()).isEqualTo(USD_LIBOR_3M);
    assertThat(trade.getProduct().getNotional()).isEqualTo(NOTIONAL_1M);
    assertThat(trade.getProduct().getAccrualFactor()).isEqualTo(0.25);
    assertThat(trade.getQuantity()).isEqualTo(quantity);
    assertThat(trade.getPrice()).isEqualTo(price);
  }