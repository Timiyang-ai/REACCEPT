  public void test_contains_nonnull_yes() {
    Iterator<String> set = asList("a", null, "b").iterator();
    assertTrue(Iterators.contains(set, "b"));
  }