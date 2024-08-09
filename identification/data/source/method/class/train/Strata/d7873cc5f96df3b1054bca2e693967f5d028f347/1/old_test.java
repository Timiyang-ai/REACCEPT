  @Test
  public void test_filtered_dropThis_atStart() {
    DummyFraCurveNode node1 = DummyFraCurveNode.of(Period.ofDays(3), GBP_LIBOR_1M, TICKER, DROP_THIS_2D);
    DummyFraCurveNode node2 = DummyFraCurveNode.of(Period.ofDays(4), GBP_LIBOR_1M, TICKER);
    DummyFraCurveNode node3 = DummyFraCurveNode.of(Period.ofDays(11), GBP_LIBOR_1M, TICKER);
    ImmutableList<DummyFraCurveNode> nodes = ImmutableList.of(node1, node2, node3);

    InterpolatedNodalCurveDefinition test = InterpolatedNodalCurveDefinition.builder()
        .name(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.ZERO_RATE)
        .dayCount(ACT_365F)
        .nodes(nodes)
        .interpolator(CurveInterpolators.LINEAR)
        .extrapolatorLeft(CurveExtrapolators.FLAT)
        .extrapolatorRight(CurveExtrapolators.FLAT)
        .build();
    assertThat(test.filtered(VAL_DATE, REF_DATA).getNodes()).containsExactly(node2, node3);
  }