  @Test
  public void test_resolve() {
    Swap test = Swap.builder()
        .legs(ImmutableList.of(MOCK_GBP1, MOCK_USD1))
        .build();
    assertThat(test.resolve(REF_DATA)).isEqualTo(ResolvedSwap.of(MOCK_EXPANDED_GBP1, MOCK_EXPANDED_USD1));
  }