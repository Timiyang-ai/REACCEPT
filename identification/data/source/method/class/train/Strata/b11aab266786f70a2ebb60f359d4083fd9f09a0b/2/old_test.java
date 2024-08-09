  @Test
  public void test_getLegs_SwapLegType() {
    assertThat(Swap.of(MOCK_GBP1, MOCK_USD1).getLegs(FIXED)).containsExactly(MOCK_GBP1);
    assertThat(Swap.of(MOCK_GBP1, MOCK_USD1).getLegs(IBOR)).containsExactly(MOCK_USD1);
    assertThat(Swap.of(MOCK_GBP1, MOCK_USD1).getLegs(OVERNIGHT)).isEmpty();
    assertThat(Swap.of(MOCK_GBP1, MOCK_USD1).getLegs(OTHER)).isEmpty();
  }