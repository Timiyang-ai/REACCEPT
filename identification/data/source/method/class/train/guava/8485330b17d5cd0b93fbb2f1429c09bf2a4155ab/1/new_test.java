  public void test_contains_null_set_yes() {
    Iterable<String> set = Sets.newHashSet("a", null, "b");
    assertTrue(Iterables.contains(set, null));
  }