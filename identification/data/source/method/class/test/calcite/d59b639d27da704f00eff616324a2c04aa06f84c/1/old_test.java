@Test public void testAggregate2() {
    String s =
        Linq4j.asEnumerable(emps)
            .aggregate(
                new Function0<String>() {
                  public String apply() {
                    return null;
                  }
                }.apply(), //CHECKSTYLE: IGNORE 0
                new Function2<String, Employee, String>() {
                  public String apply(String v1, Employee e0) {
                    return v1 == null ? e0.name : (v1 + "+" + e0.name);
                  }
                },
                new Function1<String, String>() {
                  public String apply(String v2) {
                    return "<no key>: " + v2;
                  }
                });
    assertEquals(
        "<no key>: Fred+Bill+Eric+Janet",
        s);
  }