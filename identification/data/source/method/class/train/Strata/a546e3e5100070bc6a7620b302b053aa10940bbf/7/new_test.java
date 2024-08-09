  @Test
  public void test_createTrade() {
    FraTemplate base = FraTemplate.of(Period.ofMonths(3), Period.ofMonths(6), FRA_GBP_LIBOR_3M);
    LocalDate tradeDate = LocalDate.of(2015, 5, 4); // trade date is a holiday!
    FraTrade test = base.createTrade(tradeDate, BUY, NOTIONAL_2M, 0.25d, REF_DATA);
    Fra expected = Fra.builder()
        .buySell(BUY)
        .notional(NOTIONAL_2M)
        .startDate(date(2015, 8, 5))
        .endDate(date(2015, 11, 5))
        .fixedRate(0.25d)
        .index(GBP_LIBOR_3M)
        .build();
    assertThat(test.getInfo().getTradeDate()).isEqualTo(Optional.of(tradeDate));
    assertThat(test.getProduct()).isEqualTo(expected);
  }