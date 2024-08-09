@Test
  public void method() {
    query("declare namespace rect = 'java.awt.Rectangle';" +
        "rect:contains(rect:new(xs:int(2), xs:int(2)), xs:int(1), xs:int(1))", true);
    query("declare namespace p = 'java.util.Properties'; p:new()", "{}");
  }