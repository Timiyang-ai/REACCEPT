@Test
public void xsd() {
  // specify arguments as file paths
  query(_VALIDATE_XSD.args(FILE, XSD), "");
  // specify arguments as document nodes
  query(_VALIDATE_XSD.args(DOC.args(FILE), DOC.args(XSD)), "");
  // specify arguments as file contents
  query(_VALIDATE_XSD.args(_FILE_READ_TEXT.args(FILE), _FILE_READ_TEXT.args(XSD)), "");
  // specify main-memory fragments as arguments
  query(
    "let $doc := <root/> " +
    "let $schema := <xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'> " +
    "<xs:element name='root'/> " +
    "</xs:schema> " +
    "return validate:xsd($doc, $schema)", "");

  // invalid arguments
  error(_VALIDATE_XSD.args("unknown"), Err.WHICHRES_X); // Assuming similar changes for consistency
  error(_VALIDATE_XSD.args(FILE, "unknown.xsd"), Err.WHICHRES_X); // Assuming similar changes for consistency
  error(_VALIDATE_XSD.args(FILE), Err.BXVA_FAIL_X);
  error(
      "let $doc := <root/> " +
      "let $schema := <xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'> " +
      "<xs:element name='unknown'/> " +
      "</xs:schema> " +
      "return validate:xsd($doc, $schema)", Err.BXVA_FAIL_X);
}