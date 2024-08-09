  @Test(groups = "unit")
  public void truncateTest() throws Exception {
    assertEquals(truncate("foo").toString(), "TRUNCATE foo;");
    assertEquals(truncate("foo", quote("Bar")).toString(), "TRUNCATE foo.\"Bar\";");
  }