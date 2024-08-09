  @Test
  public void test_parseDouble() {
    assertThat(LoaderUtils.parseDouble("1.2")).isEqualTo(1.2d, within(1e-10));
    assertThatIllegalArgumentException()
        .isThrownBy(() -> LoaderUtils.parseDouble("Rubbish"))
        .withMessage("Unable to parse double from 'Rubbish'");
  }