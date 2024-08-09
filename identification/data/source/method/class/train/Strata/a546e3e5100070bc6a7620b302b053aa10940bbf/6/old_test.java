  @Test
  public void test_createTrade_period() {
    FraConvention base = ImmutableFraConvention.builder()
        .index(GBP_LIBOR_3M)
        .spotDateOffset(NEXT_SAME_BUS_DAY)
        .build();
    LocalDate tradeDate = LocalDate.of(2015, 5, 5);
    FraTrade test = base.createTrade(tradeDate, Period.ofMonths(3), BUY, NOTIONAL_2M, 0.25d, REF_DATA);
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