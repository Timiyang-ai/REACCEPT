  @Test
  public void test_normalize() {
    MutablePointSensitivities test = new MutablePointSensitivities();
    test.addAll(Lists.newArrayList(CS3, CS2, CS1, CS3B));
    test.normalize();
    assertThat(test.getSensitivities()).containsExactly(CS1, CS2, CS3.withSensitivity(35d));
  }