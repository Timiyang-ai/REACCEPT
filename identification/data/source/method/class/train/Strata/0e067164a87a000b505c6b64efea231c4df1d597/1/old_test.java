  @Test
  public void test_isFloat() {
    assertThat(SwapLegType.FIXED.isFloat()).isFalse();
    assertThat(SwapLegType.IBOR.isFloat()).isTrue();
    assertThat(SwapLegType.OVERNIGHT.isFloat()).isTrue();
    assertThat(SwapLegType.INFLATION.isFloat()).isTrue();
    assertThat(SwapLegType.OTHER.isFloat()).isFalse();
  }