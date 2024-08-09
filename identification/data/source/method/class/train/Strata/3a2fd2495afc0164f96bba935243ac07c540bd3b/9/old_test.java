  @Test
  public void test_inOrderOrEqual_true() {
    LocalDate a = LocalDate.of(2011, 7, 2);
    LocalDate b = LocalDate.of(2011, 7, 3);
    ArgChecker.inOrderOrEqual(a, b, "a", "b");
    ArgChecker.inOrderOrEqual(a, a, "a", "b");
    ArgChecker.inOrderOrEqual(b, b, "a", "b");
  }