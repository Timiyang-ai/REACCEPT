  @Test
  public void toStringTest() {
    PrefixList prefixList = new PrefixList(null, ";");
    assertEquals(prefixList.toString(), "");

    prefixList = new PrefixList("", ";");
    assertEquals(prefixList.toString(), "");

    prefixList = new PrefixList(";", ";");
    assertEquals(prefixList.toString(), "");

    prefixList = new PrefixList(" a ; ; b ", ";");
    assertEquals(prefixList.toString(), "a;b;");

    prefixList = new PrefixList("a/b;c", ";");
    assertEquals(prefixList.toString(), "a/b;c;");

    prefixList = new PrefixList("a/b;c;", ";");
    assertEquals(prefixList.toString(), "a/b;c;");
  }