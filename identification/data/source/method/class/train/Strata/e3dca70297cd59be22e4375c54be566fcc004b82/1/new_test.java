  @Test
  public void test_isCrossCurrency() {
    assertThat(Swap.of(MOCK_GBP1, MOCK_USD1).isCrossCurrency()).isTrue();
    assertThat(Swap.of(MOCK_GBP1, MOCK_GBP2, MOCK_USD1).isCrossCurrency()).isTrue();
    assertThat(Swap.of(MOCK_GBP1, MOCK_GBP2).isCrossCurrency()).isFalse();
    assertThat(Swap.of(MOCK_GBP1).isCrossCurrency()).isFalse();
  }