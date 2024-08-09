@Test public void testExists() {
    final List<Integer> ints = Arrays.asList(1, 10, 2);
    final List<Integer> empty = Collections.emptyList();
    Assert.assertFalse(
        Functions.exists(ints, v1 -> v1 > 20));
    Assert.assertFalse(
        Functions.exists(empty, Functions.falsePredicate1()));
    Assert.assertFalse(
        Functions.exists(empty, Functions.truePredicate1()));
  }