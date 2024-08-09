  @Test
  public void test_total_singleCurrency() {
    assertThat(SENSI_1.total(USD, FxMatrix.empty()).getAmount()).isCloseTo(VECTOR_USD1.sum(), offset(1e-8));
  }