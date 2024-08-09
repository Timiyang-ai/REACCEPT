  @Test
  public void test_normalize_pay_double() {
    assertThat(PayReceive.PAY.normalize(1d)).isCloseTo(-1d, offset(0d));
    assertThat(PayReceive.PAY.normalize(0d)).isCloseTo(0d, offset(0d));
    assertThat(PayReceive.PAY.normalize(-0d)).isCloseTo(0d, offset(0d));
    assertThat(PayReceive.PAY.normalize(-1d)).isCloseTo(-1d, offset(0d));
  }