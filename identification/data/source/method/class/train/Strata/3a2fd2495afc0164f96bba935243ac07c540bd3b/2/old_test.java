  @Test
  public void test_notNegative_int_ok() {
    assertThat(ArgChecker.notNegative(0, "name")).isEqualTo(0);
    assertThat(ArgChecker.notNegative(1, "name")).isEqualTo(1);
  }