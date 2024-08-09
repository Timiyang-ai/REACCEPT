public static String normalize(String query) throws QueryException {
    String[] lines = query.split(System.lineSeparator());

    if (!lines[0].contains(SET_SUBSTITUTOR)) {
      // short-circuit for performance
      return query;
    }

    return normalizePattern(lines[0], Arrays.stream(lines).skip(1).collect(Collectors.toList()));
  }