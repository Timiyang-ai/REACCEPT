  protected SoyRecord createTestData() {
    SoyList tri = SoyValueConverterUtility.newList(1, 3, 6, 10, 15, 21);
    return SoyValueConverterUtility.newDict(
        "boo",
        8,
        "foo.bar",
        "baz",
        "foo.goo2",
        tri,
        "goo",
        tri,
        "moo",
        3.14,
        "t",
        true,
        "f",
        false,
        "n",
        null,
        "map0",
        SoyValueConverterUtility.newDict(),
        "list0",
        SoyValueConverterUtility.newList(),
        "longNumber",
        1000000000000000001L,
        "floatNumber",
        1.5);
  }