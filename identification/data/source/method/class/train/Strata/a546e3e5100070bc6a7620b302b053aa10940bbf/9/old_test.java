  @Test
  public void test_createTrade() {
    FxSwapTemplate base = FxSwapTemplate.of(NEAR_PERIOD, FAR_PERIOD, CONVENTION);
    LocalDate tradeDate = LocalDate.of(2015, 10, 29);
    FxSwapTrade test = base.createTrade(tradeDate, BUY, NOTIONAL_EUR, FX_RATE_NEAR, FX_RATE_PTS, REF_DATA);
    LocalDate spotDate = PLUS_TWO_DAYS.adjust(tradeDate, REF_DATA);
    LocalDate nearDate = spotDate.plus(NEAR_PERIOD);
    LocalDate farDate = spotDate.plus(FAR_PERIOD);
    BusinessDayAdjustment bda = CONVENTION.getBusinessDayAdjustment();
    FxSwap expected = FxSwap.ofForwardPoints(
        CurrencyAmount.of(EUR, NOTIONAL_EUR), FxRate.of(EUR, USD, FX_RATE_NEAR), FX_RATE_PTS, nearDate, farDate, bda);
    assertThat(test.getInfo().getTradeDate()).isEqualTo(Optional.of(tradeDate));
    assertThat(test.getProduct()).isEqualTo(expected);
  }