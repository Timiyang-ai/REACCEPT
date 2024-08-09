@Test
public void dtdInfo() {
  // specify arguments as file paths
  query(_VALIDATE_DTD_INFO.args(FILE, DTD), "");
  // specify document as document nodes
  query(_VALIDATE_DTD_INFO.args(DOC.args(FILE), DTD), "");
  // specify arguments as file contents
  query(_VALIDATE_DTD_INFO.args(_FILE_READ_TEXT.args(FILE),
      _FILE_READ_TEXT.args(DTD)), "");
  // specify main-memory fragments as arguments
  query(
    "let $doc := <root/> " +
    "let $dtd := '<!ELEMENT root (#PCDATA)>' " +
    "return validate:dtd($doc, $dtd) ", "");

  // returned error
  query(EXISTS.args(_VALIDATE_DTD_INFO.args(FILE)), "true");
  query(EXISTS.args(
      "let $doc := <root/> " +
      "let $dtd := '<!ELEMENT unknown (#PCDATA)>' " +
      "return validate:dtd($doc, $dtd) "), "true");

  // invalid arguments
  error(_VALIDATE_DTD_INFO.args("unknown"), Err.WHICHRES_X);
  error(_VALIDATE_DTD_INFO.args(FILE, "unknown.dtd"), Err.WHICHRES_X);
}