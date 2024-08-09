  @Test
  public void test_builder() {
    TradeInfo test = TradeInfo.builder()
        .counterparty(COUNTERPARTY)
        .build();
    assertThat(test.getId()).isEmpty();
    assertThat(test.getCounterparty()).hasValue(COUNTERPARTY);
    assertThat(test.getTradeDate()).isEmpty();
    assertThat(test.getTradeTime()).isEmpty();
    assertThat(test.getZone()).isEmpty();
    assertThat(test.getSettlementDate()).isEmpty();
    assertThat(test.getAttributeTypes()).isEmpty();
    assertThat(test.getAttributes()).isEmpty();
    assertThatIllegalArgumentException()
        .isThrownBy(() -> test.getAttribute(AttributeType.DESCRIPTION));
    assertThat(test.findAttribute(AttributeType.DESCRIPTION)).isEmpty();
  }