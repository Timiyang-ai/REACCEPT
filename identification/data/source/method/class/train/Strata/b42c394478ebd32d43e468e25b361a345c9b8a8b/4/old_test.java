  @Test
  public void test_ofLongShort_noInfo() {
    GenericSecurityPosition test = GenericSecurityPosition.ofLongShort(SECURITY, LONG_QUANTITY, SHORT_QUANTITY);
    assertThat(test.getInfo()).isEqualTo(PositionInfo.empty());
    assertThat(test.getSecurity()).isEqualTo(SECURITY);
    assertThat(test.getLongQuantity()).isEqualTo(LONG_QUANTITY);
    assertThat(test.getShortQuantity()).isEqualTo(SHORT_QUANTITY);
    assertThat(test.getQuantity()).isEqualTo(QUANTITY);
  }