@Test public void testGroupBy() {
    String s =
        Linq4j.asEnumerable(emps)
            .groupBy(
                EMP_DEPTNO_SELECTOR,
                (Function0<String>) () -> null,
                (v1, e0) -> v1 == null ? e0.name : (v1 + "+" + e0.name),
                (v1, v2) -> v1 + ": " + v2)
            .orderBy(Functions.identitySelector())
            .toList()
            .toString();
    assertEquals(
        "[10: Fred+Eric+Janet, 30: Bill]",
        s);
  }