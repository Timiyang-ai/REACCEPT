@Test public void testExists() {
    final List<Integer> ints = Arrays.asList(1, 10, 2);
    final List<Integer> empty = Collections.emptyList();
    Assert.assertFalse(
        Functions.exists(ints,
            new Predicate1<Integer>() {
              public boolean apply(Integer v1) {
                return v1 > 20;
              }
            }));
    Assert.assertFalse(
        Functions.exists(empty, Functions.<Integer>falsePredicate1()));
    Assert.assertFalse(
        Functions.exists(empty, Functions.<Integer>truePredicate1()));
  }