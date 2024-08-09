  @Test
  public void test_resolve() {
    FxNdf base = sut();
    ResolvedFxNdf resolved = base.resolve(REF_DATA);
    assertThat(resolved.getAgreedFxRate()).isEqualTo(FX_RATE);
    assertThat(resolved.getIndex()).isEqualTo(GBP_USD_WM);
    assertThat(resolved.getNonDeliverableCurrency()).isEqualTo(USD);
    assertThat(resolved.getPaymentDate()).isEqualTo(PAYMENT_DATE);
    assertThat(resolved.getSettlementCurrency()).isEqualTo(GBP);
    assertThat(resolved.getSettlementCurrencyNotional()).isEqualTo(CURRENCY_NOTIONAL);
    assertThat(resolved.getSettlementNotional()).isEqualTo(NOTIONAL);
  }