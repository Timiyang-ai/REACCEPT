  @Test
  public void test_parseBoolean() {
    assertThat(LoaderUtils.parseBoolean("TRUE")).isTrue();
    assertThat(LoaderUtils.parseBoolean("True")).isTrue();
    assertThat(LoaderUtils.parseBoolean("true")).isTrue();
    assertThat(LoaderUtils.parseBoolean("t")).isTrue();
    assertThat(LoaderUtils.parseBoolean("yes")).isTrue();
    assertThat(LoaderUtils.parseBoolean("y")).isTrue();
    assertThat(LoaderUtils.parseBoolean("FALSE")).isFalse();
    assertThat(LoaderUtils.parseBoolean("False")).isFalse();
    assertThat(LoaderUtils.parseBoolean("false")).isFalse();
    assertThat(LoaderUtils.parseBoolean("f")).isFalse();
    assertThat(LoaderUtils.parseBoolean("no")).isFalse();
    assertThat(LoaderUtils.parseBoolean("n")).isFalse();
    assertThatIllegalArgumentException().isThrownBy(() -> LoaderUtils.parseBoolean("Rubbish"));
  }