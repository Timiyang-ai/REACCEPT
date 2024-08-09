@Test public void testFilter() {
    final List<String> abc = Arrays.asList("A", "B", "C", "D");
    // a miss, then a hit
    Assert.assertEquals("[A, C, D]",
        Functions.filter(abc,
            new Predicate1<String>() {
              public boolean apply(String v1) {
                return !v1.equals("B");
              }
            }).toString());
    // a hit, then all misses
    Assert.assertEquals("[A]",
        Functions.filter(abc,
            new Predicate1<String>() {
              public boolean apply(String v1) {
                return v1.equals("A");
              }
            }).toString());
    // two hits, then a miss
    Assert.assertEquals("[A, B, D]",
        Functions.filter(abc,
            new Predicate1<String>() {
              public boolean apply(String v1) {
                return !v1.equals("C");
              }
            }).toString());
    Assert.assertSame(Collections.emptyList(),
        Functions.filter(abc, Functions.<String>falsePredicate1()));
    Assert.assertSame(abc,
        Functions.filter(abc, Functions.<String>truePredicate1()));
  }