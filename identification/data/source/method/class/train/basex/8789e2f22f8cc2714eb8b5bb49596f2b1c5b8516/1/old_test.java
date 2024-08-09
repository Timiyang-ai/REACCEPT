@Test
  public void optimizeEbv() {
    query("not(<a/>[b])", "true");
    query("empty(<a/>[b])", "true");
    query("exists(<a/>[b])", "false");

    query("not(<a/>[b = 'c'])", "true");
    query("empty(<a/>[b = 'c'])", "true");
    query("exists(<a/>[b = 'c'])", "false");

    check("empty(<a>X</a>[text()])", null, "//@axis = 'child'");
    check("exists(<a>X</a>[text()])", null, "//@axis = 'child'");
    check("boolean(<a>X</a>[text()])", null, "//@axis = 'child'");
    check("not(<a>X</a>[text()])", null, "//@axis = 'child'");

    check("if(<a>X</a>[text()]) then 1 else 2", null, "//@axis = 'child'");
    check("<a>X</a>[text()] and <a/>", null, "//@axis = 'child'");
    check("<a>X</a>[text()] or <a/>", null, "//@axis = 'child'");
    check("for $a in <a>X</a> where $a[text()] return $a", null, "//@axis = 'child'");

    check("empty(<a>X</a>/.[text()])", null, "//@axis = 'child'");
  }