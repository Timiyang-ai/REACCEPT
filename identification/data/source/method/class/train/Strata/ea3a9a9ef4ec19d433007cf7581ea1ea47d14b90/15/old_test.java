  @Test
  public void test_notZero_double_ok() {
    assertThat(ArgChecker.notZero(1d, "name")).isEqualTo(1d);
  }