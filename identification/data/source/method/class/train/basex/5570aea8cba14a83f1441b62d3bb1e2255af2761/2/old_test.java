@Test public void parse() {
    for(final String[] test : TOXML) {
      final String query = test[1].isEmpty() ? _CSV_PARSE.args(test[0]) :
        _CSV_PARSE.args(test[0], " {" + test[1] + "}");
      if(test.length == 2) {
        error(query, Err.BXCS_PARSE, Err.ELMOPTION);
      } else if(test[2].startsWith("...")) {
        contains(query, test[2].substring(3));
      } else {
        query(query, test[2]);
      }
    }
  }