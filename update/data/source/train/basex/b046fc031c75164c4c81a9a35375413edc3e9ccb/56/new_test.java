@Test
  public void xsdInfo() {
    // specify arguments as file paths
    query(_VALIDATE_XSD_INFO.args(FILE, XSD), "");
    // specify arguments as document nodes
    query(_VALIDATE_XSD_INFO.args(DOC.args(FILE), DOC.args(XSD)), "");
    // specify arguments as file contents
    query(_VALIDATE_XSD_INFO.args(_FILE_READ_TEXT.args(FILE),
        _FILE_READ_TEXT.args(XSD)), "");
    // specify main-memory fragments as arguments
    query(
      "let $doc := <root/> " +
      "let $schema := <xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'> " +
      "<xs:element name='root'/> " +
      "</xs:schema> " +
      "return validate:xsd-info($doc, $schema)", "");

    // returned error
    query(EXISTS.args(_VALIDATE_XSD_INFO.args(FILE)), "true");
    query(EXISTS.args(
        "let $doc := <root/> " +
        "let $schema := <xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'> " +
        "<xs:element name='unknown'/> " +
        "</xs:schema> " +
        "return validate:xsd-info($doc, $schema)"), "true");

    // invalid arguments
    error(_VALIDATE_XSD_INFO.args("unknown"), Err.WHICHRES_X);
    error(_VALIDATE_XSD_INFO.args(FILE, "unknown.xsd"), Err.WHICHRES_X);
  }