public static String normalize(String pattern, List<String> formatArgs) throws QueryException {
    int numberOfSetsProvided = Iterables.frequency(formatArgs, SET_SEPARATOR) + 1;
    int numberOfSetsRequested = countMatches(pattern, SET_SUBSTITUTOR);

    if (numberOfSetsRequested == 0) {
      // no pattern specified - wrap everything back
      return pattern + System.lineSeparator() + Joiner.on(System.lineSeparator()).join(formatArgs);
    }

    if (numberOfSetsProvided != numberOfSetsRequested && numberOfSetsProvided > 1) {
      String message =
          String.format(
              "Incorrect number of sets. Query uses `%s` %d times but %d sets were given",
              SET_SUBSTITUTOR, numberOfSetsRequested, numberOfSetsProvided);
      throw new QueryException(message);
    }

    // If they only provided one list as args, use that for every instance of `%Ss`
    if (numberOfSetsProvided == 1) {
      return pattern.replace(SET_SUBSTITUTOR, getSetRepresentation(formatArgs));
    }

    List<String> unusedFormatArgs = formatArgs;
    String formattedQuery = pattern;
    while (formattedQuery.contains(SET_SUBSTITUTOR)) {
      int nextSeparatorIndex = unusedFormatArgs.indexOf(SET_SEPARATOR);
      List<String> currentSet =
          nextSeparatorIndex == -1
              ? unusedFormatArgs
              : unusedFormatArgs.subList(0, nextSeparatorIndex);
      // +1 so we don't include the separator in the next list
      unusedFormatArgs = unusedFormatArgs.subList(nextSeparatorIndex + 1, unusedFormatArgs.size());
      formattedQuery =
          formattedQuery.replaceFirst(SET_SUBSTITUTOR, getSetRepresentation(currentSet));
    }
    return formattedQuery;
  }