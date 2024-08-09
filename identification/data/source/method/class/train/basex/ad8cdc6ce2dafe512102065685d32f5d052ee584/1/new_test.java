@Test
  public void transform() {
    final String doc = "<a/>";
    String style = wrap("<xsl:template match='/'><X/></xsl:template>");
    query(_XSLT_TRANSFORM.args(doc, style), "<X/>");
    query(_XSLT_TRANSFORM.args(doc, '"' + style + '"'), "<X/>");

    style = wrap("<xsl:param name='t'/><xsl:template match='/'>" +
        "<X><xsl:value-of select='$t'/></X></xsl:template>");
    query(_XSLT_TRANSFORM.args(doc, style, " map { 't': '1' }"), "<X>1</X>");
    query(_XSLT_TRANSFORM.args(doc, style, " map { 't' : text { '1' } }"), "<X>1</X>");
  }