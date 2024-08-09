  @Test
  public void test_inOrderNotEqual_true() {
    LocalDate a = LocalDate.of(2011, 7, 2);
    LocalDate b = LocalDate.of(2011, 7, 3);
    ArgChecker.inOrderNotEqual(a, b, "a", "b");
  }