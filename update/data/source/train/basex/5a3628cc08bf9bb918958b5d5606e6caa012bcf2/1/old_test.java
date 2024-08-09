@Test
  public void typeTest() {
    query("declare function local:f($x as xs:long, $y as xs:NCName)" +
      "    as element(e) {" +
      "  <e x='{$x}' y='{$y}'/>" +
      "};" +
      "local:f#2 instance of function(xs:long, xs:NCName) as element(e)",
      "true");
  }