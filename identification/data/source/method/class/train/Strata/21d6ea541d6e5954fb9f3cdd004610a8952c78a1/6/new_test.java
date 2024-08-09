  @Test
  public void test_resolve() {
    RatePeriodSwapLeg test = RatePeriodSwapLeg.builder()
        .type(IBOR)
        .payReceive(RECEIVE)
        .paymentPeriods(RPP1)
        .paymentEvents(NOTIONAL_EXCHANGE)
        .build();
    ResolvedSwapLeg expected = ResolvedSwapLeg.builder()
        .type(IBOR)
        .payReceive(RECEIVE)
        .paymentPeriods(RPP1)
        .paymentEvents(NOTIONAL_EXCHANGE)
        .build();
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }