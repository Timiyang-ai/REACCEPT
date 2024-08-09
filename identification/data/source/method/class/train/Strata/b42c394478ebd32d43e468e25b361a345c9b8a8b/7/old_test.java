  @Test
  public void test_ofNet_noInfo() {
    SecurityPosition test = SecurityPosition.ofNet(SECURITY_ID, QUANTITY);
    assertThat(test.getInfo()).isEqualTo(PositionInfo.empty());
    assertThat(test.getSecurityId()).isEqualTo(SECURITY_ID);
    assertThat(test.getLongQuantity()).isEqualTo(QUANTITY);
    assertThat(test.getShortQuantity()).isEqualTo(0d);
    assertThat(test.getQuantity()).isEqualTo(QUANTITY);
    assertThat(test.withInfo(POSITION_INFO).getInfo()).isEqualTo(POSITION_INFO);
    assertThat(test.withQuantity(129).getQuantity()).isCloseTo(129d, offset(0d));
    assertThat(test.withQuantity(-129).getQuantity()).isCloseTo(-129d, offset(0d));
  }