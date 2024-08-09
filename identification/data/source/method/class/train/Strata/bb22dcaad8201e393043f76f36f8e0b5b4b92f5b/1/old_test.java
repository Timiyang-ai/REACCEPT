  @Test
  public void test_equalWithTolerance_length() {
    PointSensitivities test1 = PointSensitivities.of(Lists.newArrayList(CS3, CS2, CS1)).normalized();
    PointSensitivities test2 = PointSensitivities.of(Lists.newArrayList(CS3, CS2)).normalized();
    assertThat(test1.equalWithTolerance(test2, 1.0E+1)).isFalse();
  }