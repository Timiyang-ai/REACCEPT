  @Test
  public void test_ofLongShort_noInfo() {
    SecurityPosition test = SecurityPosition.ofLongShort(SECURITY_ID, LONG_QUANTITY, SHORT_QUANTITY);
    assertThat(test.getInfo()).isEqualTo(PositionInfo.empty());
    assertThat(test.getSecurityId()).isEqualTo(SECURITY_ID);
    assertThat(test.getLongQuantity()).isEqualTo(LONG_QUANTITY);
    assertThat(test.getShortQuantity()).isEqualTo(SHORT_QUANTITY);
    assertThat(test.getQuantity()).isEqualTo(QUANTITY);
  }