@Test
  public void transform() {
    final String doc = "<a/>";
    String style = wrap("<xsl:template match='/'><X/></xsl:template>");
    query(_XSLT_TRANSFORM.args(doc, style), "<X/>");
    query(_XSLT_TRANSFORM.args(doc, '"' + style + '"'), "<X/>");

    style = wrap("<xsl:param name='t'/><xsl:template match='/'>" +
        "<X><xsl:value-of select='$t'/></X></xsl:template>");
    String param = "<xslt:parameters><xslt:t>1</xslt:t></xslt:parameters>";
    query(_XSLT_TRANSFORM.args(doc, style, param), "<X>1</X>");

    param = " map { 't' : text { '1' } }";
    query(_XSLT_TRANSFORM.args(doc, style, param), "<X>1</X>");
  }