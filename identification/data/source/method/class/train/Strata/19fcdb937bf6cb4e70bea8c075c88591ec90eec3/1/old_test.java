  @Test
  public void stream() {
    RatesCurveGroup test = RatesCurveGroup.of(NAME, DISCOUNT_CURVES, IBOR_CURVES);
    List<Curve> expected = ImmutableList.<Curve>builder()
        .addAll(DISCOUNT_CURVES.values())
        .addAll(IBOR_CURVES.values())
        .build();
    assertThat(test.stream().collect(toList())).containsOnlyElementsOf(expected);
  }