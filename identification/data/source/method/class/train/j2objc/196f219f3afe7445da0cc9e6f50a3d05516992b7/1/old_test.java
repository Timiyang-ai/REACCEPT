  public void test_hashCode() throws Exception {
    Hello h = new Hello();
    assertEquals(h.hashCode(), Objects.hashCode(h));
    assertEquals(0, Objects.hashCode(null));
  }