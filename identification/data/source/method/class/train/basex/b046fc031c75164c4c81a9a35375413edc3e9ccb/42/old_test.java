@Test
  public void dtd() {
    // specify arguments as file paths
    query(_VALIDATE_DTD.args(FILE, DTD), "");
    // specify document as document nodes
    query(_VALIDATE_DTD.args(DOC.args(FILE), DTD), "");
    // specify arguments as file contents
    query(_VALIDATE_DTD.args(_FILE_READ_TEXT.args(FILE),
        _FILE_READ_TEXT.args(DTD)), "");
    // specify main-memory fragments as arguments
    query(
      "let $doc := <root/> " +
      "let $dtd := '<!ELEMENT root (#PCDATA)>' " +
      "return validate:dtd($doc, $dtd) ", "");

    // invalid arguments
    error(_VALIDATE_DTD.args("unknown"), Err.WHICHRES);
    error(_VALIDATE_DTD.args(FILE, "unknown.dtd"), Err.WHICHRES);
    error(_VALIDATE_DTD.args(FILE), Err.BXVA_FAIL);
    error(
        "let $doc := <root/> " +
        "let $dtd := '<!ELEMENT unknown (#PCDATA)>' " +
        "return validate:dtd($doc, $dtd) ", Err.BXVA_FAIL);
  }