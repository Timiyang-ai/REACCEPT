@Test public void testAggregate2() {
    String s =
        Linq4j.asEnumerable(emps)
            .aggregate(
                ((Function0<String>) () -> null).apply(), //CHECKSTYLE: IGNORE 0
                (v1, e0) -> v1 == null ? e0.name : (v1 + "+" + e0.name), v2 -> "<no key>: " + v2);
    assertEquals(
        "<no key>: Fred+Bill+Eric+Janet",
        s);
  }