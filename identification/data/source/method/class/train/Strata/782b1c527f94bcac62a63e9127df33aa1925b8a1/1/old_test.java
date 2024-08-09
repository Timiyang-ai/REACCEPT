  @Test
  public void test_empty() {
    CurveSensitivities test = CurveSensitivities.empty();
    assertThat(test.getInfo()).isEqualTo(PortfolioItemInfo.empty());
    assertThat(test.getTypedSensitivities()).isEmpty();
  }