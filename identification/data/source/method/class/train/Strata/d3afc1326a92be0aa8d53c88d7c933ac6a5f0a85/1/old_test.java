  @Test
  public void test_parseInteger() {
    assertThat(LoaderUtils.parseInteger("2")).isEqualTo(2);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> LoaderUtils.parseInteger("Rubbish"))
        .withMessage("Unable to parse integer from 'Rubbish'");
  }