@Test public void parse() {
    for(final String[] test : TOXML) {
      final String expected = test[test.length == 3 ? 2 : 1];
      final String query = test.length == 3 ? _CSV_PARSE.args(test[0], test[1]) :
        _CSV_PARSE.args(test[0]);
      if(expected.startsWith("...")) {
        contains(query, expected.substring(3));
      } else {
        query(query, expected);
      }
    }
  }