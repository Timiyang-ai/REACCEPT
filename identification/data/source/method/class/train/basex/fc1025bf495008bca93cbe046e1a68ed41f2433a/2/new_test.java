@Test
  public void selfRecursive() {
    check("declare function local:f($i) { if($i eq 12345) then $i else local:f($i+1) };" +
        "local:f(0)",

        "12345",

        "exists(//" + Util.className(If.class) + '/' +
            Util.className(StaticFuncCall.class) + "[@tailCall eq 'true'])"
    );
  }