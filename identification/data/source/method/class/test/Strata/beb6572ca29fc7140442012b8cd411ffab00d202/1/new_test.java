  @Test
  public void test_contractSpecId_future() {
    EtdContractSpecId test = EtdIdUtils.contractSpecId(EtdType.FUTURE, ExchangeIds.ECAG, FGBS);
    assertThat(test.getStandardId()).isEqualTo(StandardId.of("OG-ETD", "F-ECAG-FGBS"));
  }