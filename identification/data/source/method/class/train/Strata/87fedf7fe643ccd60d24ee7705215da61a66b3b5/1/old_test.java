  @Test
  public void test_normalized_sorts() {
    PointSensitivities test = PointSensitivities.of(Lists.newArrayList(CS3, CS2, CS1));
    assertThat(test.normalized().getSensitivities()).containsExactly(CS1, CS2, CS3);
  }