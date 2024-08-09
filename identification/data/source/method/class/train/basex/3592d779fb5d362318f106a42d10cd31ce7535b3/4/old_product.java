private static void run(final String query, final String result) {
    query("import module namespace geo='http://expath.org/ns/geo'; " +
          "declare namespace gml='http://www.opengis.net/gml';" + query, result);
  }