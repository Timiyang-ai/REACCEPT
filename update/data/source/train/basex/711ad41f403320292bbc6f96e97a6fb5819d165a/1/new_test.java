@Test public void parseXml() {
    parse("", "", "<csv/>");
    parse("X", "", "<csv>\n<record>\n<entry>X</entry>\n</record>\n</csv>");
    parse(" '\"X\"\"Y\"'", "", "...<entry>X\"Y</entry>");
    parse(" '\"X\",Y'", "", "...<entry>X</entry>\n<entry>Y</entry>");

    parse("X;Y", "'separator':';'", "...<entry>X</entry>\n<entry>Y</entry>");
    parse("X,Y", "", "...<entry>X</entry>\n<entry>Y</entry>");

    parse("X\nY", "'header':true()", "<csv>\n<record>\n<X>Y</X>\n</record>\n</csv>");
    parse("A,B,C\nX,Y,Z", "'header':true()", "...<A>X</A>\n<B>Y</B>\n<C>Z</C>");

    parse("X\nY", "'format':'attributes','header':true()", "...<entry name=\"X\">Y</entry>");

    parseError("", "'x':'y'");
    parseError("", "'format':'abc'");
    parseError("", "'separator':''");
    parseError("", "'separator':'XXX'");
  }