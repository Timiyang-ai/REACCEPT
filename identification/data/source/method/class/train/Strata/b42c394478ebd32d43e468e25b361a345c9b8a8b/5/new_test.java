  @Test
  public void test_ofNet_noInfo() {
    GenericSecurityPosition test = GenericSecurityPosition.ofNet(SECURITY, QUANTITY);
    assertThat(test.getInfo()).isEqualTo(PositionInfo.empty());
    assertThat(test.getSecurity()).isEqualTo(SECURITY);
    assertThat(test.getLongQuantity()).isEqualTo(QUANTITY);
    assertThat(test.getShortQuantity()).isEqualTo(0d);
    assertThat(test.getQuantity()).isEqualTo(QUANTITY);
    assertThat(test.getProduct()).isEqualTo(SECURITY);
    assertThat(test.getSecurityId()).isEqualTo(SECURITY.getSecurityId());
    assertThat(test.getCurrency()).isEqualTo(SECURITY.getCurrency());
    assertThat(test.withInfo(POSITION_INFO).getInfo()).isEqualTo(POSITION_INFO);
    assertThat(test.withQuantity(129).getQuantity()).isCloseTo(129d, offset(0d));
    assertThat(test.withQuantity(-129).getQuantity()).isCloseTo(-129d, offset(0d));
  }