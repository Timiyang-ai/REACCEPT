  @Test
  public void test_mapSensitivities() {
    PointSensitivities test = PointSensitivities.of(Lists.newArrayList(CS3, CS2, CS1));
    assertThat(test.mapSensitivities(s -> s / 2).getSensitivities())
        .containsExactly(CS3.withSensitivity(16d), CS2.withSensitivity(11d), CS1.withSensitivity(6d));
  }