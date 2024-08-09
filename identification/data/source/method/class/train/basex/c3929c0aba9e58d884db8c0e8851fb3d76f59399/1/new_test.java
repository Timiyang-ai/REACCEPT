@Test
  public void parse() {
    parse("", "", "<csv/>");
    parse("X", "", "<csv><record><entry>X</entry></record></csv>");
    parse(" '\"X\"\"Y\"'", "", "...<entry>X\"Y</entry>");
    parse(" '\"X\",Y'", "", "...<entry>X</entry><entry>Y</entry>");

    parse("X;Y", "'separator':';'", "...<entry>X</entry><entry>Y</entry>");
    parse("X,Y", "", "...<entry>X</entry><entry>Y</entry>");

    parse("X\nY", "'header':true()", "<csv><record><X>Y</X></record></csv>");
    parse("A,B,C\nX,Y,Z", "'header':true()", "...<A>X</A><B>Y</B><C>Z</C>");

    parse("X\nY", "'format':'attributes','header':true()", "...<entry name=\"X\">Y</entry>");

    parseError("", "'x':'y'");
    parseError("", "'format':'abc'");
    parseError("", "'separator':''");
    parseError("", "'separator':'XXX'");
  }