private static void query(final String input, final String options, final String expected,
                            final Function function) {
    final String query = options.isEmpty() ? function.args(input) :
      function.args(input, " {" + options + '}');
    if(expected.startsWith("...")) {
      contains(query, expected.substring(3));
    } else {
      query(query, expected);
    }
  }