  @Test
  public void test_parseDoublePercent() {
    assertThat(LoaderUtils.parseDoublePercent("1.2")).isEqualTo(0.012d, within(1e-10));
    assertThatIllegalArgumentException()
        .isThrownBy(() -> LoaderUtils.parseDoublePercent("Rubbish"))
        .withMessage("Unable to parse percentage from 'Rubbish'");
  }