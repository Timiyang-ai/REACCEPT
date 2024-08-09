  @Test
  public void test_toTrade_dates() {
    FraConvention base = ImmutableFraConvention.builder()
        .index(GBP_LIBOR_3M)
        .spotDateOffset(NEXT_SAME_BUS_DAY)
        .build();
    LocalDate tradeDate = LocalDate.of(2015, 5, 5);
    LocalDate startDate = date(2015, 8, 5);
    LocalDate endDate = date(2015, 11, 5);
    LocalDate paymentDate = startDate;
    FraTrade test = base.toTrade(tradeDate, startDate, endDate, startDate, BUY, NOTIONAL_2M, 0.25d);
    Fra expected = Fra.builder()
        .buySell(BUY)
        .notional(NOTIONAL_2M)
        .startDate(startDate)
        .endDate(endDate)
        .paymentDate(AdjustableDate.of(paymentDate))
        .fixedRate(0.25d)
        .index(GBP_LIBOR_3M)
        .build();
    assertThat(test.getInfo().getTradeDate()).isEqualTo(Optional.of(tradeDate));
    assertThat(test.getProduct()).isEqualTo(expected);
  }