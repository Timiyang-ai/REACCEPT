  @Test
  public void test_resolve() {
    OvernightFuture base = sut();
    ResolvedOvernightFuture expected = ResolvedOvernightFuture.builder()
        .securityId(SECURITY_ID)
        .currency(USD)
        .notional(NOTIONAL)
        .accrualFactor(ACCRUAL_FACTOR)
        .overnightRate(OvernightRateComputation.of(
            USD_FED_FUND, START_DATE, END_DATE, 0, OvernightAccrualMethod.AVERAGED_DAILY, REF_DATA))
        .lastTradeDate(LAST_TRADE_DATE)
        .rounding(ROUNDING)
        .build();
    assertThat(base.resolve(REF_DATA)).isEqualTo(expected);
  }