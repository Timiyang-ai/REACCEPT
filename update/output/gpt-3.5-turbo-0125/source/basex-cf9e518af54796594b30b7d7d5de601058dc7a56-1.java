@Test
public void transform() {
    final Function func = _XSLT_TRANSFORM;
    final String doc = " <a/>";
    String style = wrap("<xsl:template match='/'><X/></xsl:template>");
    query(func.args(doc, ' ' + style), "<X/>");
    query(func.args(doc, style), "<X/>");

    style = wrap("<xsl:param name='t'/><xsl:template match='/'>" +
        "<X><xsl:value-of select='$t'/></X></xsl:template>");
    query(func.args(doc, ' ' + style, " map { 't': '1' }"), "<X>1</X>");
    query(func.args(doc, ' ' + style, " map { 't' : text { '1' } }"), "<X>1</X>");
}