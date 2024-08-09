  @Test
  public void test_presentValue_settle_before_val() {
    CurrencyAmount pvComputed = PRICER_TRADE.presentValue(BILL_TRADE_SETTLE_BEFORE_VAL, PROVIDER);
    CurrencyAmount pvExpected = PRICER_PRODUCT.presentValue(BILL_PRODUCT.resolve(REF_DATA), PROVIDER)
        .multipliedBy(QUANTITY);
    assertThat(pvComputed.getCurrency()).isEqualTo(EUR);
    assertThat(pvComputed.getAmount()).isCloseTo(pvExpected.getAmount(), offset(TOLERANCE_PV));
    MultiCurrencyAmount ceComputed = PRICER_TRADE.currencyExposure(BILL_TRADE_SETTLE_BEFORE_VAL, PROVIDER);
    assertThat(ceComputed.getCurrencies()).hasSize(1);
    assertThat(ceComputed.contains(EUR)).isTrue();
    assertThat(ceComputed.getAmount(EUR).getAmount()).isCloseTo(pvExpected.getAmount(), offset(TOLERANCE_PV));
    CurrencyAmount cashComputed = PRICER_TRADE.currentCash(BILL_TRADE_SETTLE_BEFORE_VAL, VAL_DATE);
    assertThat(cashComputed.getCurrency()).isEqualTo(EUR);
    assertThat(cashComputed.getAmount()).isCloseTo(0, offset(TOLERANCE_PV));
  }