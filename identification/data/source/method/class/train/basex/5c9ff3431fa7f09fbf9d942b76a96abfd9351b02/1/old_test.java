@Test public void serialize() {
    for(final String[] test : TOCSV) {
      final String query = test[1].isEmpty() ? _CSV_SERIALIZE.args(test[0]) :
        _CSV_SERIALIZE.args(test[0], " {" + test[1] + "}");
      if(test.length == 2) {
        error(query, Err.BXCS_CONFSEP, Err.INVALIDOPT);
      } else {
        query(query, test[2]);
      }
    }
  }