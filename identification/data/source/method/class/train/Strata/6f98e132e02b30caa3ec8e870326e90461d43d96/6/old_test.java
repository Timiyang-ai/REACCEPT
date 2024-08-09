  @Test
  public void test_toTrade_periods() {
    ImmutableFxSwapConvention base = ImmutableFxSwapConvention.of(EUR_USD, PLUS_TWO_DAYS, BDA_FOLLOW);
    Period startPeriod = Period.ofMonths(3);
    Period endPeriod = Period.ofMonths(6);
    LocalDate tradeDate = LocalDate.of(2015, 5, 5);
    LocalDate spotDate = PLUS_TWO_DAYS.adjust(tradeDate, REF_DATA);
    LocalDate nearDate = spotDate.plus(startPeriod);
    LocalDate farDate = spotDate.plus(endPeriod);
    FxSwapTrade test =
        base.createTrade(tradeDate, startPeriod, endPeriod, BUY, NOTIONAL_EUR, FX_RATE_NEAR, FX_RATE_PTS, REF_DATA);
    FxSwap expected = FxSwap.ofForwardPoints(
        CurrencyAmount.of(EUR, NOTIONAL_EUR), FxRate.of(EUR, USD, FX_RATE_NEAR), FX_RATE_PTS, nearDate, farDate, BDA_FOLLOW);
    assertThat(test.getInfo().getTradeDate()).isEqualTo(Optional.of(tradeDate));
    assertThat(test.getProduct()).isEqualTo(expected);
  }