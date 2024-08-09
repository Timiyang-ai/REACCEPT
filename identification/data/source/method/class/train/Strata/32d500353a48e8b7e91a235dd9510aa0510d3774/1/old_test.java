  @Test
  public void test_convertedTo_explicitRate() {
    assertThat(CCY_AMOUNT.convertedTo(CCY2, 2.5d)).isEqualTo(CurrencyAmount.of(CCY2, AMT1 * 2.5d));
    assertThat(CCY_AMOUNT.convertedTo(CCY1, 1d)).isEqualTo(CCY_AMOUNT);
    assertThatIllegalArgumentException().isThrownBy(() -> CCY_AMOUNT.convertedTo(CCY1, 1.5d));
  }