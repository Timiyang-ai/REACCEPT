  @Test
  public void test_resolve() {
    BondFutureOption test = sut();
    ResolvedBondFutureOption expected = ResolvedBondFutureOption.builder()
        .securityId(SECURITY_ID)
        .putCall(CALL)
        .strikePrice(STRIKE_PRICE)
        .expiry(EXPIRY_DATE.atTime(EXPIRY_TIME).atZone(EXPIRY_ZONE))
        .premiumStyle(FutureOptionPremiumStyle.DAILY_MARGIN)
        .underlyingFuture(FUTURE.resolve(REF_DATA))
        .build();
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }