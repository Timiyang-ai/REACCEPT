@Test
  public void parse() {
    // check if the function returns a HTML root node
    query(EXISTS.args(_HTML_PARSE.args("&lt;html/&gt;") + "/*:html"), "true");
    // check if the function returns <html/>
    query(_HTML_PARSE.args("&lt;html/&gt;", " {'nons':true()}"), "<html/>");
  }