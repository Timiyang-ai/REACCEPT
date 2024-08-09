  @Test
  public void test_resolve() {
    ResolvedBondFuture expected = ResolvedBondFuture.builder()
        .securityId(SECURITY_ID)
        .deliveryBasket(RESOLVED_BASKET)
        .conversionFactors(CONVERSION_FACTOR)
        .lastTradeDate(LAST_TRADING_DATE)
        .firstNoticeDate(FIRST_NOTICE_DATE)
        .lastNoticeDate(LAST_NOTICE_DATE)
        .firstDeliveryDate(FIRST_DELIVERY_DATE)
        .lastDeliveryDate(LAST_DELIVERY_DATE)
        .rounding(ROUNDING)
        .build();
    assertThat(sut().resolve(REF_DATA)).isEqualTo(expected);
  }