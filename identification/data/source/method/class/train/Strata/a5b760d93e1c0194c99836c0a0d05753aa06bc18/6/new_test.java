  @Test
  public void test_legInitialNotional() {
    ResolvedSwapLeg firstLeg = SWAP_TRADE.getProduct().getLegs().get(0);
    ResolvedSwapLeg secondLeg = SWAP_TRADE.getProduct().getLegs().get(1);
    Currency ccy = firstLeg.getCurrency();
    RatePaymentPeriod firstPaymentPeriod = (RatePaymentPeriod) firstLeg.getPaymentPeriods().get(0);
    double notional = firstPaymentPeriod.getNotional();

    LegAmounts expected = LegAmounts.of(
        SwapLegAmount.of(firstLeg, CurrencyAmount.of(ccy, notional)),
        SwapLegAmount.of(secondLeg, CurrencyAmount.of(ccy, notional)));

    assertThat(SwapMeasureCalculations.DEFAULT.legInitialNotional(SWAP_TRADE)).isEqualTo(expected);
  }