  @Test
  public void test_createProduct() {
    CapitalIndexedBondSecurity test = sut();
    assertThat(test.createProduct(ReferenceData.empty())).isEqualTo(PRODUCT);
    TradeInfo tradeInfo = TradeInfo.builder().tradeDate(date(2016, 6, 30)).settlementDate(date(2016, 7, 1)).build();
    CapitalIndexedBondTrade expectedTrade = CapitalIndexedBondTrade.builder()
        .info(tradeInfo)
        .product(PRODUCT)
        .quantity(100)
        .price(123.50)
        .build();
    assertThat(test.createTrade(tradeInfo, 100, 123.50, ReferenceData.empty())).isEqualTo(expectedTrade);
  }