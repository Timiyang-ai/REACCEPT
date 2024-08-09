public static String normalize(String query) throws QueryException {
    String[] lines = query.split(System.lineSeparator());
    if (lines.length == 1) {
      // short-circuit for performance
      return query;
    }
    return normalize(lines[0], Arrays.stream(lines).skip(1).collect(Collectors.toList()));
  }