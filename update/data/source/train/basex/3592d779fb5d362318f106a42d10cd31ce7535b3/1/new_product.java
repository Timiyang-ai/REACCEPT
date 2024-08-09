private static void run(final String query, final String result) {
    final String qu = "import module namespace geo='http://expath.org/ns/geo'; " +
        "declare namespace gml='http://www.opengis.net/gml';" + query;
    assertEquals(result, query(qu).replaceAll(Prop.NL + "\\s*", ""));
  }