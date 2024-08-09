  @Test
  public void test_collectIndices() {
    ResolvedSwapLeg test = ResolvedSwapLeg.builder()
        .type(IBOR)
        .payReceive(RECEIVE)
        .paymentPeriods(RPP1)
        .paymentEvents(NOTIONAL_EXCHANGE)
        .build();
    ImmutableSet.Builder<Index> builder = ImmutableSet.builder();
    test.collectIndices(builder);
    assertThat(builder.build()).containsOnly(GBP_LIBOR_3M);
  }