@Test public void parse() {
    for(final String[] test : TOXML) {
      final String expected = test[2];
      final String query = _CSV_PARSE.args(test[0], " {" + test[1] + "}");
      if(test.length == 1) {
        error(query, Err.BXCS_PARSE);
      } else if(expected.startsWith("...")) {
        contains(query, expected.substring(3));
      } else {
        query(query, expected);
      }
    }
  }