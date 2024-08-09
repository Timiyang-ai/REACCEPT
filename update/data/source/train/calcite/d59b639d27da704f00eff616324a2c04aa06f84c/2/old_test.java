@Test public void testGroupBy() {
    String s =
        Linq4j.asEnumerable(emps)
            .groupBy(
                EMP_DEPTNO_SELECTOR, new Function0<String>() {
                  public String apply() {
                    return null;
                  }
                }, new Function2<String, Employee, String>() {
                  public String apply(String v1, Employee e0) {
                    return v1 == null ? e0.name : (v1 + "+" + e0.name);
                  }
                }, new Function2<Integer, String, String>() {
                  public String apply(Integer v1, String v2) {
                    return v1 + ": " + v2;
                  }
                })
            .orderBy(Functions.<String>identitySelector())
            .toList()
            .toString();
    assertEquals(
        "[10: Fred+Eric+Janet, 30: Bill]",
        s);
  }