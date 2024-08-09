  @Test
  public void test_resolve() {
    IborFuture test = sut();
    ResolvedIborFuture expected = ResolvedIborFuture.builder()
        .securityId(SECURITY_ID)
        .currency(USD)
        .notional(NOTIONAL)
        .accrualFactor(ACCRUAL_FACTOR)
        .iborRate(IborRateComputation.of(USD_LIBOR_3M, LAST_TRADE_DATE, REF_DATA))
        .rounding(ROUNDING)
        .build();
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }