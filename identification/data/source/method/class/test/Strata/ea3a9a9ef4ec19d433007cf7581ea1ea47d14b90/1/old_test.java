  @Test
  public void test_notNegativeOrZero_int_ok() {
    assertThat(ArgChecker.notNegativeOrZero(1, "name")).isEqualTo(1);
  }